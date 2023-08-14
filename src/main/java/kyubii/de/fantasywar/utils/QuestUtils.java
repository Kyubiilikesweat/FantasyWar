package kyubii.de.fantasywar.utils;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.configs.QuestConfig;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@SuppressWarnings("all")
public class QuestUtils {
    QuestConfig config = new QuestConfig();
    private static final Connection con = FantasyWar.mysql.getConnection();

    /*public void createPlayer(UUID uuid){
        if (!playerExists(uuid)){
            createQuests(uuid, "mining");
        }
    } */
    public boolean playerExists(UUID uuid, String quest){
        Connection connection;
        String update = "SELECT player_uuid FROM playerinfo WHERE player_uuid=?";
        PreparedStatement p;
        try {
            connection = con;
            p = connection.prepareStatement(update);
            p.setString(1, uuid.toString());
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


    private void createQuests(UUID uuid, String quest){
        config.get().set(uuid + "." + quest + ".level", 0);
        config.get().set(uuid + "." + quest + ".exp", 0);
        config.get().set(uuid + "." + quest + ".exptolvlup", 0);
        config.get().set(uuid + "." + quest + ".reward", 100);
        config.save();
        config.reload();
    }
    public int getLevel(UUID uuid, String quest){
        return config.get().getInt(uuid + "." + quest + ".level");
    }

    public int getExp(UUID uuid, String quest){
        return config.get().getInt(uuid + "." + quest + ".exp");
    }
    public int getExpToLvlUp(UUID uuid, String quest){
        return config.get().getInt(uuid + "." + quest + ".exptolvlup");
    }
    public int getReward(UUID uuid, String quest){
        return config.get().getInt(uuid + "." + quest + ".reward");
    }
    public void setLevel(UUID uuid, String quest, int level){
        config.get().set(uuid + "." + quest + ".level", level);
        config.save();
        config.reload();
    }
    public void setExp(UUID uuid, String quest, int exp){
        config.get().set(uuid + "." + quest + ".exp", exp);
        config.save();
        config.reload();
    }
    public void setExpToLevelUp(UUID uuid, String quest, int exp){
        config.get().set(uuid + "." + quest + ".exptolvlup", exp);
        config.save();
        config.reload();
    }
    public void setReward(UUID uuid, String quest, int reward){
        config.get().set(uuid + "." + quest + ".reward", reward);
        config.save();
        config.reload();
    }
}
