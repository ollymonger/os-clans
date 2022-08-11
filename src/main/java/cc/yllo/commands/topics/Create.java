package cc.yllo.commands.topics;

import org.bukkit.command.CommandSender;

import cc.yllo.main;
import cc.yllo.types.GenericTopic;

public class Create extends GenericTopic {
    public Create(){
        arg = "create";
        // Does not need any extra arguments.
    }

    @Override
    public boolean executeArgument(CommandSender sender, String[] args) {
        if(args.length == 1 || args.length == 2){
            String message = "Usage: /clan create <clan-name> <clan-tag>";
            sender.sendMessage(message);
            return true;
        }

        Boolean create = main.clanUtils.createNewClan(args[1], args[2]);

        if(create){
            String message = "Clan " + args[1] + " created with tag " + args[2];
            sender.sendMessage(message);
        } else {
            String message = "Clan " + args[1] + " already exists";
            sender.sendMessage(message);
        }

        return true;
    }
}
