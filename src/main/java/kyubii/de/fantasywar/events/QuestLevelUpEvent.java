package kyubii.de.fantasywar.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;
import org.jetbrains.annotations.NotNull;

public class QuestLevelUpEvent extends Event implements Cancellable {
    private static final HandlerList HANDLERS = new HandlerList();
    private boolean isCancelled;
    private final Player player;
    private final int level;
    private final String quest;
    private final int exp;

    public QuestLevelUpEvent(Player player, int level, String quest, int exp) {
        this.isCancelled = false;
        this.player = player;
        this.level = level;
        this.quest = quest;
        this.exp = exp;

    }

    public int getExp() {
        return exp;
    }

    public String getQuest() {
        return quest;
    }

    public int getLevel() {
        return level;
    }

    public Player getPlayer() {
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

    public static HandlerList getHandlerList() {
        return HANDLERS;
    }
}
