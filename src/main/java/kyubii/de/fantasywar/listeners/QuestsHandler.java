package kyubii.de.fantasywar.listeners;

import kyubii.de.fantasywar.events.QuestGetExperienceEvent;
import kyubii.de.fantasywar.utils.QuestMySQL;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.util.ArrayList;
import java.util.List;

public class QuestsHandler implements Listener {
    QuestMySQL questMySQL = new QuestMySQL();

    @EventHandler
    public void onQuestGetExperience(QuestGetExperienceEvent event){
        if (event.getLevel() == 10){
            event.setCancelled(true);
            return;
        }
        if (event.isReadyToLvlUp()){
            event.LevelUp();
        }
    }


    //Miner
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event){
        Player player = event.getPlayer();
        if (!player.getWorld().getName().equalsIgnoreCase("Farmwelt-1")){
            for (Material mat : getMinerBlocks()){
                if (mat == event.getBlock().getType()){
                    int exp = questMySQL.getExperience(player.getUniqueId(), "Miner");
                    questMySQL.setExperience(player.getUniqueId(), "Miner", exp + 1);
                    if (mat == Material.DIAMOND_ORE || mat == Material.DEEPSLATE_DIAMOND_ORE){
                        questMySQL.setExperience(player.getUniqueId(), "Miner", exp + 1);
                    }
                }
            }
        }
    }
    private List<Material> getMinerBlocks(){
        List<Material> matList = new ArrayList<>();
        matList.add(Material.STONE);
        matList.add(Material.COBBLESTONE);
        matList.add(Material.DEEPSLATE);
        matList.add(Material.COBBLED_DEEPSLATE);
        matList.add(Material.COAL_ORE);
        matList.add(Material.COPPER_ORE);
        matList.add(Material.DIAMOND_ORE);
        matList.add(Material.REDSTONE_ORE);
        matList.add(Material.GOLD_ORE);
        matList.add(Material.ANDESITE);
        matList.add(Material.LAPIS_ORE);
        matList.add(Material.IRON_ORE);
        matList.add(Material.COAL_ORE);
        matList.add(Material.DEEPSLATE_COAL_ORE);
        matList.add(Material.DEEPSLATE_COPPER_ORE);
        matList.add(Material.DEEPSLATE_IRON_ORE);
        matList.add(Material.DEEPSLATE_REDSTONE_ORE);
        matList.add(Material.DEEPSLATE_LAPIS_ORE);
        matList.add(Material.DEEPSLATE_DIAMOND_ORE);
        matList.add(Material.DIORITE);
        matList.add(Material.DEEPSLATE_EMERALD_ORE);
        matList.add(Material.DEEPSLATE_GOLD_ORE);
        matList.add(Material.NETHERRACK);
        matList.add(Material.NETHER_GOLD_ORE);
        matList.add(Material.NETHER_QUARTZ_ORE);
        return matList;
    }

}
