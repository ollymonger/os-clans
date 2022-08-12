package cc.yllo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import cc.yllo.main;

public class OnPlayerLogin implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        String clan = main.clanUtils.getPlayerClan(event.getPlayer().getUniqueId().toString()).name;

        if(clan != null) {
            main.plugin.getLogger().info("[Clans] " + event.getPlayer().getName() + " is in clan " + clan);
            event.getPlayer().sendMessage("You are in clan " + clan);
            main.clanUtils.addToMembersMap(event.getPlayer().getUniqueId().toString(), clan);
        }
    }
}
