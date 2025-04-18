package dev.amargos.config;

import dev.amargos.SpawnPlugin;

public class ConfigManager {

    public static void loadConfig() {
        if (!SpawnPlugin.getInstance().getConfig().contains("messages.spawn-set")) {
            SpawnPlugin.getInstance().getConfig().set("messages.spawn-set", "&a¡Spawn establecido correctamente!");
        }
        if (!SpawnPlugin.getInstance().getConfig().contains("messages.spawn-deleted")) {
            SpawnPlugin.getInstance().getConfig().set("messages.spawn-deleted", "&cSpawn eliminado.");
        }
        if (!SpawnPlugin.getInstance().getConfig().contains("messages.teleporting")) {
            SpawnPlugin.getInstance().getConfig().set("messages.teleporting", "&aTeleportando al spawn...");
        }
        if (!SpawnPlugin.getInstance().getConfig().contains("messages.no-spawn")) {
            SpawnPlugin.getInstance().getConfig().set("messages.no-spawn", "&cNo hay un spawn configurado.");
        }
        SpawnPlugin.getInstance().saveConfig();
    }
}
