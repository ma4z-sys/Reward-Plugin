package com.ma4z.rewardplugin;

import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class RewardManager {

    private final RewardPlugin plugin = RewardPlugin.getInstance();

    public void giveReward(Player player, String rewardId) {
        if (!plugin.getDataFile().getConfig().contains("rewards." + rewardId)) return;

        var section = plugin.getDataFile().getConfig().getConfigurationSection("rewards." + rewardId);
        String action = section.getString("action");

        switch (action) {
            case "ADD-MONEY" -> {
                double amount = section.getDouble("details.amount");
                plugin.getEconomy().depositPlayer(player, amount);
                player.sendMessage(plugin.getConfig().getString("prefix") + " You received $" + amount);
            }
            case "SET-RANK" -> {
                String rank = section.getString("details.rank");
                User user = plugin.getLuckPerms().getUserManager().getUser(player.getUniqueId());
                if (user != null) {
                    var node = InheritanceNode.builder(rank).build();
                    user.data().add(node);
                    plugin.getLuckPerms().getUserManager().saveUser(user);
                    player.sendMessage(plugin.getConfig().getString("prefix") + " You received the rank " + rank);
                }
            }
        }
    }
}
