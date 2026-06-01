# Plugin Architecture

## Overview
The plugin system allows for modular game extensions and enhancements.

## Plugin Structure
```
plugins/
├── base/
│   └── PluginInterface.kt
├── steam-cloud/
│   └── SteamCloudPlugin.kt
└── game-extensions/
    └── GameExtensionPlugin.kt
```

## Creating a Plugin
1. Implement the `PluginInterface`
2. Register in the plugin manager
3. Deploy to the plugins directory

## Available Hooks
- `onGameStart()` - Called when a game launches
- `onGameStop()` - Called when a game closes
- `onSaveState()` - Called before saving game state
- `onLoadState()` - Called when loading game state
