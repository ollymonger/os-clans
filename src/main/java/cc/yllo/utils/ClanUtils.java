package cc.yllo.utils;

import java.io.File;
import java.util.HashMap;

import org.bukkit.event.Listener;

import cc.yllo.main;
import cc.yllo.types.ClanType;
import dev.dejvokep.boostedyaml.YamlDocument;

public class ClanUtils implements Listener {
    
    private static HashMap<String, ClanType> clanMap = new HashMap<String, ClanType>();

    // Generate a random number based on tick
    public static int randomNumber(int min, int max) {
        return (int) (Math.random() * (max - min) + min);
    }

    public ClanUtils(){
        YamlDocument[] clans = Config.getClans();
        for(YamlDocument cfg : clans){
            String clanName = cfg.getSection("settings").getString("clanName");
            String uuid = cfg.getSection("settings").getString("uuid");
            String tag = cfg.getSection("settings").getString("clanTag");
            ClanType clan = new ClanType(clanName, uuid, tag);
            clanMap.put(uuid, clan);
            main.plugin.getServer().getLogger().info("[Clans] Loaded clan: " + clanName);
        }
    }

    public boolean createNewClan(String clanName, String tag){
        // Check in clanMap if the clanName is already in use
        // Hashmap key = uuid, value = clanName
        for(String key : clanMap.keySet()){
            if(clanMap.get(key).name.equals(clanName)){
                return false;
            }
        }
        String uuid = String.valueOf(randomNumber(1,10000));

        if(clanMap.containsKey(uuid)){
            uuid = String.valueOf(randomNumber(1,1000000));
        }

        try {
            main.config.createClanConfig(clanName, uuid, tag);
            clanMap.put(uuid, new ClanType(clanName, uuid, tag));
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void saveAllClans(){
        // Maybe have a hash map of all clan configs
        // Iterate through the hash map and save each config
    }
}
