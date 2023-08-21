package kyubii.de.fantasywar.utils;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.events.QuestGetExperienceEvent;
import kyubii.de.fantasywar.events.QuestLevelUpEvent;
import org.bukkit.Bukkit;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

public class QuestMySQL {


    public static void createQuestTable() throws SQLException {

        try (Statement stmt = FantasyWar.mysql.getConnection().createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS Quests (" +
                    "UUID VARCHAR(36) PRIMARY KEY," +
                    "Miner INT DEFAULT 1," +
                    "Laeufer INT DEFAULT 1," +
                    "Bauarbeiter INT DEFAULT 1," +
                    "Farmer INT DEFAULT 1," +
                    "Schwimmer INT DEFAULT 1," +
                    "CountMiner INT DEFAULT 0," +
                    "CountLaeufer INT DEFAULT 0," +
                    "CountBauarbeiter INT DEFAULT 0," +
                    "CountFarmer INT DEFAULT 0," +
                    "CountSchwimmer INT DEFAULT 0" +
                    ")";
            stmt.executeUpdate(createTableSQL);
        }
    }

    public void addPlayer(UUID uuid) throws SQLException {
        if (!playerExists(uuid)) {
            try (PreparedStatement stmt = FantasyWar.mysql.getConnection().prepareStatement(
                    "INSERT INTO Quests (UUID) VALUES (?)")) {
                stmt.setString(1, uuid.toString());
                stmt.executeUpdate();
            }
        }
    }

    public boolean playerExists(UUID uuid) throws SQLException{
        try (PreparedStatement stmt = FantasyWar.mysql.getConnection().prepareStatement(
                "SELECT COUNT(UUID) AS count FROM Quests WHERE UUID = ?")){
            stmt.setString(1, uuid.toString());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int count = rs.getInt("count");
                return count > 0;
            }
        }
        return false;
    }

    public void setExperience(UUID uuid, String quest, int exp) {
        try {
            PreparedStatement p = FantasyWar.mysql.getConnection().prepareStatement("UPDATE Quests SET Count" + quest.toUpperCase() + "=? WHERE UUID=?");
            p.setString(2, uuid.toString());
            p.setInt(1, exp);
            p.execute();
            p.close();
            QuestGetExperienceEvent questGetExperienceEvent = new QuestGetExperienceEvent(Bukkit.getPlayer(uuid), quest, exp, getLevel(uuid, quest));
            Bukkit.getPluginManager().callEvent(questGetExperienceEvent);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getExperience(UUID uuid, String quest){
        try {
            PreparedStatement p = FantasyWar.mysql.getConnection().prepareStatement("SELECT * FROM Quests WHERE UUID=?");
            p.setString(1, uuid.toString());
            ResultSet rs = p.executeQuery();
            if (rs.next()){
                return rs.getInt("Count" + quest.toUpperCase());
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


    public void setLevel(UUID uuid, String quest, int lvl) {
        try {
            PreparedStatement p = FantasyWar.mysql.getConnection().prepareStatement("UPDATE Quests SET " + quest.toUpperCase() + "=? WHERE UUID=?");
            p.setString(2, uuid.toString());
            p.setInt(1, lvl);
            p.execute();
            p.close();
            QuestLevelUpEvent questLevelUpEvent = new QuestLevelUpEvent(Bukkit.getPlayer(uuid), lvl, quest, getExperience(uuid, quest));
            Bukkit.getPluginManager().callEvent(questLevelUpEvent);
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
    public int getLevel(UUID uuid, String quest){
        try {
            PreparedStatement p = FantasyWar.mysql.getConnection().prepareStatement("SELECT * FROM Quests WHERE UUID=?");
            p.setString(1, uuid.toString());
            ResultSet rs = p.executeQuery();
            if (rs.next()){
                return rs.getInt(quest.toUpperCase());
            }

        } catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }


}
