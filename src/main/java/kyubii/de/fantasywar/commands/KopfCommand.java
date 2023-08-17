package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class KopfCommand implements CommandExecutor {

    @Override
    @SuppressWarnings("all")
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(FantasyWar.getNoPlayer());
        } else {
            Player p = (Player) sender;
            if (p.hasPermission(FantasyWar.getKopfPerm())) {
                if (args.length == 1) {
                    ItemStack is = new ItemStack(Material.LEGACY_SKULL_ITEM, 1, (short) 3);
                    SkullMeta im = (SkullMeta) is.getItemMeta();
                    im.setOwner(args[0]);
                    im.setDisplayName("§e§l" + args[0]);
                    is.setItemMeta(im);
                    p.getInventory().addItem(new ItemStack[]{is});
                    p.updateInventory();
                    return true;
                } else p.sendMessage(FantasyWar.getSystemPrefix() + "§cUsage: /kopf <Player>");
                return true;
            } else p.sendMessage(FantasyWar.getNoPerm());
        }
        return false;
    }
}
