package de.group.fwsystemcb.commands;

import de.group.fwsystemcb.Main;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
public class BroadcastCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (commandSender.hasPermission("broadcast.use")){

            if (args.length >= 1){
                String msg = "§4§lINFO §8»" + " §a§l";
                for (int i = 0; i< args.length;i++) {
                    msg = msg + args[i] + " ";
                }
                Bukkit.broadcastMessage("§8»");
                Bukkit.broadcastMessage(msg);
                Bukkit.broadcastMessage("§8»");
                for (Player all : Bukkit.getOnlinePlayers()){
                    all.playSound(all.getLocation(), Sound.BLOCK_BELL_USE, 45, 65);
                }


            }else
                commandSender.sendMessage(Main.prefix + "§cUsage: /bc <Message>");

        }else
            commandSender.sendMessage(Main.prefix + "§cDazu hast du keine Rechte!");
        return false;
    }
}
