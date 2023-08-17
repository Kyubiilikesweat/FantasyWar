package kyubii.de.fantasywar.listeners;

import kyubii.de.fantasywar.utils.MySQLUtils;
import kyubii.de.fantasywar.utils.WerbungMySQL;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class PlayerjoinHandler implements Listener {

    MySQLUtils mySQLUtils = new MySQLUtils();
    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        mySQLUtils.createPlayer(event.getPlayer().getUniqueId().toString());

        try {
            if (!WerbungMySQL.playerExists(event.getPlayer().getUniqueId())) {
                WerbungMySQL.addPlayer(event.getPlayer().getUniqueId());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
