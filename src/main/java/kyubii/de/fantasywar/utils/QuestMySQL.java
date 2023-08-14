package kyubii.de.fantasywar.utils;

import kyubii.de.fantasywar.FantasyWar;

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
                    "Miner INT DEFAULT 0," +
                    "Laeufer INT DEFAULT 0," +
                    "Bauarbeiter INT DEFAULT 0," +
                    "Farmer INT DEFAULT 0," +
                    "Schwimmer INT DEFAULT 0," +
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
        try (PreparedStatement stmt = FantasyWar.mysql.getConnection().prepareStatement(
                "INSTERT INTO Quests (UUID) VALUES (?)")){
            stmt.setString(1, uuid.toString());
            stmt.executeUpdate();
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
}
