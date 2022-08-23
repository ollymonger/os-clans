package cc.yllo.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import cc.yllo.types.GenericTopic;
import cc.yllo.commands.topics.Create;
import cc.yllo.commands.topics.Dissolve;
import cc.yllo.commands.topics.Info;
import cc.yllo.types.GenericCmd;

public class ClanCmd extends GenericCmd {

    public ClanCmd(){
        name = "clan";
        description = "Clan command";
        usage = "/clan";
        // argsList.add(new Test());
        // argsList.add(new HelloWorld());
        argsList.add(new Create());
        argsList.add(new Dissolve());
        argsList.add(new Info());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(cmd.getName().equalsIgnoreCase("clan")){
            if(args.length == 0){
                String message = "Available topics: ";
                for(GenericTopic arg : argsList){
                    message += arg.arg + " ";
                }
                sender.sendMessage(message);
                return true;
            }
            // using argsList, 
            // get the correct argument by args.name and execute it
            for(GenericTopic arg : argsList){
                if(arg.arg.equalsIgnoreCase(args[0])){
                    arg.executeArgument(sender, args);
                    return true;
                }
            }
            return true;
        }
        return true;
    }
}
