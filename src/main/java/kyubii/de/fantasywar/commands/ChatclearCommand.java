package kyubii.de.fantasywar.commands;


import kyubii.de.fantasywar.FantasyWar;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class chatclear implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("chatclear.use")) {
            sender.sendMessage(FantasyWar.getNoPerm());
        } else {
            for (int i = 0; i < 1005; ++i) {
                for (Player all : Bukkit.getOnlinePlayers()) {
                    if (!all.hasPermission("chatclear.see")) {
                        all.sendMessage(" ");
                    }
                }
            }

            Bukkit.broadcastMessage(FantasyWar.getSystemPrefix() + "§7Der Chat wurde von §e" + sender.getName() + " §7geleert.");
            return true;
        }
        return false;
    }
}