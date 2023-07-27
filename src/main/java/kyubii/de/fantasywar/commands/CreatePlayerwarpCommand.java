package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.PlayerWarpUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class PlayerWarpCommand implements CommandExecutor {
    PlayerWarpUtils warpUtils = new PlayerWarpUtils();
    @Override @SuppressWarnings("all")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(FantasyWar.getNoPlayer());
            return true;
        }
        if ()



        return true;
    }
}
