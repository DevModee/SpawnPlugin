package dev.amargos.config;

import dev.amargos.SpawnPlugin;

import java.io.File;

public class ConfigManager {

    private static final SpawnPlugin plugin = SpawnPlugin.getInstance();

    public static void loadConfig() {
        File configFile = new File(plugin.getDataFolder(), "config.yml");
        if (!configFile.exists()) {
            plugin.saveDefaultConfig();
            plugin.getLogger().info("Default config.yml created.");
        } else {
            plugin.reloadConfig();
            plugin.getLogger().info("Existing config.yml loaded.");
        }
        enforceConfigDefaults(); // Aplica valores por defecto si faltan claves
        plugin.saveConfig();     // Guarda la configuración actualizada
        setupLanguageFiles();    // Asegura que existan los archivos de idioma
    }

    private static void enforceConfigDefaults() {
        plugin.getConfig().addDefault("spawn.enabled", false);
        plugin.getConfig().addDefault("spawn.world", "world");
        plugin.getConfig().addDefault("spawn.x", 0.0);
        plugin.getConfig().addDefault("spawn.y", 64.0);
        plugin.getConfig().addDefault("spawn.z", 0.0);
        plugin.getConfig().addDefault("spawn.yaw", 0.0);
        plugin.getConfig().addDefault("spawn.pitch", 0.0);

        plugin.getConfig().addDefault("sound.enabled", true);
        plugin.getConfig().addDefault("sound.sound", "ENDERMAN_TELEPORT");

        plugin.getConfig().addDefault("cooldown.seconds", 5);

        plugin.getConfig().addDefault("lang", "en");

        plugin.getConfig().options().copyDefaults(true); // Copia los defaults si no existen
    }

    private static void setupLanguageFiles() {
        File langFolder = new File(plugin.getDataFolder(), "lang");
        if (!langFolder.exists()) {
            langFolder.mkdirs();
        }

        File enFile = new File(langFolder, "messages-en.yml");
        if (!enFile.exists()) {
            plugin.saveResource("lang/messages-en.yml", false);
        }

        File esFile = new File(langFolder, "messages-es.yml");
        if (!esFile.exists()) {
            plugin.saveResource("lang/messages-es.yml", false);
        }
    }
}
