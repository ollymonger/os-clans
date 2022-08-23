package cc.yllo.commands.topics;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

import cc.yllo.main;
import cc.yllo.types.ClanType;
import cc.yllo.types.GenericPost;
import cc.yllo.types.GenericTopic;

public class Info extends GenericTopic {
    public Info(){
        arg = "info";
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
            post.body = clan.members.size() + " &7members" + "&d | &7TAG: " + clan.tag;
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
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7-=========={&c "+post.title+"&7 }==========-"));
                            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7" + post.title + "\n &d | &7" + post.body));
                            return true;
                        }
                    }
                    sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cCould not find clan with name: " + args[1]));
                    return true;
                }
            }
            String[] paginated = paginatedPage();
            String message = "";

            if(paginated.length == 0){
                sender.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo clans found on page " + page));
                return true;
            }

            for(String post : paginated){
                message += post + "\n";
            }

            message += "&d{&3 Page " + page + " of " + (posts.size() / pageSize + 1+" &d}");
            sender.sendMessage(ChatColor.translateAlternateColorCodes('&', message));
            return true;
        }
        return true;
    }
}
