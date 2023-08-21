package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.utils.QuestUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class QuestCommand implements CommandExecutor {
    QuestUtils utils = new QuestUtils();
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            player.openInventory(utils.getQuestInventory(player.getUniqueId()));
        }

        return true;
    }
}
