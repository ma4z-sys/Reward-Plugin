package com.ma4z.rewardplugin;

import net.milkbowl.vault.economy.Economy;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class RewardPlugin extends JavaPlugin {

    private static RewardPlugin instance;
    private Economy econ;
    private LuckPerms luckPerms;
    private DataFile dataFile; 

    @Override
    public void onEnable() {
        instance = this;

        saveDefaultConfig();

        dataFile = DataFile.getInstance();
        dataFile.setup(this);

        if (!setupEconomy()) {
            getLogger().severe("Vault not found or no economy plugin installed!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        if (!setupLuckPerms()) {
            getLogger().severe("LuckPerms not found!");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        getCommand("new-reward").setExecutor(new Commands());
        getCommand("delete-reward").setExecutor(new Commands());
        getCommand("edit-reward").setExecutor(new Commands());

        getLogger().info("RewardPlugin enabled!");
    }

    public static RewardPlugin getInstance() {
        return instance;
    }

    public Economy getEconomy() {
        return econ;
    }

    public LuckPerms getLuckPerms() {
        return luckPerms;
    }

    public DataFile getDataFile() {
        return dataFile;
    }

    private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) return false;
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) return false;
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupLuckPerms() {
        try {
            luckPerms = LuckPermsProvider.get();
            return luckPerms != null;
        } catch (Exception e) {
            return false;
        }
    }
}
