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

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return false;
        }

        Player player = (Player) sender;

        String cooldownMessage = cooldownManager.getCooldownMessage(player);
        if (cooldownMessage != null) {
            player.sendMessage(cooldownMessage);
            return false;
        }

        if (!SpawnPlugin.getInstance().getConfig().getBoolean("spawn.enabled")) {
            player.sendMessage(MessageUtil.getMessage("no-spawn"));
            return false;
        }

        Location spawnLocation = new Location(
                Bukkit.getWorld(SpawnPlugin.getInstance().getConfig().getString("spawn.world")),
                SpawnPlugin.getInstance().getConfig().getDouble("spawn.x"),
                SpawnPlugin.getInstance().getConfig().getDouble("spawn.y"),
                SpawnPlugin.getInstance().getConfig().getDouble("spawn.z"),
                (float) SpawnPlugin.getInstance().getConfig().getDouble("spawn.yaw"),
                (float) SpawnPlugin.getInstance().getConfig().getDouble("spawn.pitch")
        );

        player.teleport(spawnLocation);

        if (SpawnPlugin.getInstance().getConfig().getBoolean("sound.enabled")) {
            try {
                String soundName = SpawnPlugin.getInstance().getConfig().getString("sound.sound").toUpperCase();
                Sound sound = Sound.valueOf(soundName);
                player.playSound(player.getLocation(), sound, 1.0f, 1.0f);
            } catch (IllegalArgumentException e) {
                Bukkit.getLogger().warning("[SpawnPlugin] Invalid sound in config.yml: "
                        + SpawnPlugin.getInstance().getConfig().getString("sound.sound"));
                e.printStackTrace();
            }
        }

        player.sendMessage(MessageUtil.getMessage("teleporting"));

        cooldownManager.setCooldown(player);

        return true;
    }
}