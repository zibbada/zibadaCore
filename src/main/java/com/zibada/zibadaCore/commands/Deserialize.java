package com.zibada.zibadaCore.commands;

import com.zibada.zibadaCore.ZibadaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Map;

public class Deserialize implements CommandExecutor {

    private ZibadaCore core;

    public Deserialize(ZibadaCore core){
        this.core = core;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            File f = new File(core.getDataPath()+"/test.yml");
            YamlConfiguration yamlConfig = YamlConfiguration.loadConfiguration(f);

            // Get the serialized item data
            Map<String, Object> serializedItem =yamlConfig.getConfigurationSection("itemStack").getValues(false);

            // Deserialize and return the ItemStack
            p.getInventory().addItem(ItemStack.deserialize(serializedItem));
        }

        return false;
    }
}
