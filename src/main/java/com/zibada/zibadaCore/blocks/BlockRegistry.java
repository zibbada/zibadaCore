package com.zibada.zibadaCore.blocks;

import com.zibada.zibadaCore.ZibadaCore;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;

public class BlockRegistry {

    public static final NamespacedKey KEY = new NamespacedKey(ZibadaCore.getInstance(), "custom_block_id");
    private static final HashMap<String, BaseBlock> BLOCK_MAP = new HashMap<>();

    public static void register(String id, BaseBlock block) {
        BLOCK_MAP.put(id, block);
    }

    public static BaseBlock getBlock(String id) {
        return BLOCK_MAP.get(id);
    }

    public static BaseBlock getBlock(Block block) {
        BlockPersistentDataContainer blockPdc = new BlockPersistentDataContainer(block, ZibadaCore.getInstance());
        if (blockPdc.isEmpty()) {
            return null;
        }
        if (blockPdc.has(KEY)) {
            return BLOCK_MAP.get(blockPdc.get(KEY, PersistentDataType.STRING));
        }
        return null;
    }

}
