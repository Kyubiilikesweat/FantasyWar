package kyubii.de.fantasywar.utils;

import kyubii.de.fantasywar.FantasyWar;

import java.sql.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

public class WerbungMySQL {

    public static void createWerbungTable() throws SQLException {

        try (Statement stmt = FantasyWar.mysql.getConnection().createStatement()) {

            String createTableSQL = "CREATE TABLE IF NOT EXISTS Werbung (" +
                    "UUID VARCHAR(36) PRIMARY KEY," +
                    "COOLDOWN INT DEFAULT 0" +
                    ")";
            stmt.executeUpdate(createTableSQL);

        }
    }

    public static void addPlayer(UUID uuid) throws SQLException {
        if (!playerExists(uuid)) {
            try (PreparedStatement stmt = FantasyWar.mysql.getConnection().prepareStatement(
                    "INSERT INTO Werbung (UUID) VALUES (?)")) {
                stmt.setString(1, uuid.toString());
                stmt.executeUpdate();
            }
        }
    }

    public static boolean playerExists(UUID uuid) throws SQLException{
        try (PreparedStatement stmt = FantasyWar.mysql.getConnection().prepareStatement(
                "SELECT COUNT(UUID) AS count FROM Werbung WHERE UUID = ?")){
            stmt.setString(1, uuid.toString());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()){
                int count = rs.getInt("count");
                return count > 0;
            }
        }
        return false;
    }

    public static void startUpdatingTimes(){
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try {
                    updateTime();
                } catch (SQLException e){
                    e.printStackTrace();
                }
            }
        };
        timer.scheduleAtFixedRate(task, 1000, 1000);
    }

    private static void updateTime() throws SQLException {
        try (PreparedStatement stmt = FantasyWar.mysql.getConnection().prepareStatement(
                "UPDATE Werbung SET COOLDOWN = GREATEST(0, COOLDOWN -1) WHERE COOLDOWN IS NOT NULL")){
            stmt.executeUpdate();
        }
    }

    public static void addTime(UUID uuid, int seconds) throws SQLException {
        try (PreparedStatement stmt = FantasyWar.mysql.getConnection().prepareStatement(
                "UPDATE Werbung SET COOLDOWN = ? WHERE UUID = ?" )){
            stmt.setInt(1, seconds);
            stmt.setString(2, uuid.toString());
            stmt.executeUpdate();
        }
    }

    public static int getTime(UUID uuid) throws SQLException {
        try (PreparedStatement stmt = FantasyWar.mysql.getConnection().prepareStatement(
                "SELECT COOLDOWN FROM Werbung WHERE UUID = ?")){
            stmt.setString(1, uuid.toString());
            try (ResultSet resultSet = stmt.executeQuery()){
                if (resultSet.next()){
                    int seconds = resultSet.getInt("COOLDOWN");
                    return seconds;
                }
            }
        }
        return 0;
    }
}
