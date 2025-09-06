package com.ma4z.rewardplugin;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class DataFile {

    private static DataFile instance;
    private File file;
    private FileConfiguration data;

    public static DataFile getInstance() {
        if (instance == null) instance = new DataFile();
        return instance;
    }

    public void setup(JavaPlugin plugin) {
        file = new File(plugin.getDataFolder(), "data.yml");
        if (!file.exists()) {
            plugin.saveResource("data.yml", false);
        }
        data = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return data;
    }

    public void saveConfig() {
        try {
            data.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
