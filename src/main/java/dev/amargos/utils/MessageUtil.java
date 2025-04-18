package dev.amargos.utils;

import dev.amargos.SpawnPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MessageUtil {

    private static FileConfiguration languageConfig;
    private static final Map<String, String> cache = new HashMap<>();

    public static void loadMessages() {
        String lang = SpawnPlugin.getInstance().getConfig().getString("lang", "messages-en");
        File langFile = new File(SpawnPlugin.getInstance().getDataFolder(), "lang/" + lang + ".yml");

        if (!langFile.exists()) {
            SpawnPlugin.getInstance().saveResource("lang/" + lang + ".yml", false);
        }

        languageConfig = YamlConfiguration.loadConfiguration(langFile);
    }

    public static String getMessage(String path) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }

        String msg = languageConfig.getString(path);

        if (msg == null || msg.trim().isEmpty()) {
            msg = "&c[Missing message: " + path + "]";
            SpawnPlugin.getInstance().getLogger().warning("Message missing for: " + path);
        }

        msg = colorize(msg);
        cache.put(path, msg);
        return msg;
    }

    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}