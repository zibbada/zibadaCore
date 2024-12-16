package com.zibada.zibadaCore;

import com.zibada.zibadaCore.commands.GiveCommand;
import com.zibada.zibadaCore.items.BaseItem;
import com.zibada.zibadaCore.items.ItemListener;
import com.zibada.zibadaCore.items.ItemRegistry;
import com.zibada.zibadaCore.items.TestInteractableItem;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

public final class ZibadaCore extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ItemListener(), this);
        ItemRegistry.initialize(this);
        ItemRegistry.register(new BaseItem("base_item", new ItemStack(Material.BARRIER)));
        ItemRegistry.register(new TestInteractableItem("test_item",new ItemStack(Material.DIAMOND)));
        getCommand("agive").setExecutor(new GiveCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
