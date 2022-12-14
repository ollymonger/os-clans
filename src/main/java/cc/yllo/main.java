package cc.yllo;

import java.io.IOException;
import java.util.logging.Level;

import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import cc.yllo.events.OnCommand;
import cc.yllo.events.OnPlayerLogin;
import cc.yllo.events.PlayerChat;
import cc.yllo.utils.ClanUtils;
import cc.yllo.utils.Config;

public class main extends JavaPlugin {
    // Main class: - should only contain startup methods, and shutdown methods
    public static Plugin plugin;
    public Server server;
    public static ClanUtils clanUtils;

    public static Config config;

    @Override
    public void onEnable() {
        // Called when the plugin is enabled
        main.plugin = this;
        this.server = this.getServer();
        this.getLogger().log(Level.INFO, "{ Clans } has been enabled");
    
        config = new Config();
        PlayerChat playerChat = new PlayerChat();
        OnCommand command = new OnCommand();
        clanUtils = new ClanUtils();
        //REGISTER LISTENERS
        this.server.getPluginManager().registerEvents(config, this);
        this.server.getPluginManager().registerEvents(new OnPlayerLogin(), this);
        this.server.getPluginManager().registerEvents(playerChat, this);
        this.server.getPluginManager().registerEvents(command, this);
        this.server.getPluginManager().registerEvents(clanUtils, this);
    }

    @Override
    public void onDisable() {
        // Called when the plugin is disabled
        this.getLogger().log(Level.INFO, "{ Clans } has been disabled");
        // support reloading
        this.saveConfig();
        try {
            this.getLogger().log(Level.INFO, "{ Clans } has been saved.");
            ClanUtils.saveAllClans();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
