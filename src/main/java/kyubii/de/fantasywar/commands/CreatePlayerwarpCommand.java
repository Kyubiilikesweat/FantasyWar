package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.PlayerWarpUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreatePlayerwarpCommand implements CommandExecutor {
    PlayerWarpUtils warpUtils = new PlayerWarpUtils();

    @Override
    @SuppressWarnings("all")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(FantasyWar.getNoPlayer());
            return true;
        }

        Player player = (Player) sender;
        Location location = player.getLocation();

        if (!(args.length == 1)) {
            player.sendMessage(FantasyWar.getSystemPrefix() + "Benutze §e/createfantasywarp §7[§9Warp§7]");
            return true;
        }
        String warp = args[0];
        if (!(args[0].length() < 20)) {
            player.sendMessage(FantasyWar.getSystemPrefix() + "Der §9Warp-Name §7Darf maximal 20 Buchstaben lang sein§8!");
            return true;
        }

        if (warpUtils.warpExists(warp)){
            player.sendMessage(FantasyWar.getSystemPrefix() + "Der §9Warp §7existiert bereits§8.");
            return true;
        }
        int warpCount = warpUtils.getWarpCount(player.getUniqueId());
        if (warpCount == 3){
            if (!player.hasPermission("fantasywarp.bypass")) {
                player.sendMessage(FantasyWar.getSystemPrefix() + "Du kannst maximal §93 §7Warps erstellen§8." +
                        " §7Benutze §e/deletefantasywarp §7[§9Warp§7] um einen Warp von dir zu entfernen" );
                return true;
            }
        }

        if (warp.equalsIgnoreCase("Hs") || warp.equalsIgnoreCase("Hurensohn") || warp.equalsIgnoreCase("Nigga") || warp.equalsIgnoreCase("Hs") || warp.equalsIgnoreCase("Schwanz") || warp.equalsIgnoreCase("Hakenkreuz") || warp.equalsIgnoreCase("Bastard")){
            player.sendMessage(FantasyWar.getSystemPrefix() + "§7Diese Warp-Namen werden nicht geduldet. Der Versuch, diesen Warp zu erstellen wurde einem Teammitglied gemeldet.");
            for (Player all : Bukkit.getOnlinePlayers()){
                if (all.hasPermission("fantasywarp.bypass")){
                    all.sendMessage(FantasyWar.getSystemPrefix() + "Der Spieler §c" + player.getDisplayName() + " §7Hat versucht einen §4§lunangemessenen §7Warp zu erstellen§8+3: §9" + warp);
                }
            }
            return true;
        }

        warpUtils.addWarp(player.getUniqueId(), warp, location);
        player.sendMessage(FantasyWar.getSystemPrefix() + "Du hast den Warp §aerfolgreich erstellt§8.");
        return true;
    }
}
