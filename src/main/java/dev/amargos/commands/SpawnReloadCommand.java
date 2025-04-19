package dev.amargos.commands;

import dev.amargos.SpawnPlugin;
import dev.amargos.utils.MessageUtil;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnReloadCommand implements CommandExecutor {

    private final SpawnPlugin plugin;

    public SpawnReloadCommand(SpawnPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "This command can only be executed by a player.");
            return true;
        }

        if (!command.getName().equalsIgnoreCase("spawnreload")) {
            return false;
        }

        if (!sender.isOp() && !sender.hasPermission("spawnplugin.reload")) {
            sender.sendMessage(MessageUtil.getMessage("no-permission"));
            return true;
        }

        plugin.reloadConfig();
        try {
            MessageUtil.loadMessages();
        } catch (Exception e) {
            sender.sendMessage(ChatColor.RED + "Failed to reload messages.yml! Check console for errors.");
            e.printStackTrace();
            return true;
        }

        sender.sendMessage(MessageUtil.getMessage("reload-config"));
        return true;
    }
}