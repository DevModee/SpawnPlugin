package dev.amargos.api;

import dev.amargos.SpawnPlugin;
import org.bukkit.Location;

public class SpawnAPI {

    public static Location getSpawnLocation() {
        return new Location(
                SpawnPlugin.getInstance().getServer().getWorld(SpawnPlugin.getInstance().getConfig().getString("spawn.world")),
                SpawnPlugin.getInstance().getConfig().getDouble("spawn.x"),
                SpawnPlugin.getInstance().getConfig().getDouble("spawn.y"),
                SpawnPlugin.getInstance().getConfig().getDouble("spawn.z")
        );
    }

    public static boolean isSpawnEnabled() {
        return SpawnPlugin.getInstance().getConfig().getBoolean("spawn.enabled");
    }
}
