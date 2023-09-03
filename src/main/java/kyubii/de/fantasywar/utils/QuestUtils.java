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
        ItemStack bauarbeiterItem = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayname("§9Bauarbeiter-Quest").setLore(
                        "§7" + utils.getLevel(uuid, "Bauarbeiter") + "§8/§710 §6LEVEL",
                        "§7" + utils.getExperience(uuid, "Bauarbeiter") + "§8/§7" + getBauarbeiterExpToLvlUp(utils.getLevel(uuid, "Bauarbeiter")) + " §6BLÖCKE")
                .build();
        ItemStack farmerItem = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayname("§9Farmer-Quest").setLore(
                        "§7" + utils.getLevel(uuid, "Farmer") + "§8/§710 §6LEVEL",
                        "§7" + utils.getExperience(uuid, "Farmer") + "§8/§7" + getFarmerExpToLvlUp(utils.getLevel(uuid, "Farmer")) + " §6ERNTE")
                .build();
        ItemStack CommingSoonItem = new ItemBuilder(Material.WRITTEN_BOOK).setDisplayname("§cCOMMING SOON...")
                .build();
        inv.setItem(3, bauarbeiterItem);
        inv.setItem(5, farmerItem);

        return inv;
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
