package dev.amargos;

import dev.amargos.commands.DelSpawnCommand;
import dev.amargos.commands.SetSpawnCommand;
import dev.amargos.commands.SpawnCommand;
import dev.amargos.commands.SpawnReloadCommand;
import dev.amargos.config.ConfigManager;
import dev.amargos.cooldown.CooldownManager;
import dev.amargos.utils.MessageUtil;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class SpawnPlugin extends JavaPlugin {

    private static SpawnPlugin instance;
    private FileConfiguration config = getConfig();
    private String language;
    private CooldownManager cooldownManager;

    @Override
    public void onEnable() {
        instance = this;
        saveConfig();
        language = config.getString("lang", "en");
        ConfigManager.loadConfig();
        MessageUtil.loadMessages();

        cooldownManager = new CooldownManager();

        // Register commands
        getCommand("spawnreload").setExecutor(new SpawnReloadCommand(this));
        getCommand("setspawn").setExecutor(new SetSpawnCommand());
        getCommand("delspawn").setExecutor(new DelSpawnCommand());
        getCommand("spawn").setExecutor(new SpawnCommand());

        // Register events
        getLogger().info("§a[SpawnPlugin] §fThe plugin has been successfully enabled! §aReady to teleport players to their custom spawn locations!");

    }

    private void loadLanguageMessages() {
        File langFile = new File(getDataFolder(), "lang/messages-" + language + ".yml");
        if (!langFile.exists()) {
            getLogger().warning("Language file " + langFile.getName() + "not found. Falling back to English.");
            language = "en"; // Fallback to English if no file is found
            langFile = new File(getDataFolder(), "lang/messages-en.yml");
        }

        try {
            YamlConfiguration langConfig = YamlConfiguration.loadConfiguration(langFile);
            getConfig().setDefaults(langConfig);
        } catch (Exception e) {
            getLogger().severe("Could not load language file " + langFile.getName());
        }
    }

    public String getMessage(String path) {
        return MessageUtil.getMessage(path);
    }

    public void reloadPlugin() {
        reloadConfig();
        getLogger().info("Plugin reloaded");
        MessageUtil.loadMessages();

    }

    @Override
    public void onDisable() {
        getLogger().info("§c[SpawnPlugin] §fThe plugin has been disabled. §cGoodbye!");
    }

    public static SpawnPlugin getInstance() {
        return instance;
    }

    public CooldownManager getCooldownManager() {
        return cooldownManager;
    }
}