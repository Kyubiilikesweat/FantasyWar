package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.hasPermission(FantasyWar.getBroadcastPerm())){
            if (args.length >= 1){
                StringBuilder msg = new StringBuilder("§4§lINFO §8»" + " §a§l");
                for (String arg : args) {
                    msg.append(arg).append(" ");
                }
                Bukkit.broadcastMessage("§8»");
                Bukkit.broadcastMessage(msg.toString());
                Bukkit.broadcastMessage("§8»");
                for (Player all : Bukkit.getOnlinePlayers()){
                    all.playSound(all.getLocation(), Sound.BLOCK_BELL_USE, 45, 65);
                }
            }else
                commandSender.sendMessage(FantasyWar.getSystemPrefix() + "§cUsage: /bc <Message>");
        }else
            commandSender.sendMessage(FantasyWar.getSystemPrefix() + "§cDazu hast du keine Rechte!");
        return false;
    }
}
