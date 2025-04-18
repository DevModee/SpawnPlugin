package dev.amargos.commands;

import dev.amargos.SpawnPlugin;
import dev.amargos.utils.MessageUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelSpawnCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can execute this command.");
            return false;
        }

        Player player = (Player) sender;

        if (!SpawnPlugin.getInstance().getConfig().getBoolean("spawn.enabled")) {
            player.sendMessage(MessageUtil.colorize(SpawnPlugin.getInstance().getConfig().getString("messages.no-spawn")));
            return false;
        }

        SpawnPlugin.getInstance().getConfig().set("spawn.enabled", false);
        SpawnPlugin.getInstance().saveConfig();

        player.sendMessage(MessageUtil.colorize(SpawnPlugin.getInstance().getConfig().getString("messages.spawn-deleted")));
        return true;
    }
}
