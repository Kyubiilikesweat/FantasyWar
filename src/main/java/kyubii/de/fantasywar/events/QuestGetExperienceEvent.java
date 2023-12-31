package kyubii.de.fantasywar.events;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.QuestMySQL;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class QuestGetExperienceEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private QuestMySQL utils = new QuestMySQL();
    private boolean isCancelled;
    private final int exp;
    private final int level;
    private final Player player;
    private final String quest;

    public QuestGetExperienceEvent(Player player, String quest, int exp, int level){
        this.player = player;
        this.exp = exp;
        this.level = level;
        this.isCancelled = false;
        this.quest = quest;
    }
    public boolean isReadyToLvlUp(){
        boolean b = false;
        if (quest.equalsIgnoreCase("Farmer")){
            int expToLvlUp = getFarmerExpToLvlUp(level);
            if (exp >= expToLvlUp){
                b = true;
            }
        }
        if (quest.equalsIgnoreCase("Bauarbeiter")){
            int expToLvlUp = getBauarbeiterExpToLvlUp(level);
            if (exp >= expToLvlUp){
                b = true;
            }
        }
        return b;
    }
    public void LevelUp(){
        int lvl = level + 1;
        utils.setLevel(player.getUniqueId(), quest, lvl);
    }
    public int getExp(){
        return exp;
    }
    public int getLevel(){
        return level;
    }
    public Player getPlayer(){
        return player;
    }
    @Override
    public boolean isCancelled() {
        return isCancelled;
    }

    @Override
    public void setCancelled(boolean b) {
        this.isCancelled = b;
    }

    @NotNull
    @Override
    public HandlerList getHandlers() {
        return HANDLERS;
    }
    public static HandlerList getHandlerList(){
        return HANDLERS;
    }

    private int getBauarbeiterExpToLvlUp(int lvl){
        return lvl * 100;
    }
    private int getFarmerExpToLvlUp(int lvl){
        return lvl * 40;
    }

}
