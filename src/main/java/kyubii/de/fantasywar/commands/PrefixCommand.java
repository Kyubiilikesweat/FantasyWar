package kyubii.de.fantasywar.commands;

import com.iridium.iridiumcolorapi.IridiumColorAPI;
import me.neznamy.tab.api.TabAPI;
import me.neznamy.tab.api.TabPlayer;
import me.neznamy.tab.api.nametag.NameTagManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class PrefixCommand implements CommandExecutor {
    @Override @SuppressWarnings("all")
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        if (commandSender instanceof Player){
            Player player = (Player) commandSender;
            String name = IridiumColorAPI.process("<RAINBOW1>" + player.getDisplayName() + "</RAINBOW>");

            player.setDisplayName(name);
            player.setPlayerListName(name);
            TabPlayer tabPlayer = TabAPI.getInstance().getPlayer(player.getUniqueId());
            String prefix = IridiumColorAPI.process("<RAINBOW1>" + TabAPI.getInstance().getNameTagManager().getOriginalPrefix(tabPlayer) + "</RAINBOW>");
            TabAPI.getInstance().getTabListFormatManager().setName(tabPlayer, name);
            TabAPI.getInstance().getNameTagManager().setPrefix(tabPlayer, prefix);
        }

        return true;
    }
}
