package kyubii.de.fantasywar;

import kyubii.de.fantasywar.commands.*;
import kyubii.de.fantasywar.listeners.PlayerjoinHandler;
import kyubii.de.fantasywar.utils.MySQLConnect;
import kyubii.de.fantasywar.utils.WarpsConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
Author - Kyubiilikesweat
 */
public final class FantasyWar extends JavaPlugin {
    private static FantasyWar instance;
    public static MySQLConnect mysql;
    private final WarpsConfig warpsConfig = new WarpsConfig();

    @Override
    public void onEnable() {
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            instance = this;
            getConfig().options().copyDefaults(true);
            saveConfig();
            loadConfig();
            loadMySQL();
            createTable();
            loadCommands();
            loadListeners();

            Bukkit.getConsoleSender().sendMessage("§3§lFANTASYWAR-SYSTEM §7- §8Wurde §aaktiviert");
        }else {
            Bukkit.getConsoleSender().sendMessage("§cPlaceholderAPI wurde nicht gefunden!");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§3§lFANTASYWAR-SYSTEM §7- §8Wurde §cdeaktiviert");
    }

    @SuppressWarnings("all")
    public void loadCommands() {
        getCommand("Sign").setExecutor(new SignCommand());
        getCommand("Broadcast").setExecutor(new BroadcastCommand());
        getCommand("Kopf").setExecutor(new KopfCommand());
        getCommand("Chatclear").setExecutor(new ChatclearCommand());
        getCommand("Fantasywarp").setExecutor(new FantasywarpCommand());
        getCommand("Createfantasywarp").setExecutor(new CreateFantasyrwarpCommand());
        getCommand("Deletefantasywarp").setExecutor(new DeletewarpCommand());
        getCommand("Fantasywarpinfo").setExecutor(new FantasywarpInfoCommand());
    }
    public void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new PlayerjoinHandler(), this);
    }

    public void loadConfig() {
        getConfig().addDefault("MySQL." + "host", "localhost");
        getConfig().addDefault("MySQL." + "port", 3306);
        getConfig().addDefault("MySQL." + "database", "minecraft");
        getConfig().addDefault("MySQL." + "user", "minecraft");
        getConfig().addDefault("MySQL." + "password", "osr4MegN3CqDai66");
        saveConfig();

        warpsConfig.setUp();
        warpsConfig.save();
    }

    public static FantasyWar getInstance() {return instance;}
    public static String getSystemPrefix() {return "§3§lSYSTEM §8» §7";}
    public static String getNoPerm() {return "§3§lSYSTEM §8» §cDazu hast du keine Rechte!";}
    public static String getNoPlayer() {return "§3§lSYSTEM §8» §cDu musst ein Spieler sein!";}

    /*
    Permissions
     */
    public static String getBroadcastPerm() {return "broadcast.use";}
    public static String getChatclearPerm() {return "chatclear.use";}
    public static String getKopfPerm() {return "kopf.use";}
    public static String getItemsignPerm() {return "itemsign.use";}


    public static void createTable() {
        try {
            PreparedStatement st = mysql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerinfo(player_uuid varchar(64))");
            st.execute();
            st.close();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§cEs ist ein Fehler aufgetreten! Bitte kontaktiere einen Entwickler");
        }
    }
    public void loadMySQL() {
        mysql = new MySQLConnect(
                getConfig().getString("MySQL." + "host"),
                getConfig().getInt("MySQL." + "port"),
                getConfig().getString("MySQL." + "database"),
                getConfig().getString("MySQL." + "user"),
                getConfig().getString("MySQL." + "password"));
    }
}
