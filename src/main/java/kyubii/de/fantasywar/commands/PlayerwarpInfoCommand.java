package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.PlayerWarpUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerwarpInfoCommand implements CommandExecutor, TabCompleter {
    PlayerWarpUtils warpUtils = new PlayerWarpUtils();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(FantasyWar.getNoPlayer());
            return true;
        }
        Player player = (Player) sender;
        if (!(args.length == 1)){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Benutze §e/fantasywarpinfo §7[§9Warp§7]");
            return true;
        }
        if (!player.hasPermission("fantasywarp.bypass")){
            player.sendMessage(FantasyWar.getNoPerm());
            return true;
        }
        String warp = args[0];
        if (!warpUtils.warpExists(warp)){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Dieser §9Warp §7existiert nicht§8.");
            return true;
        }
        player.sendMessage(FantasyWar.getSystemPrefix() + "Der Warp §9" + warp + " §7gehört dem Spieler§8: §e" +  warpUtils.getWarpOwner(warp));
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1){
            return warpUtils.getWarpList();
        }
        return null;
    }
}
