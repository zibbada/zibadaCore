package com.zibada.zibadaCore.commands;

import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;

public class GetDataCommand implements CommandExecutor {
    Plugin core;

    public GetDataCommand(Plugin core) {
        this.core = core;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chunk chunk = player.getChunk();
            PersistentDataContainer chunkPdc = chunk.getPersistentDataContainer();
            NamespacedKey blockDataKey = new NamespacedKey(core, "blockData");

            if (!chunkPdc.has(blockDataKey, PersistentDataType.TAG_CONTAINER)) {
                player.sendMessage("No block data in chunk");
                return true;
            }

            PersistentDataContainer blockDataPdc = chunkPdc.get(blockDataKey, PersistentDataType.TAG_CONTAINER);
            player.sendMessage("Block data in chunk " + chunk.getX() + "," + chunk.getZ() + ":");

            for (NamespacedKey blockKey : blockDataPdc.getKeys()) {
                PersistentDataContainer blockPdc = blockDataPdc.get(blockKey, PersistentDataType.TAG_CONTAINER);
                player.sendMessage("\nBlock at " + blockKey.getKey() + ":");

                for (NamespacedKey dataKey : blockPdc.getKeys()) {
                    player.sendMessage("  - " + dataKey.getKey() + ": " + blockPdc.get(dataKey, PersistentDataType.STRING));
                }
            }
        }
        return false;
    }
}
