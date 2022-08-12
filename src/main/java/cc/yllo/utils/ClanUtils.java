package cc.yllo.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import cc.yllo.main;
import cc.yllo.types.ClanType;
import dev.dejvokep.boostedyaml.YamlDocument;
import dev.dejvokep.boostedyaml.dvs.versioning.BasicVersioning;
import dev.dejvokep.boostedyaml.settings.dumper.DumperSettings;
import dev.dejvokep.boostedyaml.settings.general.GeneralSettings;
import dev.dejvokep.boostedyaml.settings.loader.LoaderSettings;
import dev.dejvokep.boostedyaml.settings.updater.UpdaterSettings;

public class ClanUtils implements Listener {
    
    private static HashMap<String, ClanType> clanMap = new HashMap<String, ClanType>();
    public static ArrayList<YamlDocument> clans;

    public static HashMap<String, String> membersMap = new HashMap<String, String>();

    // Generate a random number based on tick
    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public ClanUtils(){
        clans = Config.getClans();
        for(YamlDocument cfg : clans){
            String clanName = cfg.getSection("settings").getString("clanName");
            String uuid = cfg.getSection("settings").getString("uuid");
            String tag = cfg.getSection("settings").getString("clanTag");
            // Get members
            HashMap<String, Integer> members = new HashMap<String, Integer>();
            for(Object key : cfg.getSection("settings").getSection("members").getKeys()){
                members.put(key.toString(), cfg.getSection("settings").getSection("members").getInt(key.toString()));
            }
            ClanType clan = new ClanType(clanName, uuid, tag, members);
            clanMap.put(uuid, clan);
            main.plugin.getServer().getLogger().info("[Clans] Loaded clan: " + clanName);
        }
    }

    public static void addMember(String uuid, int type, String clanUuid){
        ClanType clan = clanMap.get(clanUuid);
        clan.members.put(uuid, type);
        membersMap.put(uuid, clanUuid);
        try {
            saveClan(clan);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void removeMember(String uuid, String clanUuid){
        ClanType clan = clanMap.get(clanUuid);
        clan.members.remove(uuid);
        membersMap.remove(uuid);
        try {
            saveClan(clan);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void createClanConfig(String clanName, String uuid, String tag){
        try {
            YamlDocument cfg = YamlDocument.create(new File(main.plugin.getDataFolder(), clanName+".yml"), main.plugin.getResource("clan_template.yml"),
                    GeneralSettings.DEFAULT, LoaderSettings.builder().setAutoUpdate(true).build(), DumperSettings.DEFAULT, UpdaterSettings.builder().setVersioning(new BasicVersioning("file-version")).build());
            
            cfg.getSection("settings").set("uuid", uuid);
            cfg.getSection("settings").set("clanName", clanName);
            cfg.getSection("settings").set("clanTag", tag);
            cfg.save();
            cfg.reload();
            clans.add(cfg);     
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean createNewClan(CommandSender sender, String clanName, String tag){
        // Check in clanMap if the clanName is already in use
        // Hashmap key = uuid, value = clanName
        for(String key : clanMap.keySet()){
            if(clanMap.get(key).name.equals(clanName)){
                main.plugin.getLogger().info("[Clans] Clan " + clanName + " already exists");
                return false;
            }
        }
        String uuid = String.valueOf(randomNumber(1,10000));

        if(clanMap.containsKey(uuid)){
            uuid = String.valueOf(randomNumber(1,1000000));
        }

        try {
            createClanConfig(clanName, uuid, tag);
            clanMap.put(uuid, new ClanType(clanName, uuid, tag, new HashMap<String, Integer>()));
            Player player = (Player) sender;
            addMember(player.getUniqueId().toString(), 0, uuid);
            main.plugin.getLogger().info("[Clans] Created clan " + clanName);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveClan(ClanType clan) throws IOException{
        for(YamlDocument cfg : clans){
            if(cfg.getSection("settings").getString("uuid").equals(clan.uuid)){
                main.plugin.getLogger().info("Saving clan: " + clan.name);
                cfg.getSection("settings").set("members", clan.members);
                cfg.save();
                cfg.reload();
            }
        }
    }

    public String getPlayerClan(String uuid, Boolean tag) {
        for(String key : clanMap.keySet()){
            main.plugin.getLogger().info("Checking clan: " + clanMap.get(key).members.toString());
            if(clanMap.get(key).members.containsKey(uuid)){
                if (tag) return clanMap.get(key).tag;
                return clanMap.get(key).uuid;
            }
        }
        return null;
    }

    public boolean isLeader(String uuid, String clanUuid){
        if(clanMap.get(clanUuid).members.get(uuid) == 0){
            return true;
        }
        return false;
    }

    public boolean addToMembersMap(String uuid, String clanUuid){
        if(membersMap.containsKey(uuid)){
            return false;
        }
        membersMap.put(uuid, clanUuid);
        return true;
    }

    public boolean removeFromMembersMap(String uuid){
        if(!membersMap.containsKey(uuid)){
            return false;
        }
        membersMap.remove(uuid);
        return true;
    }

    public static void saveAllClans() throws IOException{
        for(ClanType clan : clanMap.values()){
            saveClan(clan);
        }
    }
}
