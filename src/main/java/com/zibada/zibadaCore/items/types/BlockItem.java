package com.zibada.zibadaCore.items.types;

import com.zibada.zibadaCore.ZibadaCore;
import com.zibada.zibadaCore.blocks.BlockPersistentDataContainer;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.util.Vector;

public class BlockItem extends InteractableItem{
    /*
    Fix fast non block item placing
    remove it when placed if not in gm1
    add custom custom bock support
    test it more

     */
    private Material blockMaterial;
    public BlockItem(String id, ItemStack baseItemStack) {
        super(id, baseItemStack);
        blockMaterial = baseItemStack.getType();
    }
    public BlockItem(String id, ItemStack baseItemStack, String blockMaterial) {
        super(id, baseItemStack);
        this.blockMaterial = Material.getMaterial(blockMaterial);
    }


    @Override
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK){
            e.setCancelled(true);
            Block clickedBlock = e.getClickedBlock();
            Vector blockFaceDirection = e.getBlockFace().getDirection();
            Location blockLocation = clickedBlock.getLocation().add(blockFaceDirection).toBlockLocation();
            Block newBlock = blockLocation.getBlock();
            newBlock.setType(blockMaterial);

            PersistentDataContainer blockPdc = new BlockPersistentDataContainer(newBlock, ZibadaCore.getInstance());
            blockPdc.set(new NamespacedKey(ZibadaCore.getInstance(),"owner"), PersistentDataType.STRING,e.getPlayer().getName());
        }
    }

    @Override
    public void onDrop(PlayerDropItemEvent e) {

    }

    @Override
    public void onSwapHands(PlayerSwapHandItemsEvent e) {

    }

    @Override
    public void onInventoryInteract(InventoryInteractEvent e) {

    }

    @Override
    public void onInventoryInteract(InventoryDragEvent e) {

    }
}
