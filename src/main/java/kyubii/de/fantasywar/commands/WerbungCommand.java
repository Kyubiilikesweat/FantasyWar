package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.WerbungMySQL;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class WerbungCommand implements CommandExecutor {
    private final Set<String> insults = new HashSet<>();

    public WerbungCommand(){
        insults.add("hurensohn");
        insults.add("nutte");
        insults.add("fotze");
        insults.add("hs");
        insults.add("nigah");
        insults.add("niggah");
        insults.add("nigger");
        insults.add("niger");
        insults.add("bastard");
        insults.add("nuttensohn");
    }


    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player player){
            if (strings.length < 1){
                player.sendMessage(FantasyWar.getSystemPrefix() + "Benutze §e/werbung §7[§9Werbung§7]");
                return false;
            }
            try {
                if (WerbungMySQL.getTime(player.getUniqueId()) >= 1){
                    player.sendMessage( FantasyWar.getSystemPrefix() + "§cDu musst noch etwas warten bevor du erneut Werbung machst");
                    return true;
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

            /*for (String insult : insults){
                if (insults.contains(insult.toLowerCase())){
                    player.sendMessage(FantasyWar.getSystemPrefix() + "§cDeine Werbung enthält unerwünschte Inhalte!");
                    return false;
                }
            }**/

            for (Player target : Bukkit.getOnlinePlayers()){
                target.sendMessage("§8§m————§3 WERBUNG §8§m————");
                target.sendMessage(" ");
                target.sendMessage("§7" + String.join(" ", strings).replaceAll("&", "§"));
                target.sendMessage(" ");
                target.sendMessage("§7Von: §3" + player.getName());
                target.sendMessage("§8§m————§3 WERBUNG §8§m————");
            }

            try {
                WerbungMySQL.addTime(player.getUniqueId(), 12000); //12000 = 10min
            } catch (SQLException e) {
                e.printStackTrace();
            }
            WerbungMySQL.startUpdatingTimes();
        } else {
            commandSender.sendMessage(FantasyWar.getNoPlayer());
            return true;
        }

        return false;
    }

}
