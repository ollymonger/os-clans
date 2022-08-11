package cc.yllo.commands.topics;

import org.bukkit.command.CommandSender;

import cc.yllo.types.GenericTopic;

public class HelloWorld extends GenericTopic {
    // These are more like parent "topics", for example: Topic help will not have any extra arguments. 
    // Kept out of the main cmd class to keep the code clean.
    public HelloWorld() {
        arg = "helloworld";
    }

    @Override
    public boolean executeArgument(CommandSender sender, String[] args) {
        String message = arg+" > Hello World!";
        sender.sendMessage(message);
        return true;
    }    
}
