package kyubii.de.fantasywar.listeners;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.events.QuestGetExperienceEvent;
import kyubii.de.fantasywar.events.QuestLevelUpEvent;
import kyubii.de.fantasywar.utils.QuestMySQL;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.plugin.RegisteredServiceProvider;


public class QuestsHandler implements Listener {
    RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
    QuestMySQL questMySQL = new QuestMySQL();
    Economy economy = rsp.getProvider();



    @EventHandler
    public void onQuestGetExperience(QuestGetExperienceEvent event) {
        if (event.getLevel() == 10) {
            event.setCancelled(true);
            return;
        }
        if (event.isReadyToLvlUp()) {
            event.LevelUp();
        }
    }

    @EventHandler
    public void onLevelUp(QuestLevelUpEvent event) {
        Player player = event.getPlayer();
        if (event.getLevel() == 11) {
            event.setCancelled(true);
            return;
        }
        if (event.getLevel() == 10) {
            Bukkit.broadcastMessage(FantasyWar.getSystemPrefix() + "Der Spieler " + player.getDisplayName() + " Hat Level §910 §7in der §9" + event.getQuest() + "-Quest §7erreicht§8!");
        }
        if (event.getQuest().equalsIgnoreCase("Bauarbeiter")) {
            int reward = getBauarbeiterReward(event.getLevel());
            EconomyResponse economyResponse = economy.depositPlayer(player, reward);
            if (economyResponse.transactionSuccess()) {
                player.sendMessage(FantasyWar.getQuestPrefix("Bauarbeiter") + "Du hast Level §9" + event.getLevel() + " §7erreicht§8! §7Du hast §c" + reward + " Fantys §7erhalten§8.");
            }
            return;
        }
        if (event.getQuest().equalsIgnoreCase("Farmer")) {
            int reward = getFarmerReward(event.getLevel());
            EconomyResponse economyResponse = economy.depositPlayer(player, reward);
            if (economyResponse.transactionSuccess()) {
                player.sendMessage(FantasyWar.getQuestPrefix("Farmer") + "Du hast Level §9" + event.getLevel() + " §7erreicht§8! §7Du hast §c" + reward + " Fantys §7erhalten§8.");
            }
        }

    }

    private int getFarmerReward(int level) {
        return level * 100;
    }
    private int getBauarbeiterReward(int level) {
        return level * 100;
    }



    //Miner
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (isFullyGrown(event.getBlock())) {
            int exp = questMySQL.getExperience(player.getUniqueId(), "Farmer");
            questMySQL.setExperience(player.getUniqueId(), "Farmer", exp + 1);
        }
    }


    //Bauarbeiter
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        int expbefore = questMySQL.getExperience(player.getUniqueId(), "Bauarbeiter");
        questMySQL.setExperience(player.getUniqueId(), "Bauarbeiter", expbefore + 1);
    }


    private boolean isFullyGrown(Block block) {
        BlockData bdata = block.getBlockData();
        if (bdata instanceof Ageable) {
            Ageable age = (Ageable) bdata;
            return age.getAge() == age.getMaximumAge();
        }
        return false;
    }
}
