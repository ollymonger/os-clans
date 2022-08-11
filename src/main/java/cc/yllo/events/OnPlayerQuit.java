package cc.yllo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import cc.yllo.main;

public class OnPlayerQuit implements Listener{
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent event) {
        main.clanUtils.removeFromMembersMap(event.getPlayer().getUniqueId().toString());
        main.plugin.getLogger().info("[Clans] " + event.getPlayer().getName() + " has left the server. Removed from members map.");
    }
}
