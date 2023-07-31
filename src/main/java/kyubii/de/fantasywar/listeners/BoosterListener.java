package kyubii.de.fantasywar.listeners;

import kyubii.de.fantasywar.commands.BoosterCommand;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.List;

public class BoosterListener implements Listener {
    @EventHandler @SuppressWarnings("all")
    public void onBooster(PlayerJoinEvent e){
        Player p = e.getPlayer();
        if (BoosterCommand.FlyB.intValue() == 1) {
            p.setAllowFlight(true);
        }else if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE){
            p.setAllowFlight(false);
        }
        if (BoosterCommand.BreakB.intValue() != 0){
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 20));
        }
        if (BoosterCommand.KHungerB.intValue() == 1){
            p.removePotionEffect(PotionEffectType.SATURATION);
            p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 200, 20));
        }
    }
    @EventHandler @SuppressWarnings("all")
    public void onMobBooster(EntityDeathEvent e){
        if (!(e.getEntity() instanceof Player) &&
        e.getEntity().getKiller() instanceof Player &&
                BoosterCommand.MobB.intValue() != 0)
            if (e.getEntity().getType() != EntityType.HORSE) {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.MobB.intValue() + 1);
                List<ItemStack> items = e.getDrops();
                for (Integer i = Integer.valueOf(0); i.intValue() < Multiplikator.intValue(); i = Integer.valueOf(i.intValue() + 1)){
                    for (ItemStack newitems : items)
                        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), newitems);
                }
                e.getDrops().clear();
            } else {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.MobB.intValue() + 1);
                List<ItemStack> items = e.getDrops();
                for (Integer i = Integer.valueOf(0); i.intValue() < Multiplikator.intValue(); i = Integer.valueOf(i.intValue() + 1)) {
                    for (ItemStack newitems : items) {
                        if (newitems.getType() == Material.LEATHER)
                            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), newitems);
                    }
                }
                for (ItemStack newitems2 : items) {
                    if (newitems2.getType() != Material.LEATHER)
                        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), newitems2);
                }
                e.getDrops().clear();
            }
    }

    @EventHandler @SuppressWarnings("all")
    public void onErfahrungsBooster(PlayerExpChangeEvent e){
        if (BoosterCommand.XPB.intValue() != 0) {
            Integer Multiplikator = Integer.valueOf(BoosterCommand.XPB.intValue() + 1);
            e.setAmount(e.getAmount() * Multiplikator.intValue());
        }
    }

    @EventHandler @SuppressWarnings("all")
    public void onDropBooster(BlockBreakEvent e){
        if (e.getPlayer().getGameMode() == GameMode.SURVIVAL &&
                BoosterCommand.DropB.intValue() != 0 &&
                !e.getPlayer().getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH))
            if (e.getBlock().getType() == Material.IRON_ORE) {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.DropB.intValue() + 1);
                ItemStack i = new ItemStack(Material.IRON_INGOT, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.GOLD_ORE) {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.DropB.intValue() + 1);
                ItemStack i = new ItemStack(Material.GOLD_INGOT, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.COAL_ORE) {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.DropB.intValue() + 1);
                ItemStack i = new ItemStack(Material.COAL, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.DropB.intValue() + 1);
                ItemStack i = new ItemStack(Material.DIAMOND, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.EMERALD_ORE) {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.DropB.intValue() + 1);
                ItemStack i = new ItemStack(Material.EMERALD, Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.LAPIS_ORE) {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.DropB.intValue() + 1);
                ItemStack i = new ItemStack(Material.LAPIS_LAZULI, 4 + Multiplikator.intValue(), (short)4);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.REDSTONE_ORE) {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.DropB.intValue() + 1);
                ItemStack i = new ItemStack(Material.REDSTONE, 4 + Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            } else if (e.getBlock().getType() == Material.NETHER_QUARTZ_ORE) {
                Integer Multiplikator = Integer.valueOf(BoosterCommand.DropB.intValue() + 1);
                ItemStack i = new ItemStack(Material.QUARTZ, 4 + Multiplikator.intValue());
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
    }
}
