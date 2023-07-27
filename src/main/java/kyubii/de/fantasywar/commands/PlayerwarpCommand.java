package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.PlayerWarpUtils;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerwarpCommand implements CommandExecutor, TabCompleter {
    PlayerWarpUtils warpUtils = new PlayerWarpUtils();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(FantasyWar.getNoPlayer());
            return true;
        }
        Player player =(Player) sender;
        if (!(args.length == 1)){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Benutze §e/playerwarp §7[§9Warp§7]");
            return true;
        }
        String warp = args[0];
        if (!warpUtils.warpExists(warp)){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Dieser §9Warp §7existiert nicht§8.");
            return true;
        }
        Location warpLoc = warpUtils.getWarpLocation(warp);
        player.teleport(warpLoc);
        player.sendMessage(FantasyWar.getSystemPrefix() + "Du bist nun bei dem Warp§8:§9 " + warp);
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return warpUtils.getWarpList();
        }
        return null;
    }
}
