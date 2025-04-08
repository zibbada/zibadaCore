package com.zibada.zibadaCore.blocks;

import com.zibada.zibadaCore.ZibadaCore;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.UUID;

public class ModelBlock extends BaseBlock{
    private Material model;
    public ModelBlock(String id, Material material, String baseItemId, Material model) {
        super(id, material, baseItemId);
        this.model = model;
    }
    @Override
    public void place(Block b) {
        super.place(b);
        ItemDisplay display = b.getWorld().spawn(b.getLocation().toBlockLocation(), ItemDisplay.class);
        display.setItemStack(new ItemStack(model));
        BlockPersistentDataContainer blockPdc = new BlockPersistentDataContainer(b, ZibadaCore.getInstance());
        blockPdc.set(new NamespacedKey(ZibadaCore.getInstance(),"model"), PersistentDataType.STRING,display.getUniqueId().toString());
    }

    @Override
    public void onBlockBreak(BlockBreakEvent e) {
        super.onBlockBreak(e);
        BlockPersistentDataContainer blockPdc = new BlockPersistentDataContainer(e.getBlock(),ZibadaCore.getInstance());
        e.getBlock().getWorld().getEntity(UUID.fromString(blockPdc.get(new NamespacedKey(ZibadaCore.getInstance(),"model"),PersistentDataType.STRING))).remove();

        blockPdc.remove(BlockRegistry.KEY);
        blockPdc.remove(new NamespacedKey(ZibadaCore.getInstance(),"model"));
    }
}
