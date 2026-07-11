package com.basper.nonetheritems;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Instant;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.logging.Level;

public final class NoNetherItemsPlugin extends JavaPlugin implements Listener {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private static final Set<Material> BANNED_MATERIALS = EnumSet.of(
            Material.ANCIENT_DEBRIS,
            Material.BASALT,
            Material.BLACKSTONE,
            Material.BLAZE_POWDER,
            Material.BLAZE_ROD,
            Material.BLAZE_SPAWN_EGG,
            Material.CHISELED_NETHER_BRICKS,
            Material.CHISELED_POLISHED_BLACKSTONE,
            Material.CHISELED_QUARTZ_BLOCK,
            Material.CRACKED_NETHER_BRICKS,
            Material.CRACKED_POLISHED_BLACKSTONE_BRICKS,
            Material.CRIMSON_BUTTON,
            Material.CRIMSON_DOOR,
            Material.CRIMSON_FENCE,
            Material.CRIMSON_FENCE_GATE,
            Material.CRIMSON_FUNGUS,
            Material.CRIMSON_HANGING_SIGN,
            Material.CRIMSON_HYPHAE,
            Material.CRIMSON_NYLIUM,
            Material.CRIMSON_PLANKS,
            Material.CRIMSON_PRESSURE_PLATE,
            Material.CRIMSON_ROOTS,
            Material.CRIMSON_SIGN,
            Material.CRIMSON_SLAB,
            Material.CRIMSON_STAIRS,
            Material.CRIMSON_STEM,
            Material.CRIMSON_TRAPDOOR,
            Material.CRIMSON_WALL_HANGING_SIGN,
            Material.CRIMSON_WALL_SIGN,
            Material.CRYING_OBSIDIAN,
            Material.GHAST_SPAWN_EGG,
            Material.GHAST_TEAR,
            Material.GILDED_BLACKSTONE,
            Material.GLOWSTONE,
            Material.GLOWSTONE_DUST,
            Material.HOGLIN_SPAWN_EGG,
            Material.LODESTONE,
            Material.MAGMA_BLOCK,
            Material.MAGMA_CREAM,
            Material.MAGMA_CUBE_SPAWN_EGG,
            Material.MUSIC_DISC_PIGSTEP,
            Material.NETHER_BRICK,
            Material.NETHER_BRICK_FENCE,
            Material.NETHER_BRICK_SLAB,
            Material.NETHER_BRICK_STAIRS,
            Material.NETHER_BRICK_WALL,
            Material.NETHER_BRICKS,
            Material.NETHER_GOLD_ORE,
            Material.NETHER_QUARTZ_ORE,
            Material.NETHER_SPROUTS,
            Material.NETHER_STAR,
            Material.NETHER_WART,
            Material.NETHER_WART_BLOCK,
            Material.NETHERITE_AXE,
            Material.NETHERITE_BLOCK,
            Material.NETHERITE_BOOTS,
            Material.NETHERITE_CHESTPLATE,
            Material.NETHERITE_HELMET,
            Material.NETHERITE_HOE,
            Material.NETHERITE_INGOT,
            Material.NETHERITE_LEGGINGS,
            Material.NETHERITE_PICKAXE,
            Material.NETHERITE_SCRAP,
            Material.NETHERITE_SHOVEL,
            Material.NETHERITE_SWORD,
            Material.NETHERITE_UPGRADE_SMITHING_TEMPLATE,
            Material.NETHERRACK,
            Material.PIGLIN_BANNER_PATTERN,
            Material.PIGLIN_BRUTE_SPAWN_EGG,
            Material.PIGLIN_HEAD,
            Material.PIGLIN_SPAWN_EGG,
            Material.PIGLIN_WALL_HEAD,
            Material.POLISHED_BASALT,
            Material.POLISHED_BLACKSTONE,
            Material.POLISHED_BLACKSTONE_BRICK_SLAB,
            Material.POLISHED_BLACKSTONE_BRICK_STAIRS,
            Material.POLISHED_BLACKSTONE_BRICK_WALL,
            Material.POLISHED_BLACKSTONE_BRICKS,
            Material.POLISHED_BLACKSTONE_BUTTON,
            Material.POLISHED_BLACKSTONE_PRESSURE_PLATE,
            Material.POLISHED_BLACKSTONE_SLAB,
            Material.POLISHED_BLACKSTONE_STAIRS,
            Material.POLISHED_BLACKSTONE_WALL,
            Material.QUARTZ,
            Material.QUARTZ_BLOCK,
            Material.QUARTZ_BRICKS,
            Material.QUARTZ_PILLAR,
            Material.QUARTZ_SLAB,
            Material.QUARTZ_STAIRS,
            Material.RED_NETHER_BRICK_SLAB,
            Material.RED_NETHER_BRICK_STAIRS,
            Material.RED_NETHER_BRICK_WALL,
            Material.RED_NETHER_BRICKS,
            Material.RESPAWN_ANCHOR,
            Material.SHROOMLIGHT,
            Material.SMOOTH_BASALT,
            Material.SMOOTH_QUARTZ,
            Material.SMOOTH_QUARTZ_SLAB,
            Material.SMOOTH_QUARTZ_STAIRS,
            Material.SOUL_CAMPFIRE,
            Material.SOUL_LANTERN,
            Material.SOUL_SAND,
            Material.SOUL_SOIL,
            Material.SOUL_TORCH,
            Material.SOUL_WALL_TORCH,
            Material.STRIDER_SPAWN_EGG,
            Material.STRIPPED_CRIMSON_HYPHAE,
            Material.STRIPPED_CRIMSON_STEM,
            Material.STRIPPED_WARPED_HYPHAE,
            Material.STRIPPED_WARPED_STEM,
            Material.TWISTING_VINES,
            Material.TWISTING_VINES_PLANT,
            Material.WARPED_BUTTON,
            Material.WARPED_DOOR,
            Material.WARPED_FENCE,
            Material.WARPED_FENCE_GATE,
            Material.WARPED_FUNGUS,
            Material.WARPED_FUNGUS_ON_A_STICK,
            Material.WARPED_HANGING_SIGN,
            Material.WARPED_HYPHAE,
            Material.WARPED_NYLIUM,
            Material.WARPED_PLANKS,
            Material.WARPED_PRESSURE_PLATE,
            Material.WARPED_ROOTS,
            Material.WARPED_SIGN,
            Material.WARPED_SLAB,
            Material.WARPED_STAIRS,
            Material.WARPED_STEM,
            Material.WARPED_TRAPDOOR,
            Material.WARPED_WALL_HANGING_SIGN,
            Material.WARPED_WALL_SIGN,
            Material.WARPED_WART_BLOCK,
            Material.WEEPING_VINES,
            Material.WEEPING_VINES_PLANT,
            Material.WITHER_SKELETON_SKULL,
            Material.WITHER_SKELETON_SPAWN_EGG,
            Material.WITHER_SKELETON_WALL_SKULL,
            Material.ZOGLIN_SPAWN_EGG,
            Material.ZOMBIFIED_PIGLIN_SPAWN_EGG
    );

    private boolean enabled;
    private boolean notifyPlayers;
    private boolean logToFile;
    private File logFile;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        loadSettings();
        ensureLogFile();
        Bukkit.getPluginManager().registerEvents(this, this);
        Bukkit.getScheduler().runTask(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                scanPlayerInventory(player);
            }
        });
        Bukkit.getScheduler().runTaskTimer(this, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                scanPlayerInventory(player);
            }
        }, 100L, 100L);
    }

    @Override
    public boolean onCommand(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String label,
            @NotNull String[] args
    ) {
        if (!sender.hasPermission("nonetheritems.admin")) {
            sender.sendMessage(message("no-permission-message"));
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            reloadConfig();
            loadSettings();
            ensureLogFile();
            sender.sendMessage(message("reload-message"));
            return true;
        }

        sender.sendMessage(Component.text("Usage: /" + label + " reload"));
        return true;
    }

    @Override
    public @NotNull List<String> onTabComplete(
            @NotNull CommandSender sender,
            @NotNull Command command,
            @NotNull String alias,
            @NotNull String[] args
    ) {
        if (!sender.hasPermission("nonetheritems.admin") || args.length != 1) {
            return List.of();
        }

        String prefix = args[0].toLowerCase(Locale.ROOT);
        return List.of("reload").stream()
                .filter(option -> option.startsWith(prefix))
                .toList();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTask(this, () -> scanPlayerInventory(event.getPlayer()));
    }

    @EventHandler
    public void onInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player player)) {
            return;
        }

        scanInventory(event.getInventory(), player, "container");
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        if (!(event.getPlayer() instanceof Player player)) {
            return;
        }

        scanInventory(event.getInventory(), player, "container");
    }

    private void loadSettings() {
        enabled = getConfig().getBoolean("enabled", true);
        notifyPlayers = getConfig().getBoolean("notify-players", true);
        logToFile = getConfig().getBoolean("log-to-file", true);
    }

    private void ensureLogFile() {
        File dataFolder = getDataFolder();
        if (!dataFolder.exists() && !dataFolder.mkdirs()) {
            getLogger().warning("Could not create plugin data folder.");
        }

        logFile = new File(dataFolder, getConfig().getString("log-file-name", "removed-items.log"));
    }

    private int scanInventory(Inventory inventory, Player player, String source) {
        if (!enabled || inventory == null) {
            return 0;
        }

        int removed = 0;
        for (int slot = 0; slot < inventory.getSize(); slot++) {
            ItemStack item = inventory.getItem(slot);
            if (item == null || item.getType().isAir()) {
                continue;
            }

            Material material = item.getType();
            if (!isBannedMaterial(material)) {
                continue;
            }

            int amount = item.getAmount();
            inventory.setItem(slot, null);
            removed++;

            String materialName = material.name();
            recordRemoval(player, source, materialName, amount, slot);
        }

        return removed;
    }

    private int scanPlayerInventory(Player player) {
        PlayerInventory inventory = player.getInventory();
        int removed = scanInventory(inventory, player, "inventory");
        removed += scanItemArray(inventory.getArmorContents(), player, "armor", inventory::setArmorContents);
        removed += scanItemArray(inventory.getExtraContents(), player, "offhand", inventory::setExtraContents);
        return removed;
    }

    private int scanItemArray(ItemStack[] contents, Player player, String source, InventoryContentSetter setter) {
        int removed = 0;
        for (int slot = 0; slot < contents.length; slot++) {
            ItemStack item = contents[slot];
            if (item == null || item.getType().isAir() || !isBannedMaterial(item.getType())) {
                continue;
            }

            String materialName = item.getType().name();
            int amount = item.getAmount();
            contents[slot] = null;
            removed++;
            recordRemoval(player, source, materialName, amount, slot);
        }

        if (removed > 0) {
            setter.setContents(contents);
        }

        return removed;
    }

    private boolean isBannedMaterial(Material material) {
        if (material == null || material.isAir()) {
            return false;
        }

        String normalized = material.name().toUpperCase(Locale.ROOT);
        return BANNED_MATERIALS.contains(material)
                || normalized.contains("NETHER")
                || normalized.contains("CRIMSON")
                || normalized.contains("WARPED")
                || normalized.contains("SOUL")
                || normalized.contains("BLACKSTONE")
                || normalized.contains("BASALT")
                || normalized.contains("MAGMA")
                || normalized.contains("GLOWSTONE")
                || normalized.contains("QUARTZ")
                || normalized.contains("SHROOMLIGHT")
                || normalized.contains("NETHERRACK")
                || normalized.contains("NETHERITE");
    }

    private void recordRemoval(Player player, String source, String materialName, int amount, int slot) {
        String logMessage = "Removed nether item " + materialName + " x" + amount + " from " + source + " for " + player.getName();
        getLogger().info("[NoNetherItems] " + logMessage);

        if (notifyPlayers) {
            player.sendMessage(message("chat-message", materialName, source, amount));
        }

        writeLogEntry(player, source, materialName, amount, slot);
    }

    private void writeLogEntry(Player player, String source, String materialName, int amount, int slot) {
        if (!logToFile || logFile == null) {
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFile, true))) {
            writer.write(Instant.now() + " | player=" + player.getName() + " | source=" + source + " | item=" + materialName + " | amount=" + amount + " | slot=" + slot);
            writer.newLine();
        } catch (IOException exception) {
            getLogger().log(Level.WARNING, "Could not write removal log entry.", exception);
        }
    }

    private Component message(String path, String itemName, String source, int amount) {
        String template = getConfig().getString(path, "<red>Removed nether items from your {source}.</red>");
        String resolved = template
                .replace("{item}", itemName)
                .replace("{amount}", String.valueOf(amount))
                .replace("{source}", source);
        return MINI_MESSAGE.deserialize(resolved);
    }

    private Component message(String path) {
        return MINI_MESSAGE.deserialize(getConfig().getString(path, ""));
    }

    @FunctionalInterface
    private interface InventoryContentSetter {
        void setContents(ItemStack[] contents);
    }
}
