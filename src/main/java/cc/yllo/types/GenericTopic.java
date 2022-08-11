package cc.yllo.types;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;

public class GenericTopic {
    public String arg;
 
    /* Post is always arg[1] */
    public ArrayList<GenericPost> posts = new ArrayList<GenericPost>();

    public boolean executeArgument(CommandSender sender, String[] args) {
        // do something with the arg: this is overriden
        return true;
    }
}
