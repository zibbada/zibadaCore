package com.zibada.zibadaCore.blocks;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class BaseBlock {
    private final Material material;
    private final String id;
    public BaseBlock(String id, Material material, ItemStack drop) {
        this.id = id;
        this.material = material;
    }
}
