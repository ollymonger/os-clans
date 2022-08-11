package cc.yllo.commands.topics;

import org.bukkit.command.CommandSender;

import cc.yllo.commands.topics.posts.TestPost;
import cc.yllo.types.GenericTopic;
import cc.yllo.types.GenericPost;

public class Test extends GenericTopic {
    public Test() {
        arg = "test";
        posts.add(new TestPost());
    }

    @Override
    public boolean executeArgument(CommandSender sender, String[] args) {
        // check to see if args[1] is equal to post[0].topic
        if(args.length == 2){
            for(GenericPost post : posts){
                if(post.title.equalsIgnoreCase(args[1])){
                    post.executePost(sender, args);
                    return true;
                }
            }
        }
        // send all the post.topics to the sender in one string
        String message = arg+"> Available posts: ";
        for(GenericPost post : posts){
            message += post.title + " ";
        }
        sender.sendMessage(message);
        return true;
    }    
}
