package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

public class SignCommand implements CommandExecutor {

    @Override
    @Deprecated
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("§cDu bist kein Spieler!");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission(FantasyWar.getItemsignPerm())) {
            player.sendMessage(FantasyWar.getSystemPrefix() + "§cDafür hast du keine Rechte.");
            return true;
        }
        if (player.getInventory().getItemInMainHand().getType() == Material.AIR) {
            player.sendMessage(FantasyWar.getSystemPrefix() + "Du hast kein §9Item §7in der Hand");
            return true;
        }
        if (!(args.length == 1)) {
            player.sendMessage(FantasyWar.getSystemPrefix() + "Benutze §e/sign §7[§9Beschreibung§7]");
            return true;
        }
        String description = args[0].replace("&", "§");

        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat formatdayint = new SimpleDateFormat("dd");
        SimpleDateFormat formatmonth = new SimpleDateFormat("MM");
        SimpleDateFormat formatyear = new SimpleDateFormat("yyyy");
        player.getInventory().setItemInMainHand(
                new ItemBuilder(player.getItemInHand().getType())
                        .addEnchant(Enchantment.OXYGEN, 1)
                        .hideEnchants()
                        .setDisplayname(player.getItemInHand().getItemMeta().getDisplayName())
                        .setLore("§7\"§e" + description + "§7\"", "§7Signiert von§8:§e " + player.getDisplayName(),
                                "§7Am§8:§e " + formatdayint.format(date) + "." + formatmonth.format(date) + "." + formatyear.format(date))
                        .build());

        player.sendMessage(FantasyWar.getSystemPrefix() + "Das Item wurde erfolgreich signiert§8.");
        return true;
    }
}
