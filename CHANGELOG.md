# Changelog

## [1.0.0] - 2025-04-18
### Added
- Main plugin class and structure
- /setspawn, /delspawn, and /spawn commands
- Configurable messages and cooldown
- Config file with spawn, sound, and message settings
- Cooldown system per player
- Public SpawnAPI for integration with other plugins

## [1.0.1] - 2025-04-18
### Added
- **Multi-language Support**: Added `messages-en.yml` and `messages-es.yml` for English and Spanish languages.
- **Progress Bar**: Added a boss bar/action bar to show the cooldown progress when teleporting.
- **/spawnreload Command**: Added a command to reload the plugin's configuration without restarting the server.
- **Improved API**: Added more flexible methods for API integration with other plugins.
- **getMessage(path)**` utility to retrieve localized messages anywhere in the code
- New method **reloadPlugin()** in main class to allow clean reloads


### Fixed
- Fixed incorrect config path access in `SetSpawnCommand.java`
- Cleaned up message loading to allow fallback messages if missing
- Fixed repeated use of `getInstance()` in `SetSpawnCommand.java`

### Changed
- Bumped plugin version from `1.0` to `1.1`