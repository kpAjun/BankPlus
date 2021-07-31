package me.pulsi_.bankplus.commands;

import me.pulsi_.bankplus.BankPlus;
import me.pulsi_.bankplus.guis.GuiBank;
import me.pulsi_.bankplus.managers.EconomyManager;
import me.pulsi_.bankplus.managers.MessageManager;
import me.pulsi_.bankplus.utils.ChatUtils;
import me.pulsi_.bankplus.utils.MethodUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

    private BankPlus plugin;
    public Commands(BankPlus plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender s, Command command, String label, String[] args) {

        if (args.length == 0) {
            if (s instanceof Player) {
                Player p = (Player) s;
                if (plugin.getConfiguration().getBoolean("Gui.Enabled")) {
                    new GuiBank(plugin).openGui(p);
                    MethodUtils.playSound("PERSONAL", p, plugin);
                } else {
                    MessageManager.personalBalance(p, plugin);
                }
            } else {
                s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Not-Player")));
            }
        }

        if (args.length == 1) {
            switch (args[0]) {
                case "reload":
                    if (s.hasPermission("bankplus.reload")) {
                        plugin.reloadConfigs();
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Reload")));
                    } else {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("No-Permission")));
                    }
                    break;

                case "help":
                    if (s.hasPermission("bankplus.help")) {
                        for (String helpMessage : plugin.getMessages().getStringList("Help-Message"))
                            s.sendMessage(ChatUtils.c(helpMessage));
                    } else {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("No-Permission")));
                    }
                    break;

                case "view":
                    if (s.hasPermission("bankplus.view")) {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Specify-Player")));
                    } else {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("No-Permission")));
                    }
                    break;

                case "withdraw":
                    if (s.hasPermission("bankplus.withdraw")) {
                        if (s instanceof Player) {
                            s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Specify-Number")));
                        } else {
                            s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Not-Player")));
                        }
                    } else {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("No-Permission")));
                    }
                    break;

                case "deposit":
                    if (s.hasPermission("bankplus.deposit")) {
                        if (s instanceof Player) {
                            s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Specify-Number")));
                        } else {
                            s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Not-Player")));
                        }
                    } else {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("No-Permission")));
                    }
                    break;

                case "set":
                    if (s.hasPermission("bankplus.set")) {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Specify-Player")));
                    } else {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("No-Permission")));
                    }
                    break;

                case "add":
                    if (s.hasPermission("bankplus.add")) {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Specify-Player")));
                    } else {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("No-Permission")));
                    }
                    break;

                case "remove":
                    if (s.hasPermission("bankplus.remove")) {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Specify-Player")));
                    } else {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("No-Permission")));
                    }
                    break;

                case "interest":
                    if (s.hasPermission("bankplus.interest.restart")) {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Interest-Usage")));
                    } else {
                        s.sendMessage(ChatUtils.c(plugin.getMessages().getString("No-Permission")));
                    }
                    break;

                default:
                    s.sendMessage(ChatUtils.c(plugin.getMessages().getString("Unknown-Command")));
                    break;
            }
        }

        if (args.length == 2) {
            switch (args[0]) {
                case "view":
                    if (s.hasPermission("bankplus.view")) {
                        try {
                            if (s instanceof Player) {
                                MethodUtils.playSound("VIEW", (Player) s, plugin);
                            }
                            Player target = Bukkit.getPlayerExact(args[1]);
                            MessageManager.bankOthers(s, plugin, target);
                        } catch (NullPointerException err) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            MessageManager.bankOthers(s, plugin, target);
                        }
                    } else {
                        MessageManager.noPermission(s, plugin);
                    }
                    break;

                case "withdraw":
                    if (s.hasPermission("bankplus.withdraw")) {
                        if (s instanceof Player) {
                            try {
                                Player p = (Player) s;
                                long amount = Long.parseLong(args[1]);
                                MethodUtils.withdraw(p, amount, plugin);
                            } catch (NumberFormatException ex) {
                                MessageManager.invalidNumber(s, plugin);
                            }
                        } else {
                            MessageManager.notPlayer(s, plugin);
                        }
                    } else {
                        MessageManager.noPermission(s, plugin);
                    }
                    break;

                case "deposit":
                    if (s.hasPermission("bankplus.deposit")) {
                        if (s instanceof Player) {
                            Player p = (Player) s;
                            try {
                                long amount = Long.parseLong(args[1]);
                                MethodUtils.deposit(p, amount, plugin);
                            } catch (NumberFormatException ex) {
                                MessageManager.invalidNumber(s, plugin);
                            }
                        } else {
                            MessageManager.notPlayer(s, plugin);
                        }
                    } else {
                        MessageManager.noPermission(s, plugin);
                    }
                    break;

                case "set":
                    if (s.hasPermission("bankplus.set")) {
                        MessageManager.specifyNumber(s, plugin);
                    } else {
                        MessageManager.noPermission(s, plugin);
                    }
                    break;

                case "interest":
                    if (args[1].equalsIgnoreCase("restart")) {
                        if (s.hasPermission("bankplus.interest.restart")) {
                            if (plugin.getConfiguration().getBoolean("Interest.Enabled")) {
                                try {
                                    long delay = plugin.getConfiguration().getLong("Interest.Delay");
                                    plugin.getPlayers().set("Interest-Cooldown", delay);
                                    MessageManager.interestRestarted(s, plugin);
                                    plugin.savePlayers();
                                } catch (NullPointerException ex) {
                                    MessageManager.internalError(s, plugin);
                                }
                            } else {
                                MessageManager.interestIsDisabled(s, plugin);
                            }
                        } else {
                            MessageManager.noPermission(s, plugin);
                        }
                    }
                    break;

                default:
                    MessageManager.unknownCommand(s, plugin);
                    break;
            }
        }

        if (args.length == 3) {
            switch (args[0]) {
                case "set":
                    if (s.hasPermission("bankplus.set")) {
                        try {
                            Player target = Bukkit.getPlayerExact(args[1]);
                            long amount = Long.parseLong(args[2]);
                            EconomyManager.setPlayerBankBalance(target, amount, plugin);
                            MessageManager.setMessage(s, target, amount, plugin);
                        } catch (NullPointerException err) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            long amount = Long.parseLong(args[2]);
                            EconomyManager.setPlayerBankBalance(target, amount, plugin);
                            MessageManager.setMessage(s, target, amount, plugin);
                        } catch (NumberFormatException ex) {
                            MessageManager.invalidNumber(s, plugin);
                        }
                    } else {
                        MessageManager.noPermission(s, plugin);
                    }
                    break;

                case "add":
                    if (s.hasPermission("bankplus.add")) {
                        try {
                            Player target = Bukkit.getPlayerExact(args[1]);
                            long amount = Long.parseLong(args[2]);
                            EconomyManager.addPlayerBankBalance(target, amount, plugin);
                            MessageManager.addMessage(s, target, amount, plugin);
                        } catch (NullPointerException err) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            long amount = Long.parseLong(args[2]);
                            EconomyManager.addPlayerBankBalance(target, amount, plugin);
                            MessageManager.addMessage(s, target, amount, plugin);
                            MessageManager.cannotFindPlayer(s, plugin);
                        } catch (NumberFormatException ex) {
                            MessageManager.invalidNumber(s, plugin);
                        }
                    } else {
                        MessageManager.noPermission(s, plugin);
                    }
                    break;

                case "remove":
                    if (s.hasPermission("bankplus.remove")) {
                        try {
                            Player target = Bukkit.getPlayerExact(args[1]);
                            long amount = Long.parseLong(args[2]);
                            EconomyManager.removePlayerBankBalance(target, amount, plugin);
                            MessageManager.removeMessage(s, target, amount, plugin);
                        } catch (NullPointerException err) {
                            OfflinePlayer target = Bukkit.getOfflinePlayer(args[1]);
                            long amount = Long.parseLong(args[2]);
                            EconomyManager.removePlayerBankBalance(target, amount, plugin);
                            MessageManager.removeMessage(s, target, amount, plugin);
                        } catch (NumberFormatException ex) {
                            MessageManager.invalidNumber(s, plugin);
                        }
                    } else {
                        MessageManager.noPermission(s, plugin);
                    }
                    break;

                default:
                    MessageManager.unknownCommand(s, plugin);
                    break;
            }
        }
        return true;
    }
}