package com.zibada.zibadaCore.commands;

import com.zibada.zibadaCore.ZibadaCore;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

public class TestCommand implements CommandExecutor {
    ZibadaCore core;

    public TestCommand(ZibadaCore core){
        this.core = core;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Map<String,Object> data = p.getInventory().getItemInMainHand().serialize();
            File f = new File(core.getDataPath()+"/test.yml");
            YamlConfiguration yamlConfig = new YamlConfiguration();

            // Set the serialized item in the config
            yamlConfig.set("itemStack", data);

            // Save the configuration to the file
            try {
                yamlConfig.save(f);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
}
