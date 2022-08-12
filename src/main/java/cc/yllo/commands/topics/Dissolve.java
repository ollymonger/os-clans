package cc.yllo.commands.topics;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import cc.yllo.main;
import cc.yllo.types.GenericTopic;

public class Dissolve extends GenericTopic {
    public Dissolve(){
        arg = "dissolve";
    }

    @Override
    public boolean executeArgument(CommandSender sender, String[] args) {
        Player player = (Player) sender;
        String userClan = main.clanUtils.getPlayerClan(player.getUniqueId().toString()).name;

        if(userClan == null){
            String message = "You are not in a clan.";
            sender.sendMessage(message);
            return true;
        }

        if(!main.clanUtils.isLeader(player.getUniqueId().toString(), userClan)){
            String message = "You are not the leader of your clan.";
            sender.sendMessage(message);
            return true;
        }



        return true;
    }
}
