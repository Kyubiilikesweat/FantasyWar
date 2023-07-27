package kyubii.de.fantasywar.listeners;

import kyubii.de.fantasywar.utils.MySQLUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerjoinHandler implements Listener {
    MySQLUtils mySQLUtils = new MySQLUtils();
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        mySQLUtils.createPlayer(event.getPlayer().getUniqueId().toString());
    }
}
