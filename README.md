# SpawnPlugin

**SpawnPlugin** is a lightweight and configurable Minecraft plugin that allows server administrators to set, delete, and teleport to custom spawn locations. It also supports multi-language configuration and includes useful features such as cooldowns and teleportation sounds.

## 🆕 Version
**Current version:** `1.1`  
**Supported Minecraft versions:** 1.8 - 1.20

## Features
- ✅ Set a custom spawn location with `/setspawn`.
- ❌ Delete the spawn location with `/delspawn`.
- 🚀 Teleport to the configured spawn with `/spawn`.
- 🌐 Support for multi-language configuration (`lang/messages-en.yml`, `messages-es.yml`, etc).
- ⏳ Cooldown system for the `/spawn` command.
- 🔊 Support for custom teleportation sounds.
- 🔁 `/spawnreload` command to reload all plugin configs without restarting.
- 🧩 Public API to teleport players from other plugins easily.

## Setup and Installation
1. Download the plugin and place it in the `/plugins` folder.
2. Configure the `config.yml` and the language files inside the `/plugins/SpawnPlugin/lang/` folder.
3. Start the server and use the commands to set, delete, and teleport to the spawn.

## Permissions

| Permission             | Description                            |
|------------------------|----------------------------------------|
| `spawnplugin.set`      | Allows use of `/setspawn`              |
| `spawnplugin.spawn`    | Allows use of `/spawn`                 |
| `spawnplugin.del`      | Allows use of `/delspawn`              |
| `spawnplugin.reload`   | Allows use of `/spawnreload`           |

## Commands

| Command         | Description                                             |
|----------------|---------------------------------------------------------|
| `/setspawn`     | Sets the current location as the server's spawn point. |
| `/delspawn`     | Deletes the current spawn point.                       |
| `/spawn`        | Teleports the player to the saved spawn location.      |
| `/spawnreload`  | Reloads the plugin's configuration and message files.  |

## API Access

You can use the `SpawnAPI` class from your own plugin to check or teleport a player to the spawn point.

### Example:

```java
import dev.amargos.api.SpawnAPI;

Player player = ...;

if (SpawnAPI.isSpawnEnabled()) {
    SpawnAPI.teleportToSpawn(player);
}
```

### Available Methods:

```java
public class SpawnAPI {
    public static boolean isSpawnEnabled(); // Check if spawn is set
    public static Location getSpawnLocation(); // Get the current spawn Location
    public static void teleportToSpawn(Player player); // Teleport a player to spawn
}
```

**Note:** Make sure to declare `SpawnPlugin` as a *soft-depend* or *depend* in your plugin.yml when using the API.

## Example Usage

### Setting the spawn location
Run `/setspawn` while standing at the desired location.

### Deleting the spawn location
Use `/delspawn` to remove the spawn.

### Teleporting to spawn
Use `/spawn`. A cooldown may apply depending on config settings.

### Reloading configuration
Make changes in `config.yml` or language files (`lang/messages-*.yml`) and run `/spawnreload` to apply them live.

## License
This plugin is open source and free to use.