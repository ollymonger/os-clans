package cc.yllo.commands.topics.posts;

import org.bukkit.command.CommandSender;

import cc.yllo.types.GenericPost;

public class TestPost extends GenericPost {
    public TestPost(){
        title = "test";
        body = "test > test > You have executedL /clan test test to see this message.";
    }

    @Override
    public boolean executePost(CommandSender sender, String[] args) {
        sender.sendMessage(body);
        return true;
    }
}
