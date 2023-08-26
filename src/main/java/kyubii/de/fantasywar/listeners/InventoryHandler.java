package kyubii.de.fantasywar.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryPickupItemEvent;

public class InventoryHandler implements Listener {
    @EventHandler
    public void onInvClick(InventoryClickEvent event){
        if (event.getView().getTitle().equalsIgnoreCase("§7Quests")){
            event.setCancelled(true);
        }
    }
}
