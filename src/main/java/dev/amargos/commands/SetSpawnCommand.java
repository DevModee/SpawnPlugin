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
            sender.sendMessage("Only players can execute this command.");
            return false;
        }

        Player player = (Player) sender;

        Location location = player.getLocation();
        SpawnPlugin.getInstance().getConfig().set("spawn.world", location.getWorld().getName);
        SpawnPlugin.getInstance().getConfig().set("spawn.x", location.getX());
        SpawnPlugin.getInstance().getConfig().set("spawn.y", location.getY());
        SpawnPlugin.getInstance().getConfig().set("spawn.z", location.getZ());
        SpawnPlugin.getInstance().getConfig().set("spawn.yaw", location.getYaw());
        SpawnPlugin.getInstance().getConfig().set("spawn.pitch", location.getPitch());
        SpawnPlugin.getInstance().getConfig().set("spawn.enabled", true);
        SpawnPlugin.getInstance().saveConfig();

        player.sendMessage(MessageUtil.colorize(SpawnPlugin.getInstance().getInstance().getConfig().getString("messages.spawn-set")));
        return true;
    }
}
