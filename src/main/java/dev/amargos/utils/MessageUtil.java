package dev.amargos.utils;

import dev.amargos.SpawnPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class MessageUtil {
    private static FileConfiguration languageConfig;

    public static void loadMessages() {
        String lang = SpawnPlugin.getInstance().getConfig().getString("language", "messages-en");
        File langFile = new File(SpawnPlugin.getInstance().getDataFolder(), "lang/" + lang + ".yml");

        if (!langFile.exists()) {
            SpawnPlugin.getInstance().saveResource("lang/" + lang + ".yml", false);
        }

        languageConfig = YamlConfiguration.loadConfiguration(langFile);
    }

    public static String getMessage(String path) {
        return colorize(languageConfig.getString(path, "&cMessage not found: " + path));
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
