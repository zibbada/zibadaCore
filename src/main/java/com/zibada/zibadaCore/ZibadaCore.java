package com.zibada.zibadaCore;

import com.zibada.zibadaCore.commands.GiveCommand;
import com.zibada.zibadaCore.items.*;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public final class ZibadaCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ItemListener(), this);
        ItemRegistry.initialize(this);
        ItemRegistry.register(new BaseItem("base_item", new ItemStack(Material.BARRIER)));
        ItemRegistry.register(new TestInteractableItem("test_item",new ItemStack(Material.DIAMOND)));
        File f = new File(this.getDataPath() + "/items.yml");
        try {
            ItemRegistry.registerList(ItemParser.parseItems(new FileInputStream(f)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        getCommand("agive").setExecutor(new GiveCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
