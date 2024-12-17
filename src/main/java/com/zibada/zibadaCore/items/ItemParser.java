package com.zibada.zibadaCore.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemParser {
    public static List<BaseItem> parseItems(InputStream inputStream){
        Yaml yaml = new Yaml();
        Map<String, Object> root = yaml.load(inputStream);
        List<Map<String, Object>> items = (List<Map<String, Object>>) root.get("items");

        List<BaseItem> baseItems = new ArrayList<>();
        for(Map<String, Object> itemData : items){
            String id = (String) itemData.get("id");
            String type = (String) itemData.get("type");
            Material material = Material.getMaterial(type);

            if (material == null) {
                throw new IllegalArgumentException("Invalid material type: " + type);
            }

            ItemStack itemStack = new ItemStack(material);
            ItemMeta meta = itemStack.getItemMeta();

            if (itemData.containsKey("display_name")) {
                meta.setDisplayName((String) itemData.get("display_name"));
            }

            if (itemData.containsKey("model")){
                meta.setCustomModelData((Integer) itemData.get("model"));
            }

            itemStack.setItemMeta(meta);
            baseItems.add(new BaseItem(id,itemStack));
        }


        return baseItems;
    }
}
