package com.zibada.zibadaCore.items;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.inventory.ItemStack;

public class ItemListener implements Listener {


    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent e) {
        ItemStack item = e.getItem();
        BaseItem CItem = ItemRegistry.getItem(item);
        if(CItem instanceof InteractableItem ){
            InteractableItem interactableItem = (InteractableItem) CItem;
            interactableItem.onInteract(e);
        }
    }

    @EventHandler
    public void inPlayerDrop(PlayerDropItemEvent e) {
        ItemStack item = e.getItemDrop().getItemStack();
        BaseItem CItem = ItemRegistry.getItem(item);
        if(CItem instanceof InteractableItem ){
            InteractableItem interactableItem = (InteractableItem) CItem;
            interactableItem.onDrop(e);
        }
    }

    @EventHandler
    public void onPlayerSwapHands(PlayerSwapHandItemsEvent e) {
        ItemStack item = e.getMainHandItem();
        BaseItem CItem = ItemRegistry.getItem(item);
        if(CItem instanceof InteractableItem ){
            InteractableItem interactableItem = (InteractableItem) CItem;
            interactableItem.onSwapHands(e);
        }
    }

    @EventHandler
    public void onPlayerInteract(InventoryInteractEvent e) {

    }


}
