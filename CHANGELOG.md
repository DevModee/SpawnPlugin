# Changelog


## [1.4.0] - 2025-04-19

### Fixes

- **Removed unused 'language' field in SpawnPlugin.java.**
- **Enhanced error handling for loading messages.yml (throws exception if empty or missing).**
- **Updated SpawnReloadCommand to use external messages for all responses.**
- **Added warning logs for missing message keys in MessageUtil.**
- **Ensured messages.yml is created automatically if missing.**

### Changes
- Upgraded plugin version from `1.2.0` to `1.3.0`.
- Upgraded plugin version from `1.3.0` to `1.4.0`.

## [1.2.0] - 2025-04-18

### ✨ New Features

- **Dynamic Multi-language Support**  
  Added `messages-en.yml` and `messages-es.yml`, now selectable via `lang` in `config.yml`.  
  All messages (errors, permissions, cooldowns, etc.) are now loaded dynamically.

- **Improved Cooldown System**  
  Configurable cooldown duration via `cooldown.seconds` in `config.yml`.  
  Dynamic messages show remaining time to the player.

- **Expanded Message System**  
  Added messages for missing permissions, console-only commands, incorrect usage, missing world, and more.

- **Centralized Command Registration**  
  All commands are now registered through a dedicated method `registerCommands()` for cleaner structure.

- **Plugin Initialization Logs**  
  On startup, the plugin logs language selection, loaded managers, registered commands, and status.

- **Teleport Sound Validation**  
  If the teleport sound is invalid, the player is still teleported and a warning is shown in console — preventing crashes.

- **Preparation for Future Features**  
  Introduced `registerEventListeners()` method with a console log if no listeners are present — ready for future events.

- **Developer-Friendly Comments**  
  Key parts of the code include comments to improve maintainability and future development.

---

### ✅ Improvements & Fixes

- **Permissions**  
  Permissions added for `/setspawn` and `/delspawn` commands.  
  Players without permissions now receive localized error messages.

- **Player-only Command Checks**  
  Commands like `/setspawn` and `/delspawn` can only be executed by players. Console gets a clear error message.

- **Spawn Activation Check**  
  Before teleporting, the plugin checks if a spawn is configured and enabled.

- **Robust Configuration Handling**
    - Ensures default values are generated in `config.yml` if keys are missing.
    - Prevents crashes from missing worlds, misconfigured spawn locations, or invalid config entries.

- **Language Files Autogeneration**
    - Automatically generates `messages-en.yml` and `messages-es.yml` if missing.
    - Creates the `/lang` folder if it doesn’t exist.

- **Fallback for Missing Messages**  
  Logs a warning if a message is missing and uses a generic fallback, improving stability.

- **Cleaner Error Handling**  
  Avoids sending command usage back to the player if there's a specific error message (e.g. missing permission or config).


---

### 🔢 Versioning

- Upgraded plugin version from `1.1.0` to `1.2.0`.
- Upgraded plugin version from `1.2.0` to `1.3.0`.
- Upgraded plugin version from `1.3.0` to `1.4.0`.

---

## [1.1.0] - 2025-04-18

### 🚀 Added

- Support for multiple languages (`messages-en.yml`, `messages-es.yml`).
- Progress bar (boss bar / action bar) to show teleport cooldown.
- `/spawnreload` command to reload plugin configs.
- Improved public API and `getMessage(path)` utility.
- `reloadPlugin()` method for clean reloads.
- Custom enable/disable messages.

### 🛠️ Fixed & Changed

- Fixed missing language file issue (`MessageUtil`).
- Fixed incorrect config path access.
- Reduced redundant use of `getInstance()`.
- Improved message loading with fallback logic.
- Version bumped from `1.0.0` to `1.1.0`.

---

## [1.0.0] - 2025-04-18

### 🧱 Initial Release

- Core plugin structure and main class.
- Commands: `/setspawn`, `/delspawn`, `/spawn`.
- Configurable messages, sounds, and cooldowns.
- Cooldown system per player.
- Public API (`SpawnAPI`) for external plugin integration.
