package dev.amargos;

import org.bukkit.plugin.java.JavaPlugin;

public class SpawnPlugin extends JavaPlugin {

    private static SpawnPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }

    public static SpawnPlugin getInstance() {
        return instance;
    }
}