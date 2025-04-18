package dev.amargos.commands;

import dev.amargos.SpawnPlugin;
import dev.amargos.utils.MessageUtil;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class SetSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(MessageUtil.getMessage("not-player"));
            return true;
        }

        Player player = (Player) sender;

        if (!player.isOp() && !sender.hasPermission("spawnplugin.set")) {
            sender.sendMessage(MessageUtil.getMessage("no-permission"));
            return true;
        }

        Location location = player.getLocation();

        if (location.getWorld() == null) {
            player.sendMessage(MessageUtil.getMessage("no-world"));
            return true;
        }

        SpawnPlugin.getInstance().getConfig().set("spawn.world", location.getWorld().getName());
        SpawnPlugin.getInstance().getConfig().set("spawn.x", location.getX());
        SpawnPlugin.getInstance().getConfig().set("spawn.y", location.getY());
        SpawnPlugin.getInstance().getConfig().set("spawn.z", location.getZ());
        SpawnPlugin.getInstance().getConfig().set("spawn.yaw", location.getYaw());
        SpawnPlugin.getInstance().getConfig().set("spawn.pitch", location.getPitch());
        SpawnPlugin.getInstance().getConfig().set("spawn.enabled", true);
        SpawnPlugin.getInstance().saveConfig();

        player.sendMessage(MessageUtil.getMessage("spawn-set"));
        return true;
    }
}