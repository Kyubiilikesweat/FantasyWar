package kyubii.de.fantasywar.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class QuestsHandler implements Listener {
    //Miner

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if (player.getWorld().getName().equalsIgnoreCase("Farmwelt-1-")){

        }
    }
}
