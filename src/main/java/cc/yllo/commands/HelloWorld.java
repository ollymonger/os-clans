package cc.yllo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import cc.yllo.types.GenericCmd;

public class HelloWorld extends GenericCmd {

    public HelloWorld(){
        name = "helloworld";
        description = "testing command factory";
        usage = "/helloworld";
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase(name)){
            sender.sendMessage("Hello World!");
            return true;
        }
        return true;
    }
}
