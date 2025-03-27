package com.zibada.zibadaCore;

import com.zibada.zibadaCore.blocks.BlockListener;
import com.zibada.zibadaCore.commands.GetDataCommand;
import com.zibada.zibadaCore.commands.GiveCommand;
import com.zibada.zibadaCore.commands.ReflectCommand;
import com.zibada.zibadaCore.commands.TestCommand;
import com.zibada.zibadaCore.items.*;
import com.zibada.zibadaCore.items.types.BaseItem;
import com.zibada.zibadaCore.utils.parsing.YmlObjectParser;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

public final class ZibadaCore extends JavaPlugin {

    private static ZibadaCore instance;

    @Override
    public void onEnable() {
        instance = this;
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ItemListener(), this);
        getServer().getPluginManager().registerEvents(new BlockListener(), this);
        ItemRegistry.initialize(this);
        ItemRegistry.register(new BaseItem("base_item", new ItemStack(Material.BARRIER)));

        File f = new File(this.getDataPath() + "/items.yml");
        try {
            Yaml yaml = new Yaml();
            Map<String, Object> root = yaml.load(new FileInputStream(f));

            List<Map<String, Object>> items = (List<Map<String, Object>>) root.get("items");
            ItemRegistry.registerList((YmlObjectParser.parseListWithIS(items,BaseItem.class)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        getCommand("agive").setExecutor(new GiveCommand());
        getCommand("test").setExecutor(new TestCommand(this));
        getCommand("reflect").setExecutor(new ReflectCommand());
        getCommand("getData").setExecutor(new GetDataCommand(this));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static ZibadaCore getInstance(){
        return instance;
    }
}
