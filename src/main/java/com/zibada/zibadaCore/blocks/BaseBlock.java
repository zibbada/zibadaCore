package com.zibada.zibadaCore.blocks;

import com.zibada.zibadaCore.ZibadaCore;
import com.zibada.zibadaCore.items.ItemRegistry;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class BaseBlock {
    private final Material material;
    private final String id;
    private final String baseItemId;
    public BaseBlock(String id, Material material, String baseItemId) {
        this.id = id;
        this.material = material;
        this.baseItemId = baseItemId;
    }

    public void place(Block b){
        b.setType(material);
        BlockPersistentDataContainer blockPdc = new BlockPersistentDataContainer(b, ZibadaCore.getInstance());
        blockPdc.set(BlockRegistry.KEY, PersistentDataType.STRING,id);
    }

    public void onBlockBreak(BlockBreakEvent e){
        Block b = e.getBlock();
        Location loc = b.getLocation();
        if(!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
            b.getWorld().dropItem(loc, ItemRegistry.getItem(baseItemId).getBaseItemStack());
        }

        BlockPersistentDataContainer blockPdc = new BlockPersistentDataContainer(b,ZibadaCore.getInstance());
        blockPdc.remove(BlockRegistry.KEY);
    }
}
