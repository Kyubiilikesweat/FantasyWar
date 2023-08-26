package kyubii.de.fantasywar.listeners;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.events.QuestGetExperienceEvent;
import kyubii.de.fantasywar.events.QuestLevelUpEvent;
import kyubii.de.fantasywar.utils.QuestMySQL;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.RegisteredServiceProvider;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class QuestsHandler implements Listener {
    RegisteredServiceProvider<Economy> rsp = Bukkit.getServer().getServicesManager().getRegistration(Economy.class);
    QuestMySQL questMySQL = new QuestMySQL();
    Economy economy = rsp.getProvider();
    HashMap<Player, Boolean> isMoving = new HashMap<>();
    HashMap<Player, Boolean> isSwimming = new HashMap<>();
    HashMap<Player, Location> saveLoc = new HashMap<>();
    HashMap<Player, Location> saveSwimLoc = new HashMap<>();


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
        if (event.getLevel() == 11){
            event.setCancelled(true);
            return;
        }
        if (event.getLevel() == 10){
            Bukkit.broadcastMessage(FantasyWar.getSystemPrefix() + "Der Spieler " + player.getDisplayName() + " Hat Level §910 §7in der §9" + event.getQuest() + "-Quest §7erreicht§8!");
        }
        if (event.getQuest().equalsIgnoreCase("Miner")) {
            int reward = getMinerReward(event.getLevel());
            EconomyResponse economyResponse = economy.depositPlayer(player, reward);
            if (economyResponse.transactionSuccess()){
                player.sendMessage(FantasyWar.getQuestPrefix("Miner") + "Du hast Level §9" + event.getLevel() + " §7erreicht§8! §7Du hast §c" + reward + " Fantys §7erhalten§8.");
            }
            return;
        }
        if (event.getQuest().equalsIgnoreCase("Laeufer")) {
            int reward = getLaeuferReward(event.getLevel());
            EconomyResponse economyResponse = economy.depositPlayer(player, reward);
            if (economyResponse.transactionSuccess()){
                player.sendMessage(FantasyWar.getQuestPrefix("Läufer") + "Du hast Level §9" + event.getLevel() + " §7erreicht§8! §7Du hast §c" + reward + " Fantys §7erhalten§8.");
            }return;
        }
        if (event.getQuest().equalsIgnoreCase("Bauarbeiter")) {
            int reward = getBauarbeiterReward(event.getLevel());
            EconomyResponse economyResponse = economy.depositPlayer(player, reward);
            if (economyResponse.transactionSuccess()){
                player.sendMessage(FantasyWar.getQuestPrefix("Bauarbeiter") + "Du hast Level §9" + event.getLevel() + " §7erreicht§8! §7Du hast §c" + reward + " Fantys §7erhalten§8.");
            }return;
        }
        if (event.getQuest().equalsIgnoreCase("Schwimmer")) {
            int reward = getSchwimmerReward(event.getLevel());
            EconomyResponse economyResponse = economy.depositPlayer(player, reward);
            if (economyResponse.transactionSuccess()){
                player.sendMessage(FantasyWar.getQuestPrefix("Schwimmer") + "Du hast Level §9" + event.getLevel() + " §7erreicht§8! §7Du hast §c" + reward + " Fantys §7erhalten§8.");
            }return;
        }
        if (event.getQuest().equalsIgnoreCase("Farmer")) {
            int reward = getFarmerReward(event.getLevel());
            EconomyResponse economyResponse = economy.depositPlayer(player, reward);
            if (economyResponse.transactionSuccess()){
                player.sendMessage(FantasyWar.getQuestPrefix("Farmer") + "Du hast Level §9" + event.getLevel() + " §7erreicht§8! §7Du hast §c" + reward + " Fantys §7erhalten§8.");
            }
        }
        
    }
    private int getMinerReward(int level){
        return level * 120;
    }
    private int getFarmerReward(int level){
        return level * 100;
    }
    private int getLaeuferReward(int level){
        return level * 50;
    }
    private int getBauarbeiterReward(int level){
        return level * 100;
    }
    private int getSchwimmerReward(int level){
        return level * 90;
    }



    //Miner
    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();
        if (isFullyGrown(event.getBlock())) {
            int exp = questMySQL.getExperience(player.getUniqueId(), "Farmer");
            questMySQL.setExperience(player.getUniqueId(), "Farmer", exp + 1);
        }
        if (player.getWorld().getName().equalsIgnoreCase("Farmwelt-1")) {
            for (Material mat : getMinerBlocks()) {
                if (mat == event.getBlock().getType()) {
                    int exp = questMySQL.getExperience(player.getUniqueId(), "Miner");
                    if (mat == Material.DIAMOND_ORE || mat == Material.DEEPSLATE_DIAMOND_ORE || mat == Material.NETHERITE_BLOCK) {
                        questMySQL.setExperience(player.getUniqueId(), "Miner", exp + 2);
                        return;
                    }
                    questMySQL.setExperience(player.getUniqueId(), "Miner", exp + 1);
                }
            }
        }
    }

    private List<Material> getMinerBlocks() {
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
        matList.add(Material.NETHERITE_BLOCK);
        matList.add(Material.NETHER_GOLD_ORE);
        matList.add(Material.NETHER_QUARTZ_ORE);
        return matList;
    }


    //läufer
    @EventHandler
    public void onWalk(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        if (player.getGameMode() == GameMode.CREATIVE){
            return;
        }
        if (!player.getWorld().getName().equalsIgnoreCase("Farmwelt-1")) {
            return;
        }
        if (isMoving.containsKey(player)) {
            if (isMoving.get(player)) {
                return;
            }
        }
        if (player.getLocation().getBlock().isLiquid()){
            return;
        }
        saveLoc.put(player, player.getLocation());
        isMoving.put(player, true);
        Bukkit.getScheduler().scheduleSyncDelayedTask(FantasyWar.getInstance(), new Runnable() {
            @Override
            public void run() {
                isMoving.put(player, false);
                Location newLoc = player.getLocation();
                int distance = (int) saveLoc.get(player).distance(newLoc);
                int distanceBefore = questMySQL.getExperience(player.getUniqueId(), "Laeufer");
                if (distance >= 5) {
                    questMySQL.setExperience(player.getUniqueId(), "Laeufer", distance + distanceBefore);
                }
            }
        }, 100);
    }


    //Bauarbeiter
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event) {
        Player player = event.getPlayer();
        int expbefore = questMySQL.getExperience(player.getUniqueId(), "Bauarbeiter");
        questMySQL.setExperience(player.getUniqueId(), "Bauarbeiter", expbefore + 1);
    }

    //@EventHandler
    //public void onSwimming(PlayerMoveEvent event){
      //  Player player = event.getPlayer();
        /*if (!player.getWorld().getName().equalsIgnoreCase("Farmwelt-1")) {
            return;
        }*/
        /*if (isSwimming.containsKey(player)) {
            if (isSwimming.get(player)) {
                return;
            }
        }
        isSwimming.put(player, true);
        saveSwimLoc.put(player, player.getLocation());
        Bukkit.getScheduler().scheduleSyncDelayedTask(FantasyWar.getInstance(), new Runnable() {
            @Override
            public void run() {
                if (player.getLocation().getBlock().isLiquid()) {
                    Location newLoc = player.getLocation();
                    isSwimming.put(player, false);
                    int distance = (int) saveSwimLoc.get(player).distance(newLoc);
                    int distanceBefore = questMySQL.getExperience(player.getUniqueId(), "Schwimmer");
                    if (distance >= 5) {

                        questMySQL.setExperience(player.getUniqueId(), "Schwimmer", distance + distanceBefore);
                    }
                }
            }
        }, 100);
    }*/


    private boolean isFullyGrown(Block block) {
        BlockData bdata = block.getBlockData();
        if (bdata instanceof Ageable) {
            Ageable age = (Ageable) bdata;
            return age.getAge() == age.getMaximumAge();
        }
        return false;
    }
}
