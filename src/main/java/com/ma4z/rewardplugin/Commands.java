package com.ma4z.rewardplugin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class Commands implements CommandExecutor {

    private final RewardPlugin plugin = RewardPlugin.getInstance();
    private final RewardManager manager = new RewardManager();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!sender.hasPermission("rpl4z.staff")) {
            sender.sendMessage(ChatColor.RED + "No permission!");
            return true;
        }

        switch (command.getName().toLowerCase()) {
            case "new-reward" -> {
                if (args.length < 6) return false;

                String id = args[5];
                plugin.getDataFile().getConfig().set("rewards." + id + ".name", args[0]);
                plugin.getDataFile().getConfig().set("rewards." + id + ".description", args[1]);
                plugin.getDataFile().getConfig().set("rewards." + id + ".action", args[2]);
                plugin.getDataFile().getConfig().set("rewards." + id + ".requirement", args[3]);
                plugin.getDataFile().getConfig().set("rewards." + id + ".details", args[4]);
                plugin.getDataFile().saveConfig();

                sender.sendMessage(ChatColor.GREEN + "Reward " + args[0] + " created!");
            }
            case "delete-reward" -> {
                if (args.length != 1) return false;
                plugin.getDataFile().getConfig().set("rewards." + args[0], null);
                plugin.getDataFile().saveConfig();
                sender.sendMessage(ChatColor.GREEN + "Reward " + args[0] + " deleted!");
            }
            case "edit-reward" -> sender.sendMessage(ChatColor.RED + "Editing not fully implemented yet.");
        }
        return true;
    }
}
