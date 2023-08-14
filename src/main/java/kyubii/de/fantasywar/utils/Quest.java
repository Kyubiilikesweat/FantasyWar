package kyubii.de.fantasywar.utils;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.entity.Ageable;
import org.bukkit.entity.Player;

public class Quest {
    private final QuestUtils utils = new QuestUtils();
    private final String questName;
    private final Player player;
    public Quest(String questName, Player player){
        this.questName = questName;
        this.player = player;

    }

    public String getQuestName() {
        return questName;
    }
    public Player getQuestPlayer(){
        return player;
    }
    public Integer getLevel(){
        return utils.getLevel(player.getUniqueId(), questName);
    }
    public Integer getExp(){
        return utils.getExp(player.getUniqueId(), questName);
    }
    public Integer getExpToLvlUp(){
        return utils.getExpToLvlUp(player.getUniqueId(), questName);
    }
    public Integer getReward(){
        return utils.getReward(player.getUniqueId(), questName);
    }


    private boolean Wheat(Block wheat){
        if (wheat.getType() == Material.WHEAT){
            BlockState blockState = wheat.getState();
            if (blockState.getBlockData() instanceof Ageable){
                Ageable ageable = (Ageable) blockState.getBlockData();
                return ageable.getAge() == 3;
            }
        }
        return false;
    }
}
