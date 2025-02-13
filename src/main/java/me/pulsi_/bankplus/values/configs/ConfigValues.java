package me.pulsi_.bankplus.values.configs;

import me.pulsi_.bankplus.BankPlus;
import me.pulsi_.bankplus.managers.ConfigManager;
import me.pulsi_.bankplus.utils.BPLogger;
import me.pulsi_.bankplus.utils.BPMethods;
import org.bukkit.configuration.file.FileConfiguration;

import java.math.BigDecimal;
import java.util.List;

public class ConfigValues {

    private static final String DEF_ERROR = "Invalid number for \"%\", Please correct it in the config as soon as possible!";

    private List<String> worldsBlacklist, exitCommands, bankTopFormat;
    private String exitMessage, playerChatPriority, bankClickPriority;
    private String second, seconds, minute, minutes, hour, hours, day, days;
    private String interestTimeOnlySeconds, interestTimeOnlyMinutes, interestTimeOnlyHours, interestTimeOnlyDays, interestTimeSecondsMinutes, interestTimeMinutesHours;
    private String interestTimeSecondsHours, interestTimeSecondsMinutesHours, interestTimeHoursDays, interestTimeMinutesDays, interestTimeSecondsDays;
    private String interestTimeMinutesHoursDays, interestTimeSecondsHoursDays, interestTimeSecondsMinutesHoursDays, interestTimeSecondsMinutesDays;
    private String k, m, b, t, q, qq;
    private String withdrawSound, depositSound, viewSound, personalSound;
    private String maxDepositAmount, maxWithdrawAmount, depositTaxes, withdrawTaxes, depositMinimumAmount, withdrawMinimumAmount, maxBankCapacity, startAmount;
    private String bankTopMoneyFormat, banktopUpdateBroadcastMessage, bankUpgradedMaxPlaceholder, mainGuiName;
    private String notifyOfflineInterestMessage, interestDelay, interestOfflinePermission, interestMaxAmount, interestMoneyGiven, offlineInterestMoneyGiven;
    private long notifyOfflineInterestDelay, saveBalancedDelay, updateBankTopDelay;
    private int afkPlayersTime, maxDecimalsAmount, bankTopSize;
    private boolean isReopeningBankAfterChat, isNotifyOfflineInterest, isStoringUUIDs;
    private boolean isInterestEnabled, isGivingInterestToOfflinePlayers, isOfflineInterestDifferentRate;
    private boolean isOfflineInterestEarnedMessageEnabled, isUpdateCheckerEnabled, isWithdrawSoundEnabled, isDepositSoundEnabled;
    private boolean isViewSoundEnabled, isPersonalSoundEnabled, isIgnoringAfkPlayers, useEssentialsXAFK;
    private boolean banktopEnabled, banktopUpdateBroadcastEnabled, banktopUpdateBroadcastOnlyConsole, saveBalancesBroadcast, guiModuleEnabled;

    public static ConfigValues getInstance() {
        return new ConfigValues();
    }

    public void setupValues() {
        FileConfiguration config = BankPlus.INSTANCE.getConfigManager().getConfig(ConfigManager.Type.CONFIG);

        exitMessage = config.getString("General-Settings.Chat-Exit-Message");
        playerChatPriority = config.getString("General-Settings.Event-Priorities.PlayerChat");
        bankClickPriority = config.getString("General-Settings.Event-Priorities.BankClick");
        second = config.getString("Placeholders.Time.Second");
        seconds = config.getString("Placeholders.Time.Seconds");
        minute = config.getString("Placeholders.Time.Minute");
        minutes = config.getString("Placeholders.Time.Minutes");
        hour = config.getString("Placeholders.Time.Hour");
        hours = config.getString("Placeholders.Time.Hours");
        day = config.getString("Placeholders.Time.Day");
        days = config.getString("Placeholders.Time.Days");
        interestTimeOnlySeconds = config.getString("Placeholders.Time.Interest-Time.Only-Seconds");
        interestTimeOnlyMinutes = config.getString("Placeholders.Time.Interest-Time.Only-Minutes");
        interestTimeOnlyHours = config.getString("Placeholders.Time.Interest-Time.Only-Hours");
        interestTimeOnlyDays = config.getString("Placeholders.Time.Interest-Time.Only-Days");
        interestTimeSecondsMinutes = config.getString("Placeholders.Time.Interest-Time.Seconds-Minutes");
        interestTimeMinutesHours = config.getString("Placeholders.Time.Interest-Time.Minutes-Hours");
        interestTimeSecondsHours = config.getString("Placeholders.Time.Interest-Time.Seconds-Hours");
        interestTimeSecondsMinutesHours = config.getString("Placeholders.Time.Interest-Time.Seconds-Minutes-Hours");
        interestTimeHoursDays = config.getString("Placeholders.Time.Interest-Time.Hours-Days");
        interestTimeMinutesDays = config.getString("Placeholders.Time.Interest-Time.Minutes-Days");
        interestTimeSecondsDays = config.getString("Placeholders.Time.Interest-Time.Seconds-Days");
        interestTimeMinutesHoursDays = config.getString("Placeholders.Time.Interest-Time.Minutes-Hours-Days");
        interestTimeSecondsHoursDays = config.getString("Placeholders.Time.Interest-Time.Seconds-Hours-Days");
        interestTimeSecondsMinutesDays = config.getString("Placeholders.Time.Interest-Time.Seconds-Minutes-Days");
        interestTimeSecondsMinutesHoursDays = config.getString("Placeholders.Time.Interest-Time.Seconds-Minutes-Hours-Days");
        k = config.getString("Placeholders.Money.Thousands");
        m = config.getString("Placeholders.Money.Millions");
        b = config.getString("Placeholders.Money.Billions");
        t = config.getString("Placeholders.Money.Trillions");
        q = config.getString("Placeholders.Money.Quadrillions");
        qq = config.getString("Placeholders.Money.Quintillions");
        withdrawSound = config.getString("General-Settings.Withdraw-Sound.Sound");
        depositSound = config.getString("General-Settings.Deposit-Sound.Sound");
        viewSound = config.getString("General-Settings.View-Sound.Sound");
        personalSound = config.getString("General-Settings.Personal-Sound.Sound");
        notifyOfflineInterestMessage = config.getString("General-Settings.Offline-Interest-Earned-Message.Message");
        interestDelay = config.getString("Interest.Delay");
        interestOfflinePermission = config.getString("Interest.Offline-Permission");
        maxDepositAmount = config.getString("Deposit-Settings.Max-Deposit-Amount");
        maxWithdrawAmount = config.getString("Withdraw-Settings.Max-Withdraw-Amount");
        depositTaxes = config.getString("Deposit-Settings.Deposit-Taxes");
        withdrawTaxes = config.getString("Withdraw-Settings.Withdraw-Taxes");
        depositMinimumAmount = config.getString("Deposit-Settings.Minimum-Deposit-Amount");
        withdrawMinimumAmount = config.getString("Withdraw-Settings.Minimum-Withdraw-Amount");
        maxBankCapacity = config.getString("General-Settings.Max-Bank-Capacity");
        startAmount = config.getString("General-Settings.Join-Start-Amount");
        notifyOfflineInterestDelay = config.getLong("General-Settings.Offline-Interest-Earned-Message.Delay");
        interestMaxAmount = config.getString("Interest.Max-Amount");
        interestMoneyGiven = config.getString("Interest.Money-Given");
        offlineInterestMoneyGiven = config.getString("Interest.Offline-Money-Given");
        bankUpgradedMaxPlaceholder = config.getString("Placeholders.Upgrades.Max-Level");
        worldsBlacklist = config.getStringList("General-Settings.Worlds-Blacklist");
        exitCommands = config.getStringList("General-Settings.Chat-Exit-Commands");
        isReopeningBankAfterChat = config.getBoolean("General-Settings.Reopen-Bank-After-Chat");
        isInterestEnabled = config.getBoolean("Interest.Enabled");
        isNotifyOfflineInterest = config.getBoolean("General-Settings.Offline-Interest-Earned-Message.Enabled");
        isStoringUUIDs = config.getBoolean("General-Settings.Use-UUIDs");
        isGivingInterestToOfflinePlayers = config.getBoolean("Interest.Give-To-Offline-Players");
        isOfflineInterestDifferentRate = config.getBoolean("Interest.Different-Offline-Rate");
        isOfflineInterestEarnedMessageEnabled = config.getBoolean("General-Settings.Offline-Interest-Earned-Message.Enabled");
        isUpdateCheckerEnabled = config.getBoolean("Update-Checker");
        isWithdrawSoundEnabled = config.getBoolean("General-Settings.Withdraw-Sound.Enabled");
        isDepositSoundEnabled = config.getBoolean("General-Settings.Deposit-Sound.Enabled");
        isViewSoundEnabled = config.getBoolean("General-Settings.View-Sound.Enabled");
        isPersonalSoundEnabled = config.getBoolean("General-Settings.Personal-Sound.Enabled");
        isIgnoringAfkPlayers = config.getBoolean("Interest.AFK-Settings.Ignore-AFK-Players");
        useEssentialsXAFK = config.getBoolean("Interest.AFK-Settings.Use-EssentialsX-AFK");
        afkPlayersTime = config.getInt("Interest.AFK-Settings.AFK-Time");
        maxDecimalsAmount = config.getInt("General-Settings.Max-Decimals-Amount");
        saveBalancedDelay = config.getLong("General-Settings.Save-Delay");
        banktopEnabled = config.getBoolean("BankTop.Enabled");
        updateBankTopDelay = config.getLong("BankTop.Update-Delay");
        bankTopSize = config.getInt("BankTop.Size");
        bankTopMoneyFormat = config.getString("BankTop.Money-Format");
        bankTopFormat = config.getStringList("BankTop.Format");
        banktopUpdateBroadcastEnabled = config.getBoolean("BankTop.Update-Broadcast.Enabled");
        banktopUpdateBroadcastOnlyConsole = config.getBoolean("BankTop.Update-Broadcast.Only-Console");
        banktopUpdateBroadcastMessage = config.getString("BankTop.Update-Broadcast.Message");
        saveBalancesBroadcast = config.getBoolean("General-Settings.Save-Broadcast");
        guiModuleEnabled = config.getBoolean("General-Settings.Enable-Guis");
        mainGuiName = config.getString("General-Settings.Main-Gui");
    }

    public String getPlayerChatPriority() {
        return playerChatPriority;
    }

    public String getBankClickPriority() {
        return bankClickPriority;
    }

    public String getExitMessage() {
        return exitMessage == null ? "exit" : exitMessage;
    }

    public String getSecond() {
        return second == null ? "Second" : second;
    }

    public String getSeconds() {
        return seconds == null ? "Seconds" : seconds;
    }

    public String getMinute() {
        return minute == null ? "Minute" : minute;
    }

    public String getMinutes() {
        return minutes == null ? "Minutes" : minutes;
    }

    public String getHour() {
        return hour == null ? "Hour" : hour;
    }

    public String getHours() {
        return hours == null ? "Hours" : hours;
    }

    public String getDay() {
        return day == null ? "Day" : day;
    }

    public String getDays() {
        return days == null ? "Days" : days;
    }

    public String getInterestTimeOnlySeconds() {
        return interestTimeOnlySeconds;
    }

    public String getInterestTimeOnlyMinutes() {
        return interestTimeOnlyMinutes;
    }

    public String getInterestTimeOnlyHours() {
        return interestTimeOnlyHours;
    }

    public String getInterestTimeOnlyDays() {
        return interestTimeOnlyDays;
    }

    public String getInterestTimeSecondsMinutes() {
        return interestTimeSecondsMinutes;
    }

    public String getInterestTimeMinutesHours() {
        return interestTimeMinutesHours;
    }

    public String getInterestTimeSecondsHours() {
        return interestTimeSecondsHours;
    }

    public String getInterestTimeSecondsMinutesHours() {
        return interestTimeSecondsMinutesHours;
    }

    public String getInterestTimeHoursDays() {
        return interestTimeHoursDays;
    }

    public String getInterestTimeMinutesDays() {
        return interestTimeMinutesDays;
    }

    public String getInterestTimeSecondsDays() {
        return interestTimeSecondsDays;
    }

    public String getInterestTimeMinutesHoursDays() {
        return interestTimeMinutesHoursDays;
    }

    public String getInterestTimeSecondsHoursDays() {
        return interestTimeSecondsHoursDays;
    }

    public String getInterestTimeSecondsMinutesHoursDays() {
        return interestTimeSecondsMinutesHoursDays;
    }

    public String getInterestTimeSecondsMinutesDays() {
        return interestTimeSecondsMinutesDays;
    }

    public String getK() {
        return k == null ? "K" : k;
    }

    public String getM() {
        return m == null ? "M" : m;
    }

    public String getB() {
        return b == null ? "B" : b;
    }

    public String getT() {
        return t == null ? "T" : t;
    }

    public String getQ() {
        return q == null ? "Q" : q;
    }

    public String getQq() {
        return qq == null ? "QQ" : qq;
    }

    public String getWithdrawSound() {
        return withdrawSound;
    }

    public String getDepositSound() {
        return depositSound;
    }

    public String getViewSound() {
        return viewSound;
    }

    public String getPersonalSound() {
        return personalSound;
    }

    public String getNotifyOfflineInterestMessage() {
        return notifyOfflineInterestMessage;
    }

    public long getInterestDelay() {
        if (!interestDelay.contains(" ")) return BPMethods.minutesInMilliseconds(Integer.parseInt(interestDelay));

        int delay;
        try {
            delay = Integer.parseInt(interestDelay.split(" ")[0]);
        } catch (NumberFormatException e) {
            return BPMethods.minutesInMilliseconds(5);
        }

        String delayType = interestDelay.split(" ")[1];
        switch (delayType) {
            case "s":
                return BPMethods.secondsInMilliseconds(delay);
            default:
                return BPMethods.minutesInMilliseconds(delay);
            case "h":
                return BPMethods.hoursInMilliseconds(delay);
            case "d":
                return BPMethods.daysInMilliseconds(delay);
        }
    }

    public String getInterestOfflinePermission() {
        return interestOfflinePermission;
    }

    public BigDecimal getMaxDepositAmount() {
        if (BPMethods.isInvalidNumber(maxDepositAmount)) {
            BPLogger.error(DEF_ERROR.replace("%", "Deposit-Settings.Max-Deposit-Amount"));
            return new BigDecimal(0);
        }
        return new BigDecimal(maxDepositAmount);
    }

    public BigDecimal getMaxWithdrawAmount() {
        if (BPMethods.isInvalidNumber(maxWithdrawAmount)) {
            BPLogger.error(DEF_ERROR.replace("%", "Withdraw-Settings.Max-Withdraw-Amount"));
            return new BigDecimal(0);
        }
        return new BigDecimal(maxWithdrawAmount);
    }

    public String getDepositTaxesString() {
        return depositTaxes;
    }

    public BigDecimal getDepositTaxes() {
        if (BPMethods.isInvalidNumber(depositTaxes)) {
            BPLogger.error(DEF_ERROR.replace("%", "Deposit-Settings.Deposit-Taxes"));
            return new BigDecimal(0);
        }
        return new BigDecimal(depositTaxes.replace("%", ""));
    }

    public String getWithdrawTaxesString() {
        return withdrawTaxes;
    }

    public BigDecimal getWithdrawTaxes() {
        if (BPMethods.isInvalidNumber(withdrawTaxes)) {
            BPLogger.error(DEF_ERROR.replace("%", "Withdraw-Settings.Withdraw-Taxes"));
            return new BigDecimal(0);
        }
        return new BigDecimal(withdrawTaxes.replace("%", ""));
    }

    public BigDecimal getDepositMinimumAmount() {
        if (BPMethods.isInvalidNumber(depositMinimumAmount)) {
            BPLogger.error(DEF_ERROR.replace("%", "Deposit-Settings.Minimum-Deposit-Amount"));
            return new BigDecimal(0);
        }
        return new BigDecimal(depositMinimumAmount);
    }

    public BigDecimal getWithdrawMinimumAmount() {
        if (BPMethods.isInvalidNumber(withdrawMinimumAmount)) {
            BPLogger.error(DEF_ERROR.replace("%", "Withdraw-Settings.Minimum-Withdraw-Amount"));
            return new BigDecimal(0);
        }
        return new BigDecimal(withdrawMinimumAmount);
    }

    public BigDecimal getMaxBankCapacity() {
        if (BPMethods.isInvalidNumber(maxBankCapacity)) {
            BPLogger.error(DEF_ERROR.replace("%", "General-Settings.Max-Bank-Capacity"));
            return new BigDecimal(0);
        }
        return new BigDecimal(maxBankCapacity);
    }

    public BigDecimal getStartAmount() {
        if (BPMethods.isInvalidNumber(startAmount)) {
            BPLogger.error(DEF_ERROR.replace("%", "General-Settings.Join-Start-Amount"));
            return new BigDecimal(0);
        }
        return new BigDecimal(startAmount);
    }

    public long getNotifyOfflineInterestDelay() {
        return notifyOfflineInterestDelay;
    }

    public BigDecimal getInterestMaxAmount() {
        if (BPMethods.isInvalidNumber(interestMaxAmount)) {
            BPLogger.error(DEF_ERROR.replace("%", "Interest.Max-Amount"));
            return new BigDecimal(0);
        }
        return new BigDecimal(interestMaxAmount);
    }

    public String getInterestMoneyGivenString() {
        return interestMoneyGiven;
    }

    public BigDecimal getInterestMoneyGiven() {
        if (BPMethods.isInvalidNumber(interestMoneyGiven)) {
            BPLogger.error(DEF_ERROR.replace("%", "Interest.Money-Given"));
            return new BigDecimal(0);
        }
        return new BigDecimal(interestMoneyGiven.replace("%", ""));
    }

    public String getOfflineInterestMoneyGivenString() {
        return offlineInterestMoneyGiven;
    }

    public BigDecimal getOfflineInterestMoneyGiven() {
        if (BPMethods.isInvalidNumber(offlineInterestMoneyGiven)) {
            BPLogger.error(DEF_ERROR.replace("%", "Interest.Offline-Money-Given"));
            return new BigDecimal(0);
        }
        return new BigDecimal(offlineInterestMoneyGiven.replace("%", ""));
    }

    public String getBankUpgradedMaxPlaceholder() {
        return bankUpgradedMaxPlaceholder == null ? "&cMaxed" : bankUpgradedMaxPlaceholder;
    }

    public List<String> getWorldsBlacklist() {
        return worldsBlacklist;
    }

    public List<String> getExitCommands() {
        return exitCommands;
    }

    public boolean isReopeningBankAfterChat() {
        return isReopeningBankAfterChat;
    }

    public boolean isInterestEnabled() {
        return isInterestEnabled;
    }

    public boolean isNotifyOfflineInterest() {
        return isNotifyOfflineInterest;
    }

    public boolean isStoringUUIDs() {
        return isStoringUUIDs;
    }

    public boolean isGivingInterestToOfflinePlayers() {
        return isGivingInterestToOfflinePlayers;
    }

    public boolean isOfflineInterestDifferentRate() {
        return isOfflineInterestDifferentRate;
    }

    public boolean isOfflineInterestEarnedMessageEnabled() {
        return isOfflineInterestEarnedMessageEnabled;
    }

    public boolean isUpdateCheckerEnabled() {
        return isUpdateCheckerEnabled;
    }

    public boolean isWithdrawSoundEnabled() {
        return isWithdrawSoundEnabled;
    }

    public boolean isDepositSoundEnabled() {
        return isDepositSoundEnabled;
    }

    public boolean isViewSoundEnabled() {
        return isViewSoundEnabled;
    }

    public boolean isPersonalSoundEnabled() {
        return isPersonalSoundEnabled;
    }

    public boolean isIgnoringAfkPlayers() {
        return isIgnoringAfkPlayers;
    }

    public boolean isUseEssentialsXAFK() {
        return BankPlus.INSTANCE.isEssentialsXHooked() && useEssentialsXAFK;
    }

    public int getAfkPlayersTime() {
        return afkPlayersTime;
    }

    public int getMaxDecimalsAmount() {
        return Math.max(maxDecimalsAmount, 1);
    }

    public int getBankTopSize() {
        return bankTopSize;
    }

    public List<String> getBankTopFormat() {
        return bankTopFormat;
    }

    public long getSaveBalancedDelay() {
        return saveBalancedDelay;
    }

    public long getUpdateBankTopDelay() {
        return updateBankTopDelay;
    }

    public String getBankTopMoneyFormat() {
        return bankTopMoneyFormat;
    }

    public boolean isBanktopEnabled() {
        return banktopEnabled;
    }

    public String getBanktopUpdateBroadcastMessage() {
        return banktopUpdateBroadcastMessage;
    }

    public boolean isBanktopUpdateBroadcastEnabled() {
        return banktopUpdateBroadcastEnabled;
    }

    public boolean isBanktopUpdateBroadcastOnlyConsole() {
        return banktopUpdateBroadcastOnlyConsole;
    }

    public boolean isSaveBalancesBroadcast() {
        return saveBalancesBroadcast;
    }

    public boolean isGuiModuleEnabled() {
        return guiModuleEnabled;
    }

    public String getMainGuiName() {
        return mainGuiName == null ? "bank" : mainGuiName;
    }
}