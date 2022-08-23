package cc.yllo.commands.topics;

import java.util.ArrayList;

import org.bukkit.command.CommandSender;

import cc.yllo.main;
import cc.yllo.types.ClanType;
import cc.yllo.types.GenericPost;
import cc.yllo.types.GenericTopic;

public class List extends GenericTopic {
    public List(){
        arg = "list";
        // posts list here will be argument numbers to paginate through all the clans from the ClanUtils
    }

    private Integer page = 1;
    private Integer pageSize = 5;

    private String[] paginatedPage(){
        ArrayList<String> paginated = new ArrayList<String>();
        for(int i = (page - 1) * pageSize; i < page * pageSize; i++){
            if(i >= posts.size()){
                break;
            }
            paginated.add(posts.get(i).title + " | " + posts.get(i).body);
        }
        return paginated.toArray(new String[paginated.size()]);
    }

    @Override
    public boolean executeArgument(CommandSender sender, String[] args) {
        posts.clear();
        for(ClanType clan : main.clanUtils.getClanMap().values()){
            // paginate 5 per page
            GenericPost post = new GenericPost();
            post.title = clan.name;
            post.body = clan.members.size() + " members" + " | TAG: " + clan.tag;
            posts.add(post);
        }

        if(args.length == 1 || args.length == 2){
            // list all clans 
            if(args.length == 1){
                page = 1;
            } else {
                // Check if the page is a number
                try{
                    page = Integer.parseInt(args[1]);
                } catch(NumberFormatException e){
                    // If not, just return the specified post 
                    for(GenericPost post : posts){
                        if(post.title.equalsIgnoreCase(args[1])){
                            // Maybe this should return some other message, to give the user a bit more info about this clan.
                            sender.sendMessage(post.title + " | " + post.body);
                            return true;
                        }
                    }
                    sender.sendMessage("No clan found with that name");
                    return true;
                }
            }
            String[] paginated = paginatedPage();
            String message = "";

            if(paginated.length == 0){
                sender.sendMessage("No clans found on this page.");
                return true;
            }

            for(String post : paginated){
                message += post + "\n";
            }

            message += "Page " + page + " of " + (posts.size() / pageSize + 1);
            sender.sendMessage(message);
            return true;
        }
        return true;
    }
}
