package dev.amargos;

import dev.amargos.commands.DelSpawnCommand;
import dev.amargos.commands.SetSpawnCommand;
import dev.amargos.commands.SpawnCommand;
import dev.amargos.commands.SpawnReloadCommand;
import dev.amargos.config.ConfigManager;
import dev.amargos.cooldown.CooldownManager;
import dev.amargos.utils.MessageUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class SpawnPlugin extends JavaPlugin {

    private static SpawnPlugin instance;
    private FileConfiguration config = getConfig();
    private String language;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        instance = this;

        ConfigManager.loadConfig();
        cooldownManager = new CooldownManager();

        registerCommands();
        registerEventListeners();

        try {
            MessageUtil.loadMessages();
        } catch (Exception e) {
            getLogger().severe("Failed to load messages.yml!");
            e.printStackTrace();
            getServer().getPluginManager().disablePlugin(this);
            return;
        }


        getLogger().info("============= [SpawnPlugin] =============");
        getLogger().info("The plugin has been successfully enabled!");
        getLogger().info("Registered commands: /spawn, /setspawn, /delspawn, /spawnreload");
        getLogger().info("Managers: ConfigManager, CooldownManager, MessageUtil");
        getLogger().info("=========================================");
    }

    private void registerCommands() {
        getCommand("spawnreload").setExecutor(new SpawnReloadCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("delspawn").setExecutor(new DelSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());

        getLogger().info("Commands registered: /spawn, /setspawn, /delspawn, /spawnreload");
    }

    private void registerEventListeners() {
        getLogger().info("Currently no event listeners to register.");
    }

    @Override
    public void onDisable() {
        getLogger().info("=========================================");
        getLogger().info("[SpawnPlugin] Plugin has been disabled. Goodbye!");
        getLogger().info("=========================================");
    }

    public static SpawnPlugin getInstance() {
        return instance;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }
}