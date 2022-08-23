package cc.yllo.commands.topics;

import org.bukkit.command.CommandSender;

import cc.yllo.types.GenericTopic;

public class Invite extends GenericTopic {
    public Invite(){
        arg = "invite";
    }

    @Override
    public boolean executeArgument(CommandSender sender, String[] args) {
        return true;
    }
}
