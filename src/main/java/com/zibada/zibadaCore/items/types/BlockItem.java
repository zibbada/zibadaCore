package com.zibada.zibadaCore.items.types;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.zibada.zibadaCore.ZibadaCore;
import com.zibada.zibadaCore.blocks.BaseBlock;
import com.zibada.zibadaCore.blocks.BlockPersistentDataContainer;
import com.zibada.zibadaCore.blocks.BlockRegistry;
import org.bukkit.GameMode;
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

public class BlockItem extends InteractableItem {
    /*
    Fix fast non block item placing
    remove it when placed if not in gm1
    add custom custom bock support
    test it more
    DONT USE PLUGIN ADD STATIC NAMESPACE GETTER
     */
    private String blockId;

    public BlockItem(String id, ItemStack baseItemStack, String blockId) {
        super(id, baseItemStack);
        this.blockId = blockId;
    }


    @Override
    public void onInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            e.setCancelled(true);
            Block clickedBlock = e.getClickedBlock();
            Vector blockFaceDirection = e.getBlockFace().getDirection();
            Location blockLocation = clickedBlock.getLocation().add(blockFaceDirection).toBlockLocation();
            Block newBlock = blockLocation.getBlock();
            if (newBlock.isEmpty() || newBlock.isReplaceable()) {
                BlockRegistry.getBlock(blockId).place(newBlock);
                PacketContainer armSwingPacket = new PacketContainer(PacketType.Play.Server.ANIMATION);

                armSwingPacket.getIntegers().write(0, e.getPlayer().getEntityId());
                armSwingPacket.getIntegers().write(1, 0);

                ProtocolLibrary.getProtocolManager().sendServerPacket(e.getPlayer(), armSwingPacket);
                if (!e.getPlayer().getGameMode().equals(GameMode.CREATIVE)) {
                    e.getItem().subtract(1);
                }
            }
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
