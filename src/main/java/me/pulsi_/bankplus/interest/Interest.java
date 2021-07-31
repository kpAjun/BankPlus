package me.pulsi_.bankplus.interest;

import me.pulsi_.bankplus.BankPlus;
import me.pulsi_.bankplus.managers.EconomyManager;
import me.pulsi_.bankplus.managers.MessageManager;
import me.pulsi_.bankplus.utils.MethodUtils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class Interest {

    private final BankPlus plugin;
    public Interest(BankPlus plugin) {
        this.plugin = plugin;
    }

    public void startsInterest() {
        if (!plugin.getConfiguration().getBoolean("Interest.Enabled")) return;

        Bukkit.getScheduler().runTaskTimer(plugin, () -> {

            long cooldown = plugin.getPlayers().getLong("Interest-Cooldown");
            long delay = plugin.getConfiguration().getLong("Interest.Delay");

            if (cooldown <= 1) {
                giveInterestToEveryone();
                plugin.getPlayers().set("Interest-Cooldown", delay);
            } else {
                plugin.getPlayers().set("Interest-Cooldown", cooldown - 1);
            }
            plugin.savePlayers();
        }, 0, MethodUtils.ticksInMinutes(1));
    }

    public void giveInterestToEveryone() {

        double moneyPercentage = plugin.getConfiguration().getDouble("Interest.Money-Given");
        long maxAmount = plugin.getConfiguration().getLong("Interest.Max-Amount");

        for (Player p : Bukkit.getOnlinePlayers()) {
            if (!p.hasPermission("bankplus.receive.interest")) continue;
            giveInterest(p, moneyPercentage, maxAmount);
            plugin.savePlayers();
        }
    }

    private void giveInterest(Player p, double moneyPercentage, long maxAmount) {
        long bankBalance = EconomyManager.getBankBalance(p, plugin);
        long maxBankCapacity = plugin.getConfiguration().getLong("General.Max-Bank-Capacity");
        long interestMoney = (long)(bankBalance * moneyPercentage);

        if (maxBankCapacity != 0) {
            if (bankBalance + interestMoney >= maxBankCapacity) {
                EconomyManager.setPlayerBankBalance(p, maxBankCapacity, plugin);
                if (plugin.getMessages().getBoolean("Interest-Broadcast.Enabled"))
                    MessageManager.interestBroadcastMessageMax(p, plugin, maxBankCapacity - bankBalance);
            } else {
                if (bankBalance != 0) {
                    if (interestMoney == 0) {
                        EconomyManager.addPlayerBankBalance(p, 1, plugin);
                        if (plugin.getMessages().getBoolean("Interest-Broadcast.Enabled"))
                            MessageManager.interestBroadcastMessageMax(p, plugin, 1);
                    } else {
                        if (interestMoney >= maxAmount) {
                            EconomyManager.addPlayerBankBalance(p, maxAmount, plugin);
                            if (plugin.getMessages().getBoolean("Interest-Broadcast.Enabled"))
                                MessageManager.interestBroadcastMessageMax(p, plugin, maxAmount);
                            return;
                        }
                        EconomyManager.addPlayerBankBalance(p, interestMoney, plugin);
                        if (plugin.getMessages().getBoolean("Interest-Broadcast.Enabled"))
                            MessageManager.interestBroadcastMessageMax(p, plugin, interestMoney);
                    }
                } else {
                    if (plugin.getMessages().getBoolean("Interest-Broadcast.Enabled"))
                        MessageManager.noMoneyInterest(p, plugin);
                }
            }
        } else {
            if (bankBalance != 0) {
                if (interestMoney == 0) {
                    EconomyManager.addPlayerBankBalance(p, 1, plugin);
                    if (plugin.getMessages().getBoolean("Interest-Broadcast.Enabled"))
                        MessageManager.interestBroadcastMessageMax(p, plugin, 1);
                } else {
                    if (interestMoney >= maxAmount) {
                        EconomyManager.addPlayerBankBalance(p, maxAmount, plugin);
                        if (plugin.getMessages().getBoolean("Interest-Broadcast.Enabled"))
                            MessageManager.interestBroadcastMessageMax(p, plugin, maxAmount);
                        return;
                    }
                    EconomyManager.addPlayerBankBalance(p, interestMoney, plugin);
                    if (plugin.getMessages().getBoolean("Interest-Broadcast.Enabled"))
                        MessageManager.interestBroadcastMessageMax(p, plugin, interestMoney);
                }
            } else {
                if (plugin.getMessages().getBoolean("Interest-Broadcast.Enabled"))
                    MessageManager.noMoneyInterest(p, plugin);
            }
        }
    }
}