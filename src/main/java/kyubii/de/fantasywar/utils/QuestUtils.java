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
        Inventory inv;
        inv = Bukkit.createInventory(null, 9, "§7Quests");
        ItemStack minerItem = new ItemBuilder(Material.LEGACY_BOOK_AND_QUILL).setDisplayname("&9Miner-Quest").setLore(
                        "§7" + utils.getLevel(uuid, "Miner") + "§8/§710 §6LEVEL",
                        "§7" + utils.getExperience(uuid, "Minder") + "§8/§7" + getMinerExpToLvlUp(utils.getLevel(uuid, "Miner")) + " §6BLÖCKE")
                .build();
        inv.setItem(0, minerItem);
        return inv;
    }
    public int getMinerExpToLvlUp(int lvl){
        return lvl * 1250;
    }
}
