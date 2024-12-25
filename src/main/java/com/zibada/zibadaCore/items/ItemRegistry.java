package com.zibada.zibadaCore.items;

import com.zibada.zibadaCore.items.types.BaseItem;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;
import java.util.List;

public class ItemRegistry {

    public static NamespacedKey KEY;
    private static final HashMap<String, BaseItem> ITEM_MAP = new HashMap<>();


    public static void initialize(Plugin plugin){
        KEY = new NamespacedKey(plugin,"custom_item_id");
    }

    public static void register(BaseItem i){
        ITEM_MAP.put(i.getId(),i);
    }

    public static void registerList(List<BaseItem> l){
        for(BaseItem i : l){
            ITEM_MAP.put(i.getId(),i);
        }
    }

    public static BaseItem getItem(String id){
        return ITEM_MAP.get(id);
    }

    public static BaseItem getItem(ItemStack itemStack){
        if (itemStack == null || !itemStack.hasItemMeta()) return null;
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return null;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (container.has(KEY, PersistentDataType.STRING)) {
            return ITEM_MAP.get(container.get(KEY, PersistentDataType.STRING));
        }
        return null;
    }

    public static boolean isCustomItem(ItemStack itemStack){
        if (itemStack == null || !itemStack.hasItemMeta()) return false;
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null) return false;

        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (container.has(KEY, PersistentDataType.STRING)) {
            return true;
        }
        return false;
    }

}
