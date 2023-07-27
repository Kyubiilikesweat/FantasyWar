package de.group.fwsystemcb.commands;

import de.group.fwsystemcb.Main;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class KopfCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(Main.noplayer);
        }else {
            Player p = (Player) sender;
            Player target = Bukkit.getPlayer(args[0]);
            if (p.hasPermission("kopf.use")){
                if (args.length == 1){
                    ItemStack is = new ItemStack(Material.LEGACY_SKULL_ITEM, 1 , (short)3);
                    SkullMeta im  = (SkullMeta)is.getItemMeta();
                    im.setOwner(args[0]);
                    im.setDisplayName("§e§l"+args[0]);
                    is.setItemMeta(im);
                    p.getInventory().addItem(new ItemStack[] {is});
                    p.updateInventory();
                    return true;
                }else p.sendMessage(Main.prefix + "§cUsage: /kopf <Player>");
                return true;
            }else p.sendMessage(Main.noperm);
        }
        return false;
    }
}
