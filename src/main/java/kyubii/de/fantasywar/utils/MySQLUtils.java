package kyubii.de.fantasywar.utils;

import kyubii.de.fantasywar.FantasyWar;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MySQLUtils {
    private static final Connection con = FantasyWar.mysql.getConnection();

    public boolean playerExist(String uuid){
        Connection connection;
        String update = "SELECT player_uuid FROM playerinfo WHERE player_uuid=?";
        PreparedStatement p;
        try {
            connection = con;
            p = connection.prepareStatement(update);
            p.setString(1, uuid);
            ResultSet rs = p.executeQuery();
            if(rs.next()){
                return true;
            }
            p.execute();
            p.close();
        } catch (SQLException e) {
            //Print out any exception while trying to prepare statement
            Bukkit.getConsoleSender().sendMessage("§cEs ist ein Fehler aufgetreten! Bitte kontaktiere einen Entwickler");
            e.printStackTrace();
        }

        return false;
    }

    public void createPlayer(String uuid){
        if(!playerExist(uuid)){
            Connection connection = null;
            String update = "INSERT INTO playerinfo(player_uuid, warps) VALUES (?, ?)";
            PreparedStatement p = null;
            try {
                connection = con;
                //Preparing statement - INSERT INTO...
                p = connection.prepareStatement(update);
                p.setString(1, uuid);
                p.setInt(2, 0);
                p.execute();
                p.close();
            } catch (SQLException e) {
                Bukkit.getConsoleSender().sendMessage("§cEs ist ein Fehler aufgetreten! Bitte kontaktiere einen Entwickler");
                e.printStackTrace();
            }
        }
    }
}
