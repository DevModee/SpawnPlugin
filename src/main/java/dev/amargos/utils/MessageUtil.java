package dev.amargos.utils;

import dev.amargos.SpawnPlugin;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class MessageUtil {

    private static FileConfiguration messagesConfig;
    private static final Map<String, String> cache = new HashMap<>();

    public static void loadMessages() throws Exception{
        File messagesFile = new File(SpawnPlugin.getInstance().getDataFolder(), "messages.yml");

        if (!messagesFile.exists()) {
            SpawnPlugin.getInstance().saveResource("messages.yml", false);
        }

        messagesConfig = YamlConfiguration.loadConfiguration(messagesFile);
        SpawnPlugin.getInstance().getLogger().info("Messages successfully reloaded.");

        if(messagesConfig.getKeys(false).isEmpty()){
            throw new Exception("No messages found in messages.yml!");
        }

        cache.clear();
        SpawnPlugin.getInstance().getLogger().info("Messages successfully loaded.");
    }

    public static String getMessage(String path) {
        if (cache.containsKey(path)) {
            return cache.get(path);
        }

        String msg = messagesConfig.getString(path);

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