package dev.amargos;

import dev.amargos.commands.DelSpawnCommand;
import dev.amargos.commands.SetSpawnCommand;
import dev.amargos.commands.SpawnCommand;
import dev.amargos.config.ConfigManager;
import dev.amargos.cooldown.CooldownManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnPlugin extends JavaPlugin {

    private static SpawnPlugin instance;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        instance = this;
        saveConfig();
        ConfigManager.loadConfig();

        cooldownManager = new CooldownManager();

        // Register commands
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("delspawn").getExecutor(new DelSpawnCommand());
        getCommand("spawn").getExecutor(new SpawnCommand));

        // Register events
        getLogger().info("Plugin enabled");
    }

    @Override
    public void onDisable() {
        getLogger().info("Plugin disabled");
    }

    public static SpawnPlugin getInstance() {
        return instance;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }
}