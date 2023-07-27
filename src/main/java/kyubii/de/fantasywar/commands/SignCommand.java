package kyubii.de.fantasywar;

import kyubii.de.fantasywar.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.sql.Timestamp;

public class ItemsignCommand implements CommandExecutor {
    @Override @Deprecated
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage("§cDu bist kein Spieler!");
            return true;
        }
        Player player = (Player) sender;
        if (!player.hasPermission(FantasyWar.getItemsignPerms())){
            player.sendMessage(FantasyWar.getSystemPrefix() + "§cDafür hast du keine Rechte.");
            return true;
        }
        if (!(args.length == 2)){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Benutze §e/sign §7[§9Beschreibung§7]");
            return true;
        }
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Du hälst kein §9Item §7in der Hand");
            return true;
        }
        String description = args[0].replace("&", "§");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        player.getInventory().setItemInMainHand(
                new ItemBuilder(player.getItemInHand().getType())
                .setLore("§7\"§e" + description + "§7\"" ,
                        "§7Signiert von§8:§e " + player.getDisplayName(),
                        "§7Am§8:§e " + timestamp.getDate())
                .build());
        player.sendMessage(FantasyWar.getSystemPrefix() + "Das Item wurde erfolgreich signiert§8.");
        return true;
    }
}
