package com.zibada.zibadaCore.items;

import com.zibada.zibadaCore.BaseManager;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

public class BaseItem {
    private final String id;
    private final ItemStack baseItemStack;

    public BaseItem(String id, ItemStack baseItemStack) {

        this.id = id;
        attachId(baseItemStack);
        this.baseItemStack = baseItemStack;
    }

    public String getId() {
        return id;
    }
    public ItemStack getBaseItemStack(){
        return baseItemStack.clone();
    };


    public void attachId(ItemStack itemStack){
        ItemMeta itemMeta = itemStack.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        pdc.set(ItemRegistry.KEY, PersistentDataType.STRING,id);
        itemStack.setItemMeta(itemMeta);
    }

}
