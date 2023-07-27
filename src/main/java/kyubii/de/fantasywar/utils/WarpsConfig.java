package kyubii.de.fantasywar.utils;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class WarpsConfig {
    private static File file;
    private static FileConfiguration customFile;

    public void setUp(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("FantasyWar").getDataFolder(), "warps.yml");

        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
        customFile = YamlConfiguration.loadConfiguration(file);
    }
    public FileConfiguration get(){
        return customFile;
    }
    public void save(){
        try {
            customFile.save(file);
        }catch (IOException e){
            System.out.println("Datei konnte nicht gespeichert werden!");
        }
    }
    public  void reload(){
        customFile = YamlConfiguration.loadConfiguration(file);
    }
}
