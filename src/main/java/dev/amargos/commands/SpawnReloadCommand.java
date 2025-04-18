package dev.amargos.commands;

import dev.amargos.SpawnPlugin;
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
        if (sender.hasPermission("spawnplugin.reload")) {
            plugin.reloadPlugin();
            sender.sendMessage(plugin.getMessage("reload-config"));
            return true;
        } else {
            sender.sendMessage(plugin.getMessage("no-permission"));
            return false;
        }
    }
}
