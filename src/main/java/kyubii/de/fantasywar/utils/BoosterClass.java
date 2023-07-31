package kyubii.de.fantasywar.utils;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class BoosterClass {
    public static void AddBooster(Player p, int anzahl) {
        File file = new File("plugins/FantasyWar/Booster.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getString(p.getUniqueId() + ".Booster") == null) {
            cfg.set(p.getUniqueId() + ".Booster", Integer.valueOf(0));
            try {
                cfg.save(file);
            } catch (IOException ignored) {}
            int i = cfg.getInt(p.getUniqueId() + ".Booster");
            int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Booster", Integer.valueOf(total));
            try {
                cfg.save(file);
            } catch (IOException ignored) {}
        } else {
            try {
                cfg.save(file);
            } catch (IOException ignored) {}
            int i = cfg.getInt(p.getUniqueId() + ".Booster");
            int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Booster", total);
            try {
                cfg.save(file);
            } catch (IOException ignored) {}
        }
    }

    public static int getBooster(Player p) {
        File file = new File("plugins/BoosterSystem/Booster.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int i = 0;
        if (cfg.getString(p.getUniqueId() + ".Booster") == null) {
            i = 0;
        } else {
            i = cfg.getInt(p.getUniqueId() + ".Booster");
        }
        return i;
    }

    public static void RemoveBooster(Player p, int anzahl) {
        File file = new File("plugins/BoosterSystem/Booster.yml");
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getString(p.getUniqueId() + ".Booster") == null) {
            cfg.set(p.getUniqueId() + ".Booster", 0);
            try {
                cfg.save(file);
            } catch (IOException ignored) {}
            int i = cfg.getInt(p.getUniqueId() + ".Booster");
            int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Booster", Integer.valueOf(total));
            try {
                cfg.save(file);
            } catch (IOException ignored) {}
        } else {
            try {
                cfg.save(file);
            } catch (IOException ignored) {}
            int i = cfg.getInt(p.getUniqueId() + ".Booster");
            int total = i - anzahl;
            cfg.set(p.getUniqueId() + ".Booster", Integer.valueOf(total));
            try {
                cfg.save(file);
            } catch (IOException ioException) {}
        }
    }
}