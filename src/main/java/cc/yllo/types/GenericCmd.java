package cc.yllo.types;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

public class GenericCmd implements CommandExecutor, TabCompleter {
    /* This is used to give me the ability to loop through every single "generic command", and use the command properly. */
    public String name;
    public String description;
    public String usage;

    /* Topic is always arg[0] */
    public ArrayList<GenericTopic> argsList = new ArrayList<GenericTopic>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        // Loop through every single "generic topic" and return the list of topics
        List<String> list = new ArrayList<String>();
        if(args.length == 1){
            list.clear();
            for(GenericTopic arg : argsList){
                if(arg.arg.startsWith(args[0])){
                    list.add(arg.arg);
                }
            }
        }
        if(args.length == 2){
            list.clear();
            for(GenericTopic arg : argsList){
                for(GenericPost post : arg.posts){
                    if(post.title.startsWith(args[1])){
                        list.add(post.title);
                    }
                }
            }
        }
        return list;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return true;
    }
}
