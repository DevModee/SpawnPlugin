package dev.amargos.utils;

import org.bukkit.ChatColor;

public class MessageUtil {
    public static String colorize(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }
}
