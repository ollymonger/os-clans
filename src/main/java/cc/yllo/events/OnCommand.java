package cc.yllo.events;

import java.util.ArrayList;

import org.bukkit.event.Listener;

import cc.yllo.main;
import cc.yllo.commands.HelloWorld;
import cc.yllo.types.GenericCmd;
import cc.yllo.commands.ClanCmd;

public class OnCommand implements Listener {

    private ArrayList<GenericCmd> cmds = new ArrayList<GenericCmd>();

    // figure out how to add specific commands, from their class name

    public OnCommand(){
        cmds.add(new ClanCmd());
        cmds.add(new HelloWorld());
        // register commands
        for(GenericCmd cmd : cmds){
            main.plugin.getServer().getPluginCommand(cmd.name).setExecutor(cmd);
        }
    }
}
