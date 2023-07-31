package kyubii.de.fantasywar.commands;

import kyubii.de.fantasywar.FantasyWar;
import kyubii.de.fantasywar.utils.BoosterClass;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;



public class BoosterCommand implements CommandExecutor {
    public static Integer DropB = 0;

    public static Integer XPB = 0;

    public static Integer FlyB = 0;

    public static Integer MobB = 0;

    public static Integer BreakB = 0;

    public static Integer KHungerB = 0;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {
        Player p = (Player) sender;
        if (sender instanceof Player) {
            if (args.length == 0) {
                p.sendMessage(FantasyWar.getSystemPrefix() + "§7Folgende Booster sind §aaktiviert§8:");
                if (DropB != 0) {
                    p.sendMessage("§eDrop-Booster§8: §a" + DropB + "x");
                } else {
                    p.sendMessage("§eDrop-Booster§8: §caus");
                }
                if (XPB != 0) {
                    p.sendMessage("§eXP-Booster§8: §a" + XPB + "x");
                } else {
                    p.sendMessage("§eXP-Booster§8: §caus");
                }
                if (FlyB != 0) {
                    p.sendMessage("§eFly-Booster§8: §a" + FlyB + "x");
                } else {
                    p.sendMessage("§eFly-Booster§8: §caus");
                }
                if (MobB != 0) {
                    p.sendMessage("§eMob-Booster§8: §a" + MobB + "x");
                } else {
                    p.sendMessage("§eMob-Booster§8: §caus");
                }
                if (BreakB != 0) {
                    p.sendMessage("§eAbbau-Booster§8: §a" + BreakB + "x");
                } else {
                    p.sendMessage("§eAbbau-Booster§8: §caus");
                }
                if (KHungerB != 0) {
                    p.sendMessage("§eKeinHunger-Booster§8: §a" + KHungerB + "x");
                } else {
                    p.sendMessage("§eKeinHunger-Booster§8: §caus");
                }
                p.sendMessage(FantasyWar.getSystemPrefix() + "§7Mit §e/booster help §7bekommst du weitere Informationen zu den Boostern!");
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(String.valueOf("§aAktiviere §7einen Booster mit"));
                    p.sendMessage(String.valueOf("§e/booster Drops"));
                    p.sendMessage(String.valueOf("§e/booster Erfahrung"));
                    p.sendMessage(String.valueOf("§e/booster Fliegen"));
                    p.sendMessage(String.valueOf("§e/booster Mobs"));
                    p.sendMessage(String.valueOf("§e/booster Break"));
                    p.sendMessage(String.valueOf("§e/booster Hunger"));
                } else if (args[0].equalsIgnoreCase("Drops")) {
                    if (BoosterClass.getBooster(p) >= 1) {
                        if (DropB != 3) {
                            BoosterClass.RemoveBooster(p, 1);
                            DropB = DropB + 1;
                            Bukkit.broadcastMessage("§e" + p.getName() + " §7hat für die Community den §e§lDrop-Booster §7für §e30 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator§8: §a§l" + DropB + "x");
                            Bukkit.broadcastMessage(" ");
                            for (Player all : Bukkit.getOnlinePlayers())
                                all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                            Bukkit.getScheduler().runTaskLater((Plugin) FantasyWar.getInstance(), new Runnable() {
                                public void run() {
                                    BoosterCommand.DropB = DropB - 1;
                                    Bukkit.broadcastMessage(String.valueOf("§cEin Drop-Booster ist abgelaufen!"));
                                    Bukkit.broadcastMessage("§eMultiplikator§8: §a§l" + DropB + "x");
                                    for (Player all : Bukkit.getOnlinePlayers())
                                        all.playSound(all.getLocation(), Sound.ENTITY_WITHER_HURT, 1.0F, 1.0F);
                                }
                            }, 36000L);
                        } else {
                            p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§cDas Limit vom §e§lDrop-Booster §cist bereits erreciht!"));
                        }
                    } else {
                        p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§7Leider hast du §ckeine §7Booster mehr übrig! Kaufe dir welche im §e/boostershop"));
                    }
                } else if (args[0].equalsIgnoreCase("Erfahrung")) {
                    if (BoosterClass.getBooster(p) >= 1) {
                        if (XPB != 3) {
                            BoosterClass.RemoveBooster(p, 1);
                            XPB = XPB + 1;

                            Bukkit.broadcastMessage("§e" + p.getName() + " §7hat für die Community den §e§lXP-Booster §7für §e30 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator§8: §a§l" + XPB + "x");
                            for (Player all : Bukkit.getOnlinePlayers())
                                all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                            Bukkit.getScheduler().runTaskLater((Plugin) FantasyWar.getInstance(), new Runnable() {
                                public void run() {
                                    BoosterCommand.XPB = XPB - 1;
                                    Bukkit.broadcastMessage(String.valueOf("§cEin XP-Booster ist abgelaufen!"));
                                    Bukkit.broadcastMessage("§eMultiplikator§8: §a§l" + XPB + "x");
                                    for (Player all : Bukkit.getOnlinePlayers())
                                        all.playSound(all.getLocation(), Sound.ENTITY_WITHER_HURT, 1.0F, 1.0F);
                                }
                            }, 36000L);
                        } else {
                            p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§cDas Limit vom §e§lXP-Booster §cist bereits erreciht!"));
                        }
                    } else {
                        p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§7Leider hast du §ckeine §7Booster mehr übrig! Kaufe dir welche im §e/boostershop"));
                    }
                } else if (args[0].equalsIgnoreCase("Fliegen")) {
                    if (BoosterClass.getBooster(p) >= 1) {
                        if (FlyB == 0) {
                            BoosterClass.RemoveBooster(p, 1);
                            FlyB = XPB + 1;
                            Bukkit.broadcastMessage("§e" + p.getName() + " §7hat für die Community den §e§lFly-Booster §7für §e10 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator§8: §a§l" + FlyB + "x");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                                all.setAllowFlight(true);
                            }
                            Bukkit.getScheduler().runTaskLater((Plugin) FantasyWar.getInstance(), new Runnable() {
                                public void run() {
                                    
                                    Bukkit.broadcastMessage(String.valueOf("§cDer Server Biister ist beendet. Dein Flugmodus wird deaktiviert..."));
                                    for (Player all : Bukkit.getOnlinePlayers())
                                        all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0F, 1.0F);
                                    Bukkit.getScheduler().runTaskLater((Plugin) FantasyWar.getInstance(), new Runnable() {
                                        public void run() {
                                            Bukkit.broadcastMessage(String.valueOf("§cDer Server Booster ist beendet. Dein Flugmodus wird deaktiviert..."));
                                            for (Player all : Bukkit.getOnlinePlayers())
                                                all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_FLAP, 1.0F, 1.0F);
                                            Bukkit.getScheduler().runTaskLater((Plugin) FantasyWar.getInstance(), new Runnable() {
                                                public void run() {
                                                    FlyB = FlyB - 1;
                                                    Bukkit.broadcastMessage(String.valueOf("§cEin Fly-Booster ist abgelaufen!"));
                                                    Bukkit.broadcastMessage("§eMultiplikator§8: §a§l" + FlyB + "x");
                                                    Bukkit.broadcastMessage("§cDer Server Booster is beendet. Dein Flugmodus wurde deaktiviert");
                                                    Bukkit.broadcastMessage("§e§lFly-Booster §cist jetzt deaktiviert!");
                                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.ENTITY_WITHER_HURT, 1.0F, 1.0F);
                                                        if (all.hasPermission("essentials.fly")) {
                                                            all.setAllowFlight(true);
                                                            continue;
                                                        }
                                                        all.setAllowFlight(false);
                                                    }
                                                }
                                            }, 80L);
                                        }
                                    }, 80L);
                                }
                            }, 12000L);
                        } else {
                            p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§cEs ist bereits ein §e§lFly-Booster §caktiv!"));
                        }
                    } else {
                        p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§7Leider hast du §ckeine §7Booster mehr übrig! Kaufe dir welche im §e/boostershop"));
                    }
                } else if (args[0].equalsIgnoreCase("Mobs")) {
                    if (BoosterClass.getBooster(p) >= 1) {
                        if (MobB != 3) {
                            BoosterClass.RemoveBooster(p, 1);
                            MobB = MobB + 1;
                            Bukkit.broadcastMessage("§e" + p.getName() + " §7hat für die Community den §e§lMob-Booster §7für §e30 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator: §a§l" + MobB + "x");
                            for (Player all : Bukkit.getOnlinePlayers())
                                all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                            Bukkit.getScheduler().runTaskLater((Plugin) FantasyWar.getInstance(), new Runnable() {
                                public void run() {
                                    MobB = MobB - 1;
                                    Bukkit.broadcastMessage(String.valueOf("§cEin §eMob-Booster §cist abgelaufen!"));
                                    Bukkit.broadcastMessage("§eMultiplikator: §a§l" + MobB + "x");
                                    for (Player all : Bukkit.getOnlinePlayers())
                                        all.playSound(all.getLocation(), Sound.ENTITY_WITHER_HURT, 1.0F, 1.0F);
                                }
                            }, 36000L);
                        } else {
                            p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§cDas Limit vom §e§lMob-Booster §cist bereits erreicht!"));
                        }
                    } else {
                        p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§7Leider hast du §ckeine §7Booster mehr übrig! Kaufe dir welche im §e/boostershop"));
                    }
                } else if (args[0].equalsIgnoreCase("Break")) {
                    if (BoosterClass.getBooster(p) >= 1) {
                        if (BreakB != 1) {
                            BoosterClass.RemoveBooster(p, 1);
                            BreakB = BreakB + 1;
                            Bukkit.broadcastMessage("§e" + p.getName() + " §7hat für die Community den §e§lBreak-Booster §7für §e30 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BreakB + "x");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                                all.removePotionEffect(PotionEffectType.FAST_DIGGING);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 2147483647, 50));
                            }
                            Bukkit.getScheduler().runTaskLater( FantasyWar.getInstance(), new Runnable() {
                                public void run() {
                                    BreakB = BreakB - 1;
                                    Bukkit.broadcastMessage(String.valueOf("§cEin §eBreak-Booster §cist abgelaufen!"));
                                    Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BreakB + "x");
                                    for (Player all : Bukkit.getOnlinePlayers())
                                        all.playSound(all.getLocation(), Sound.ENTITY_WITHER_HURT, 1.0F, 1.0F);
                                }
                            }, 36000L);
                        } else {
                            p.sendMessage(String.valueOf("Limit vom §e§lBreak-Booster §cist bereits erreicht!"));
                        }
                    } else {
                        p.sendMessage(String.valueOf("§7Leider hast du §ckeine §7Booster mehr übrig! Kaufe dir welche im §e/boostershop"));
                    }
                } else if (args[0].equalsIgnoreCase("Hunger")) {
                    if (BoosterClass.getBooster(p) >= 1) {
                        if (KHungerB != 1) {
                            BoosterClass.RemoveBooster(p, 1);
                            KHungerB = KHungerB + 1;
                            Bukkit.broadcastMessage("§e" + p.getName() + " §7hat für die Community den §e§lKeinHunger-Booster §7für §e30 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator: §a§l" + KHungerB + "x");
                            for (Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.ENTITY_ENDER_DRAGON_GROWL, 1.0F, 1.0F);
                                all.removePotionEffect(PotionEffectType.SATURATION);
                                p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 2147483647, 255));
                            }
                            Bukkit.getScheduler().runTaskLater((Plugin) FantasyWar.getInstance(), new Runnable() {
                                public void run() {
                                    KHungerB = KHungerB - 1;
                                    Bukkit.broadcastMessage(String.valueOf("§cEin §eKeinHunger-Booster §cist abgelaufen!"));
                                    Bukkit.broadcastMessage("§eMultiplikator: §a§l" + KHungerB + "x");
                                    for (Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.ENTITY_WITHER_HURT, 1.0F, 1.0F);
                                        all.removePotionEffect(PotionEffectType.SATURATION);
                                    }
                                }
                            }, 36000L);
                        } else {
                            p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("Limit vom §e§lKeinHunger-Booster §cist bereits erreicht!"));
                        }
                    } else {
                        p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§7Leider hast du §ckeine §7Booster mehr übrig! Kaufe dir welche im §e/boostershop"));
                    }
                }else
                p.sendMessage(String.valueOf("§aAktiviere §7einen Booster mit"));
                p.sendMessage(String.valueOf("§e/booster Drops"));
                p.sendMessage(String.valueOf("§e/booster Erfahrung"));
                p.sendMessage(String.valueOf("§e/booster Fliegen"));
                p.sendMessage(String.valueOf("§e/booster Mobs"));
                p.sendMessage(String.valueOf("§e/booster Break"));
                p.sendMessage(String.valueOf("§e/booster Hunger"));
            }else if (args.length == 3){
                if (args[0].equalsIgnoreCase("give")) {
                    if (p.hasPermission("booster.give")){
                    String target = args[1];
                    Player tar = Bukkit.getPlayer(target);
                    if (tar != null){
                        if (args[2].matches("[0-9]")) {
                            p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§7Du hast dem Spieler §e" + tar.getName() + " §6" + args[2] + " §7Booster gegeben."));
                            tar.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§e" + p.getName() + " §7hat dir §6" + args[2] + " §7Booster gegeben."));
                            BoosterClass.AddBooster(tar, Integer.valueOf(args[2]));
                        }else p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§cBitte gebe eine Zahl an."));
                    }else p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§cDer Spieler ist nicht online!"));
                }else p.sendMessage(FantasyWar.getNoPerm());
                }else
                    p.sendMessage(FantasyWar.getSystemPrefix() + String.valueOf("§cBenutze: §e/booster <Help/Erfahrung/Drops/Fliegen/Mobs/Hunger/give> <Spieler> <Anzahl>"));
            }
        }
        return false;
    }
}
