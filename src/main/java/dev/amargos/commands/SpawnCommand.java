package dev.amargos.commands;

import dev.amargos.SpawnPlugin;
import dev.amargos.cooldown.CooldownManager;
import dev.amargos.utils.MessageUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SpawnCommand implements CommandExecutor {
    private final CooldownManager cooldownManager = SpawnPlugin.getInstance().getCooldownManager();

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return false;
        }

        Player player = (Player) sender;

        if (cooldownManager.isOnCooldown(player)) {
            player.sendMessage(MessageUtil.colorize(SpawnPlugin.getInstance().getConfig().getString("messages.teleporting")));
            return false;
        }

        if (!SpawnPlugin.getInstance().getConfig().getBoolean("spawn.enabled")) {
            player.sendMessage(MessageUtil.colorize(SpawnPlugin.getInstance().getConfig().getString("messages.no-spawn")));
            return false;
        }

        Location spawnLocation = new Location(
                Bukkit.getWorld(SpawnPlugin.getInstance().getConfig().getString("spawn.world")),
                SpawnPlugin.getInstance().getConfig().getDouble("spawn.x"),
                SpawnPlugin.getInstance().getConfig().getDouble("spawn.y"),
                SpawnPlugin.getInstance().getConfig().getDouble("spawn.z")
        );
        player.teleport(spawnLocation);

        if (SpawnPlugin.getInstance().getConfig().getBoolean("sound.enabled")) {
            player.playSound(player.getLocation(), Sound.valueOf(SpawnPlugin.getInstance().getConfig().getString("sound.sound")), 1.0f, 1.0f);
        }

        player.sendMessage(MessageUtil.colorize(SpawnPlugin.getInstance().getConfig().getString("messages.teleporting")));

        cooldownManager.setCooldown(player);
        return true;
    }
}
