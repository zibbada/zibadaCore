package com.zibada.zibadaCore.items;

import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public abstract class InteractableItem extends BaseItem {
    public InteractableItem(String id, ItemStack baseItemStack) {
        super(id, baseItemStack);
    }

    public abstract void onInteract (PlayerInteractEvent e);
    public abstract void onDrop (PlayerDropItemEvent e);
    public abstract void onSwapHands(PlayerSwapHandItemsEvent e);
    public abstract void onInventoryInteract (InventoryInteractEvent e);
    public abstract void onInventoryInteract (InventoryDragEvent e);

}
