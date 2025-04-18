package dev.amargos.commands;

import dev.amargos.SpawnPlugin;
import dev.amargos.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class SpawnReloadCommand implements CommandExecutor {

    private final SpawnPlugin plugin;

    public SpawnReloadCommand(SpawnPlugin plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("spawnplugin.reload")) {
            sender.sendMessage(MessageUtil.getMessage("no-permission"));
            return true;
        }

        plugin.reloadConfig();
        sender.sendMessage(MessageUtil.getMessage("reload-config"));
        return true;
    }
}