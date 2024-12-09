package com.zibada.zibadaCore.items;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

import java.util.HashMap;

public class ItemRegistry {

    public static NamespacedKey KEY;
    private static final HashMap<String,BaseItem> ITEM_MAP = new HashMap<>();


    public static void initialize(Plugin plugin){
        KEY = new NamespacedKey(plugin,"custom_item_id");
    }
    public void register(BaseItem i){
        ITEM_MAP.put(i.getId(),i);
    }

}
