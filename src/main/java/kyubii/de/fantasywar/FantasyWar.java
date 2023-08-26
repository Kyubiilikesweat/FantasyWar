package kyubii.de.fantasywar;

import kyubii.de.fantasywar.commands.*;
import kyubii.de.fantasywar.listeners.InventoryHandler;
import kyubii.de.fantasywar.listeners.PlayerjoinHandler;
import kyubii.de.fantasywar.listeners.QuestsHandler;
import kyubii.de.fantasywar.utils.MySQLConnect;
import kyubii.de.fantasywar.configs.WarpsConfig;
import kyubii.de.fantasywar.utils.QuestMySQL;
import kyubii.de.fantasywar.utils.WerbungMySQL;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/*
Author - Kyubiilikesweat
 */
public final class FantasyWar extends JavaPlugin {
    private static FantasyWar instance;
    private static Economy econ = null;
    public static MySQLConnect mysql;
    private final WarpsConfig warpsConfig = new WarpsConfig();
    private static List<String> questsList = new ArrayList<>();

    @Override()
    public void onEnable() {
        instance = this;
        getConfig().options().copyDefaults(true);
        saveConfig();
        loadConfig();
        loadMySQL();
        createTable();
        loadCommands();
        loadListeners();
        if (!setupEconomy()){
            Bukkit.getConsoleSender().sendMessage("§cEs wurde kein Vault gefunden!");
        }
        try {
            QuestMySQL.createQuestTable();
            WerbungMySQL.createWerbungTable();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


        Bukkit.getConsoleSender().sendMessage("§3§lFANTASYWAR-SYSTEM §7- §8Wurde §aaktiviert");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage("§3§lFANTASYWAR-SYSTEM §7- §8Wurde §cdeaktiviert");
    }
    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return true;
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
        getCommand("Werbung").setExecutor(new WerbungCommand());
        getCommand("Quest").setExecutor(new QuestCommand());
    }
    public void loadListeners() {
        Bukkit.getPluginManager().registerEvents(new InventoryHandler(), this);
        Bukkit.getPluginManager().registerEvents(new PlayerjoinHandler(), this);
        Bukkit.getPluginManager().registerEvents(new QuestsHandler(), this);
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
    public void loadMySQL() {
        mysql = new MySQLConnect(
                getConfig().getString("MySQL." + "host"),
                getConfig().getInt("MySQL." + "port"),
                getConfig().getString("MySQL." + "database"),
                getConfig().getString("MySQL." + "user"),
                getConfig().getString("MySQL." + "password"));
    }

    public static FantasyWar getInstance() {return instance;}
    public static String getQuestPrefix(String quest){return "§3§l" + quest +" §8» §7";}
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
    public static List<String> getQuests() {
        questsList.add("Miner");
        questsList.add("Laeufer");
        questsList.add("Farmer");
        questsList.add("Bauarbeiter");
        questsList.add("Schwimmer");
        return questsList;
    }

    public static void createTable() {
        try {
            PreparedStatement st = mysql.getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS playerinfo(player_uuid varchar(64))");
            st.execute();
            st.close();
        } catch (SQLException e) {
            Bukkit.getConsoleSender().sendMessage("§cEs ist ein Fehler aufgetreten! Bitte kontaktiere einen Entwickler");
        }
    }

}
