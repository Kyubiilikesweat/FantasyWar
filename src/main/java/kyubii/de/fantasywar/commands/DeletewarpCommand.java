package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.PlayerWarpUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.List;

public class DeletewarpCommand implements CommandExecutor, TabCompleter {
    PlayerWarpUtils warpUtils = new PlayerWarpUtils();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(FantasyWar.getNoPlayer());
            return true;
        }
        Player player = (Player) sender;
        if (!(args.length == 1)){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Benutze §e/deleteplayerwarp §7[§9Warp§7]");
            return true;
        }
        String warp = args[0];
        if (!warpUtils.warpExists(warp)){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Dieser §9Warp §7existiert nicht§8.");
            return true;
        }
        if (!warpUtils.isOwnerOfWarp(player.getUniqueId(), warp)){
            if (!player.hasPermission("playerwarp.bypass")) {
                player.sendMessage(FantasyWar.getNoPerm());
                return true;
            }
        }
        warpUtils.removeWarp(warp);
        player.sendMessage(FantasyWar.getSystemPrefix() + "Der §9Warp §7wurde §aerfolgreich §7gelöscht§8.");
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
