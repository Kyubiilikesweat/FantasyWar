package kyubii.de.fantasywar.listeners;

import kyubii.de.fantasywar.commands.PlayerwarpCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class PlayerDamageListener implements Listener {
    @EventHandler
    public void onPlayerDamage(EntityDamageByEntityEvent event){
        if (event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if (PlayerwarpCommand.warpCooldownMap.containsKey(player)) {
                if (PlayerwarpCommand.warpCooldownMap.get(player)) {
                    event.setCancelled(true);
                }
            }
        }
    }
    @EventHandler
    public void onFallDamage(EntityDamageEvent event){
        Player player = (Player) event.getEntity();
        if (PlayerwarpCommand.warpCooldownMap.containsKey(player)) {
            if (PlayerwarpCommand.warpCooldownMap.get(player)) {
                event.setCancelled(true);
            }
        }
    }
}
