package kyubii.de.fantasywar.utils;

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


}
