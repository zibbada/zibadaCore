package com.zibada.zibadaCore.utils.parsing;

import net.kyori.adventure.text.Component;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class ItemStackParser {
    public static ItemStack parseItemStack(Map<String, Object> itemData){
        String type = (String) itemData.get("type");
        Material material = Material.getMaterial(type.toUpperCase());

        if (material == null) {
            throw new IllegalArgumentException("Invalid material type: " + type);
        }

        ItemStack itemStack = new ItemStack(material);
        ItemMeta meta = itemStack.getItemMeta();

        if (itemData.containsKey("name")) {
            meta.itemName(Component.text((String) itemData.get("name")));
        }

        if (itemData.containsKey("model")){
            meta.setCustomModelData((Integer) itemData.get("model"));
        }

        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
