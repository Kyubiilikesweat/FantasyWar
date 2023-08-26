package kyubii.de.fantasywar.utils;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

@SuppressWarnings("all")
public class QuestUtils {
    QuestMySQL utils = new QuestMySQL();
    public Inventory getQuestInventory(UUID uuid){
        Inventory inv = Bukkit.createInventory(null, 9, "§7Quests");

        ItemStack minerItem = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayname("§9Miner-Quest").setLore(
                        "§7" + utils.getLevel(uuid, "Miner") + "§8/§710 §6LEVEL",
                        "§7" + utils.getExperience(uuid, "Miner") + "§8/§7" + getMinerExpToLvlUp(utils.getLevel(uuid, "Miner")) + " §6BLÖCKE",
                        "§8(§cINFO: Nur Gestein u.ä wird gezählt§8)")
                .build();
        ItemStack laeuferItem = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayname("§9Läufer-Quest").setLore(
                        "§7" + utils.getLevel(uuid, "Laeufer") + "§8/§710 §6LEVEL",
                        "§7" + utils.getExperience(uuid, "Laeufer") + "§8/§7" + getLaeuferExpToLvlUp(utils.getLevel(uuid, "Laeufer")) + " §6METER")
                .build();
        ItemStack bauarbeiterItem = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayname("§9Bauarbeiter-Quest").setLore(
                        "§7" + utils.getLevel(uuid, "Bauarbeiter") + "§8/§710 §6LEVEL",
                        "§7" + utils.getExperience(uuid, "Bauarbeiter") + "§8/§7" + getBauarbeiterExpToLvlUp(utils.getLevel(uuid, "Bauarbeiter")) + " §6BLÖCKE")
                .build();
        ItemStack farmerItem = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayname("§9Farmer-Quest").setLore(
                        "§7" + utils.getLevel(uuid, "Farmer") + "§8/§710 §6LEVEL",
                        "§7" + utils.getExperience(uuid, "Farmer") + "§8/§7" + getFarmerExpToLvlUp(utils.getLevel(uuid, "Farmer")) + " §6ERNTE")
                .build();
        ItemStack schwimmerItem = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayname("§9Schwimmer-Quest").setLore(
                        "§7" + utils.getLevel(uuid, "Schwimmer") + "§8/§710 §6LEVEL",
                        "§7" + utils.getExperience(uuid, "Schwimmer") + "§8/§7" + getSchwimmerExpToLvlUp(utils.getLevel(uuid, "Schwimmer")) + " §6METER")
                .build();
        ItemStack CommingSoonItem = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayname("§cCOMMING SOON...")
                .build();
        inv.setItem(0, minerItem);
        inv.setItem(1, laeuferItem);
        inv.setItem(2, bauarbeiterItem);
        inv.setItem(3, farmerItem);
        inv.setItem(4, CommingSoonItem);

        return inv;
    }
    private int getMinerExpToLvlUp(int lvl){
        return lvl * 80;
    }
    private int getLaeuferExpToLvlUp(int lvl){
        return lvl * 300;
    }
    private int getBauarbeiterExpToLvlUp(int lvl){
        return lvl * 100;
    }
    private int getFarmerExpToLvlUp(int lvl){
        return lvl * 70;
    }
    private int getSchwimmerExpToLvlUp(int lvl){
        return lvl * 150;
    }

}
