package kyubii.de.fantasywar.utils;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnect {
    private Connection con;
    private final String host;
    private final int port;
    private final String database;
    private final String user;
    private final String password;
    public MySQLConnect(String host, Integer port, String database, String user, String password){
        this.database = database;
        this.host = host;
        this.user = user;
        this.port = port;
        this.password = password;
        connect();
        Bukkit.getConsoleSender().sendMessage("§aVerbindung zur §eDatebank §akonnte hergestellt werden!");
    }
    public void connect(){
        try {
            con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
        } catch (SQLException e) {
            e.printStackTrace();
            Bukkit.getConsoleSender().sendMessage("§cDie Verbindung zur Datenbank konnte nicht hergestellt werden!");
        }
    }
    public boolean hasConnection(){
        return this.con != null;
    }
    public Connection getConnection(){
        if (hasConnection()) {
            return this.con;
        }
        Bukkit.getConsoleSender().sendMessage("§cEs wurde keine Connection gefunden!");
        return null;
    }
}
