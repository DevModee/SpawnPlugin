package dev.amargos.cooldown;

import dev.amargos.SpawnPlugin;
import dev.amargos.utils.MessageUtil;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public boolean isOnCooldown(Player player) {
        UUID playerId = player.getUniqueId();
        if (cooldowns.containsKey(playerId)) {
            long remainingTime = cooldowns.get(playerId) - System.currentTimeMillis();
            if (remainingTime > 0) {
                return true;
            }
            cooldowns.remove(playerId);
        }
        return false;
    }

    public void setCooldown(Player player) {
        long cooldownSeconds = SpawnPlugin.getInstance().getConfig().getLong("cooldown.seconds");
        long cooldownTime = System.currentTimeMillis() + (cooldownSeconds * 1000);
        cooldowns.put(player.getUniqueId(), cooldownTime);
    }

    public long getRemainingTime(Player player) {
        UUID playerId = player.getUniqueId();
        if (cooldowns.containsKey(playerId)) {
            long remainingTime = (cooldowns.get(playerId) - System.currentTimeMillis()) / 1000;
            if (remainingTime > 0) {
                return remainingTime;
            }
        }
        return -1;
    }

    public String getCooldownMessage(Player player) {
        if (isOnCooldown(player)) {
            long remaining = getRemainingTime(player);
            return MessageUtil.getMessage("cooldown").replace("{seconds}", String.valueOf(remaining));
        }
        return null;
    }
}