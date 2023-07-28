package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.PlayerWarpUtils;
import net.md_5.bungee.api.chat.*;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.List;

public class FantasywarpCommand implements CommandExecutor, TabCompleter {
    PlayerWarpUtils warpUtils = new PlayerWarpUtils();
    public static HashMap<Player, Boolean> warpCooldownMap = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)){
            sender.sendMessage(FantasyWar.getNoPlayer());
            return true;
        }
        Player player =(Player) sender;
        if (args.length == 0){
            if (warpUtils.getWarpList().isEmpty()){
                player.sendMessage(FantasyWar.getSystemPrefix() + "Zurzeit gibt es keine §9Fantasywarps§8.");
                return true;
            }
            player.sendMessage(FantasyWar.getSystemPrefix() + "Zurzeit gibt es folgende§9 Warps§8:");
            for (String warps : warpUtils.getWarpList()){
                TextComponent textComponent = new TextComponent("§7- §9" + warps);
                textComponent.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/fantasywarp " + warps));
                textComponent.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§7Klicke, um dich zu diesem §9Warp §7zu teleportieren")));
                textComponent.addExtra("");
                player.spigot().sendMessage(textComponent);
            }
            return true;
        }
        String warp = args[0];
        if (!warpUtils.warpExists(warp)){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Dieser §9Warp §7existiert nicht§8.");
            return true;
        }
        if (warpCooldownMap.containsKey(player)) {
            if (warpCooldownMap.get(player)) {
                player.sendMessage(FantasyWar.getSystemPrefix() + "Warte noch etwas bevor du dich nochmal Teleportierst");
                return true;
            }
        }

        Location warpLoc = warpUtils.getWarpLocation(warp);
        player.teleport(warpLoc);
        warpCooldownMap.put(player, true);
        Bukkit.getScheduler().scheduleAsyncDelayedTask(FantasyWar.getInstance(), new Runnable() {
            @Override
            public void run() {
                warpCooldownMap.put(player, false);
            }
        }, 100);
        player.sendMessage(FantasyWar.getSystemPrefix() + "Du bist nun bei dem Warp§8:§9 " + warp);
        return true;
    }
    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if (strings.length == 1) {
            return warpUtils.getWarpList();
        }
        return null;
    }
}
