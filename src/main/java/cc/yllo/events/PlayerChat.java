package cc.yllo.events;


import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import cc.yllo.main;
import cc.yllo.types.ClanType;

public class PlayerChat implements Listener {
    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        // get the format of the message
        String clanString = main.config.getConfig().getString("general.format");
        ClanType clanTag = main.clanUtils.getPlayerClan(event.getPlayer().getUniqueId().toString());
        // add main.config.getConfig().getSection("general").getString("format") to the beginning
        if(clanTag != null) {
            event.setFormat(ChatColor.translateAlternateColorCodes('&', clanString).replace("%CLAN%",clanTag.tag)+" "+event.getFormat());
        }
    }
}