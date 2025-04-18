package dev.amargos.cooldown;

import dev.amargos.SpawnPlugin;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CooldownManager {

    private final Map<UUID, Long> cooldowns = new HashMap<>();

    public boolean isOnCooldown(Player player) {
        return cooldowns.containsKey(player.getUniqueId()) &&
                cooldowns.get(player.getUniqueId()) > System.currentTimeMillis();
    }

    public void setCooldown(Player player) {
        long cooldownTime = System.currentTimeMillis() + (SpawnPlugin.getInstance().getConfig().getLong("messages.cooldown.seconds") * 1000);
        cooldowns.put(player.getUniqueId(), cooldownTime);
    }
}
