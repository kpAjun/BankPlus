package me.pulsi_.bankplus.bankGuis;

import me.pulsi_.bankplus.BankPlus;
import me.pulsi_.bankplus.account.BankPlusPlayerFiles;
import me.pulsi_.bankplus.account.economy.MultiEconomyManager;
import me.pulsi_.bankplus.account.economy.SingleEconomyManager;
import me.pulsi_.bankplus.bankGuis.objects.Bank;
import me.pulsi_.bankplus.utils.BPLogger;
import me.pulsi_.bankplus.utils.BPMessages;
import me.pulsi_.bankplus.utils.BPMethods;
import me.pulsi_.bankplus.values.Values;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to receive information from the selected bank and many more usefully methods to manage the bank and the player.
 */
public class BankReader {
    
    private final Bank bank;

    /**
     * This constructor is used for methods that doesn't require to select a bank, for example #getAvailableBanks();
     * If you try to access methods that requires the bank object with this constructor you'll get a lot of errors.
     */
    public BankReader() {
        this.bank = null;
    }

    public BankReader(String bankName) {
        this.bank = BankPlus.INSTANCE.getBankGuiRegistry().get(bankName);
    }

    public boolean exist() {
        return bank != null;
    }

    public File getFile() {
        return bank.getBankFile();
    }

    public FileConfiguration getConfig() {
        return bank.getBankConfig();
    }

    public String getTitle() {
        return bank.getTitle();
    }

    public int getLines() {
        return bank.getSize();
    }

    public long getUpdateDelay() {
        return bank.getUpdateDelay();
    }

    public boolean isFillerEnabled() {
        return bank.hasFiller();
    }

    public String getFillerMaterial() {
        return bank.getFillerMaterial();
    }

    public boolean isFillerGlowing() {
        return bank.isFillerGlowing();
    }

    public boolean hasPermissionSection() {
        return bank.getPermission() != null;
    }

    public String getPermission() {
        return bank.getPermission();
    }

    public ConfigurationSection getBanksGuiItemSection() {
        return bank.getBanksListGuiItems();
    }

    public boolean hasUpgrades() {
        return bank.getUpgrades() != null;
    }

    public ConfigurationSection getUpgrades() {
        return bank.getUpgrades();
    }

    public boolean hasItems() {
        return bank.getItems() != null;
    }

    public ConfigurationSection getItems() {
        return bank.getItems();
    }

    public boolean hasSettings() {
        return bank.getSettings() != null;
    }

    public ConfigurationSection getSettings() {
        return bank.getSettings();
    }

    /**
     * Get the current bank capacity based on the level of the bank of this player.
     * @param p The player
     * @return A BigDecimal number representing the capacity.
     */
    public BigDecimal getCapacity(Player p) {
        if (!hasUpgrades()) return Values.CONFIG.getMaxBankCapacity();

        FileConfiguration config = new BankPlusPlayerFiles(p).getPlayerConfig();
        int level = Math.max(config.getInt("Banks." + bank.getIdentifier() + ".Level"), 1);
        String capacity = getUpgrades().getString(level + ".Capacity");
        return new BigDecimal(capacity == null ? Values.CONFIG.getMaxBankCapacity().toString() : capacity);
    }

    /**
     * Get the current bank capacity based on the level of the bank of this player.
     * @param p The player
     * @return A BigDecimal number representing the capacity.
     */
    public BigDecimal getCapacity(OfflinePlayer p) {
        if (!hasUpgrades()) return Values.CONFIG.getMaxBankCapacity();

        FileConfiguration config = new BankPlusPlayerFiles(p).getPlayerConfig();
        int level = Math.max(config.getInt("Banks." + bank.getIdentifier() + ".Level"), 1);
        String capacity = getUpgrades().getString(level + ".Capacity");
        return new BigDecimal(capacity == null ? Values.CONFIG.getMaxBankCapacity().toString() : capacity);
    }

    /**
     * Get the custom interest amount different from the one selected in the config based on the bank level.
     * @param p The player
     * @return A BigDecimal number representing the interest rate.
     */
    public BigDecimal getInterest(Player p) {
        if (!hasUpgrades()) return Values.CONFIG.getInterestMoneyGiven();

        FileConfiguration config = new BankPlusPlayerFiles(p).getPlayerConfig();
        int level = Math.max(config.getInt("Banks." + bank.getIdentifier() + ".Level"), 1);
        String interest = getUpgrades().getString(level + ".Interest");

        if (BPMethods.isInvalidNumber(interest)) {
            if (interest != null) BPLogger.error("Invalid interest amount in the " + level + "* upgrades section, file: " + bank.getIdentifier() + ".yml");
            return Values.CONFIG.getInterestMoneyGiven();
        }

        return new BigDecimal(interest.replace("%", ""));
    }

    /**
     * Get the custom interest amount different from the one selected in the config based on the bank level.
     * @param p The player
     * @return A BigDecimal number representing the interest rate.
     */
    public BigDecimal getInterest(OfflinePlayer p) {
        if (!hasUpgrades()) return Values.CONFIG.getInterestMoneyGiven();

        FileConfiguration config = new BankPlusPlayerFiles(p).getPlayerConfig();
        int level = Math.max(config.getInt("Banks." + bank.getIdentifier() + ".Level"), 1);
        String interest = getUpgrades().getString(level + ".Interest");

        if (BPMethods.isInvalidNumber(interest)) {
            if (interest != null) BPLogger.error("Invalid interest amount in the " + level + "* upgrades level, file: " + bank.getIdentifier() + ".yml");
            return Values.CONFIG.getInterestMoneyGiven();
        }

        return new BigDecimal(interest.replace("%", ""));
    }

    /**
     * Get the custom offline interest amount different from the one selected in the config based on the bank level.
     * @param p The player
     * @return A BigDecimal number representing the offline interest rate.
     */
    public BigDecimal getOfflineInterest(Player p) {
        if (!hasUpgrades()) return Values.CONFIG.getOfflineInterestMoneyGiven();

        FileConfiguration config = new BankPlusPlayerFiles(p).getPlayerConfig();
        int level = Math.max(config.getInt("Banks." + bank.getIdentifier() + ".Level"), 1);
        String interest = getUpgrades().getString(level + ".Offline-Interest");

        if (BPMethods.isInvalidNumber(interest)) {
            if (interest != null) BPLogger.error("Invalid offline interest amount in the " + level + "* upgrades section, file: " + bank.getIdentifier() + ".yml");
            return Values.CONFIG.getOfflineInterestMoneyGiven();
        }

        return new BigDecimal(interest.replace("%", ""));
    }

    /**
     * Get the custom offline interest amount different from the one selected in the config based on the bank level.
     * @param p The player
     * @return A BigDecimal number representing the offline interest rate.
     */
    public BigDecimal getOfflineInterest(OfflinePlayer p) {
        if (!hasUpgrades()) return Values.CONFIG.getOfflineInterestMoneyGiven();

        FileConfiguration config = new BankPlusPlayerFiles(p).getPlayerConfig();
        int level = Math.max(config.getInt("Banks." + bank.getIdentifier() + ".Level"), 1);
        String interest = getUpgrades().getString(level + ".Offline-Interest");

        if (BPMethods.isInvalidNumber(interest)) {
            if (interest != null) BPLogger.error("Invalid offline interest amount in the " + level + "* upgrades level, file: " + bank.getIdentifier() + ".yml");
            return Values.CONFIG.getOfflineInterestMoneyGiven();
        }

        return new BigDecimal(interest.replace("%", ""));
    }

    /**
     * Get the cost of the selected level of this bank.
     * @param level The level to check.
     * @return A BigDecimal number representing the level cost.
     */
    public BigDecimal getLevelCost(int level) {
        if (!hasUpgrades()) return new BigDecimal(0);

        String cost = getUpgrades().getString(level + ".Cost");
        return new BigDecimal(cost == null ? "0" : cost);
    }

    /**
     * Get a list of all levels that this bank have, if it has none, it will return a list of 1.
     * @return A string list with all levels.
     */
    public List<String> getLevels() {
        List<String> levels = new ArrayList<>();
        if (!hasUpgrades()) {
            levels.add("1");
            return levels;
        }

        levels.addAll(getUpgrades().getKeys(false));
        return levels;
    }

    /**
     * Get the current bank level for that player.
     * @param p The player.
     * @return The current level.
     */
    public int getCurrentLevel(Player p) {
        FileConfiguration config = new BankPlusPlayerFiles(p).getPlayerConfig();
        return Math.max(config.getInt("Banks." + bank.getIdentifier() + ".Level"), 1);
    }

    /**
     * Check if the bank of that player has a next level.
     * @param p The player.
     * @return true if it has a next level, false otherwise.
     */
    public boolean hasNextLevel(Player p) {
        ConfigurationSection section = getUpgrades();
        if (section == null) return false;

        return section.getConfigurationSection(String.valueOf(getCurrentLevel(p) + 1)) != null;
    }

    /**
     * Check if the selected bank has a next level
     * @param currentLevel The current level of the bank.
     * @return true if it has a next level, false otherwise.
     */
    public boolean hasNextLevel(int currentLevel) {
        ConfigurationSection section = getUpgrades();
        if (section == null) return false;

        return section.getConfigurationSection(String.valueOf(currentLevel + 1)) != null;
    }

    /**
     * This method does not require to specify a bank in the constructor.
     * @param p The player.
     * @return A list with all names of available banks for this player. To get a list of ALL banks use the BanksGuiRegistry class through the main class.
     */
    public List<String> getAvailableBanks(Player p) {
        List<String> availableBanks = new ArrayList<>();
        for (String bankName : BankPlus.INSTANCE.getBankGuiRegistry().getBanks().keySet()) {
            if (new BankReader(bankName).isAvailable(p)) availableBanks.add(bankName);
        }
        return availableBanks;
    }

    /**
     * This method does not require to specify a bank in the constructor.
     * @param p The player.
     * @return A list with all names of available banks for this player. To get a list of ALL banks use the BanksGuiRegistry class through the main class.
     */
    public List<String> getAvailableBanks(OfflinePlayer p) {
        List<String> availableBanks = new ArrayList<>();
        for (String bankName : BankPlus.INSTANCE.getBankGuiRegistry().getBanks().keySet()) {
            if (new BankReader(bankName).isAvailable(p)) availableBanks.add(bankName);
        }
        return availableBanks;
    }

    /**
     * Check if this bank is available for the player.
     * @param p The player
     * @return true if available, false otherwise.
     */
    public boolean isAvailable(Player p) {
        return !hasPermissionSection() || p.hasPermission(getPermission());
    }

    /**
     * Check if this bank is available for the player.
     * @param p The player
     * @return true if available, false otherwise.
     */
    public boolean isAvailable(OfflinePlayer p) {
        if (!hasPermissionSection()) return true;
        else {
            String wName = Bukkit.getWorlds().get(0).getName();
            return BankPlus.INSTANCE.getPermissions().playerHas(wName, p, getPermission());
        }
    }

    /**
     * Method used to upgrade the selected bank for the specified player.
     * @param p The player.
     */
    public void upgradeBank(Player p) {
        if (!hasNextLevel(p)) {
            BPMessages.send(p, "Bank-Max-Level");
            return;
        }
        BigDecimal balance;

        SingleEconomyManager singleEconomyManager = new SingleEconomyManager(p);
        MultiEconomyManager multiEconomyManager = new MultiEconomyManager(p);

        if (Values.MULTIPLE_BANKS.isMultipleBanksModuleEnabled()) balance = multiEconomyManager.getBankBalance();
        else balance = singleEconomyManager.getBankBalance();

        int level = getCurrentLevel(p);
        BigDecimal cost = getLevelCost(level + 1);
        if (balance.doubleValue() < cost.doubleValue()) {
            BPMessages.send(p, "Insufficient-Money");
            return;
        }

        if (Values.MULTIPLE_BANKS.isMultipleBanksModuleEnabled()) multiEconomyManager.removeBankBalance(cost, bank.getIdentifier());
        else singleEconomyManager.removeBankBalance(cost);

        BankPlusPlayerFiles files = new BankPlusPlayerFiles(p);
        files.getPlayerConfig().set("Banks." + bank.getIdentifier() + ".Level", level + 1);
        files.savePlayerFile(true);

        BPMessages.send(p, "Bank-Upgraded");
    }

    /**
     * Get the bank specified in the constructor.
     * @return The bank object.
     */
    public Bank getBank() {
        return bank;
    }
}