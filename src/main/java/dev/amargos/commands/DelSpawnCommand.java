package dev.amargos.commands;

import dev.amargos.SpawnPlugin;
import dev.amargos.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtil.getMessage("not-player"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp() && !sender.hasPermission("spawnplugin.del")) {
            sender.sendMessage(MessageUtil.getMessage("no-permission"));
            return true;
        }

        if (!SpawnPlugin.getInstance().getConfig().getBoolean("spawn.enabled")) {
            player.sendMessage(MessageUtil.getMessage("no-spawn"));
            return true;
        }

        SpawnPlugin.getInstance().getConfig().set("spawn.enabled", false);
        SpawnPlugin.getInstance().saveConfig();

        player.sendMessage(MessageUtil.getMessage("spawn-deleted"));
        return true;
    }
}