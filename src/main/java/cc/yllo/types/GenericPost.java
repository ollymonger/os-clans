package cc.yllo.types;

import org.bukkit.command.CommandSender;

public class GenericPost  {
    public String title;
    public String body;

    /* Post always handles additional arguments */
    public boolean executePost(CommandSender sender, String[] args) {
        // do something with the arg: this is overriden
        return true;
    }
}
