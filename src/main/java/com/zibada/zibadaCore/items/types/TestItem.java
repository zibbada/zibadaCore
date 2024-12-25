package com.zibada.zibadaCore.items.types;

import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class TestItem extends InteractableItem{
    String msg;
    public TestItem(String id,ItemStack baseItemStack,String msg) {
        super(id, baseItemStack);
        this.msg = msg;
    }

    @Override
    public void onInteract(PlayerInteractEvent e) {
        e.getPlayer().sendMessage(msg);
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
