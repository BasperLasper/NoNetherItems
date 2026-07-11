# NoNetherItems

NoNetherItems is a Minecraft server plugin that automatically removes Nether-related items from player inventories and opened containers. It is intended for servers where Nether items are disabled or prohibited.

## Features

- Removes Nether-related materials from player inventories
- Checks armor slots, offhand/extra inventory slots, and container inventories
- Scans players when they join and every 5 seconds while they are online
- Scans containers when players open or close them
- Supports optional player notifications using MiniMessage formatting
- Records every removal in the server console
- Supports optional removal logging to a dedicated file
- Includes a reload command with permission-based access
- Can be enabled or disabled without removing the plugin

The blocked materials include Nether blocks, Nether wood sets, Netherite equipment and materials, Quartz items, Blackstone, Basalt, Soul items, Glowstone, Magma items, Nether mob spawn eggs, and other Nether-specific items.

> **Important:** Matching items are permanently removed. They are not stored for later recovery. Back up important server data and test the plugin before using it on a production server.

## Requirements

- A Bukkit-compatible server supporting API version 1.21
- Java version appropriate for your server software

## Installation

1. Stop the Minecraft server.
2. Place the NoNetherItems plugin JAR in the server's `plugins` directory.
3. Start the server to generate the default configuration.
4. Edit `plugins/NoNetherItems/config.yml` as needed.
5. Run `/nonetheritems reload` or restart the server after changing the configuration.

## Command

| Command | Alias | Permission | Description |
| --- | --- | --- | --- |
| `/nonetheritems reload` | `/nni reload` | `nonetheritems.admin` | Reloads the plugin configuration. |

The `nonetheritems.admin` permission is granted to server operators by default.

## Configuration

```yaml
enabled: true
notify-players: true
log-to-file: true
log-file-name: "removed-items.log"

chat-message: "<red>Removed nether item {item} x{amount} from your {source}. Nether items are not allowed.</red>"
reload-message: "<green>NoNetherItems config reloaded.</green>"
no-permission-message: "<red>You do not have permission to use this command.</red>"
```

| Setting | Description |
| --- | --- |
| `enabled` | Enables or disables item removal. |
| `notify-players` | Sends a chat message to a player whenever an item stack is removed. |
| `log-to-file` | Writes removal records to a log file in the plugin data directory. |
| `log-file-name` | Sets the name of the removal log file. |
| `chat-message` | Sets the message sent after an item is removed. |
| `reload-message` | Sets the successful reload message. |
| `no-permission-message` | Sets the message shown when a sender lacks permission. |

### Message Placeholders

The `chat-message` option supports the following placeholders:

| Placeholder | Value |
| --- | --- |
| `{item}` | The removed Bukkit material name. |
| `{amount}` | The number of items removed from the stack. |
| `{source}` | The location scanned, such as `inventory`, `armor`, `offhand`, or `container`. |

Messages support MiniMessage tags such as `<red>`, `<green>`, and other compatible formatting.

## Logging

Every removed stack is written to the server console. When `log-to-file` is enabled, the plugin also writes the UTC timestamp, player name, source, material, amount, and slot number to the configured log file.

Example entry:

```text
2026-01-01T12:00:00Z | player=PlayerName | source=inventory | item=NETHERRACK | amount=64 | slot=0
```

## How Items Are Detected

The plugin uses a built-in list of known Nether materials and also blocks material names containing Nether-related terms such as `NETHER`, `CRIMSON`, `WARPED`, `SOUL`, `BLACKSTONE`, `BASALT`, `MAGMA`, `GLOWSTONE`, `QUARTZ`, `SHROOMLIGHT`, `NETHERRACK`, and `NETHERITE`.

The blocked item list is built into the plugin and cannot be changed through `config.yml`.
