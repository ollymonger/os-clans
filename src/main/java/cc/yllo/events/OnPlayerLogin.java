package cc.yllo.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import cc.yllo.main;
import cc.yllo.types.ClanType;

public class OnPlayerLogin implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerLogin(PlayerLoginEvent event) {
        ClanType clan = main.clanUtils.getPlayerClan(event.getPlayer().getUniqueId().toString());

        if(clan != null) {
            main.plugin.getLogger().info("[Clans] " + event.getPlayer().getName() + " is in clan " + clan.name);
            event.getPlayer().sendMessage("You are in clan " + clan.name);
            main.clanUtils.addToMembersMap(event.getPlayer().getUniqueId().toString(), clan.uuid);
        }
    }
}
