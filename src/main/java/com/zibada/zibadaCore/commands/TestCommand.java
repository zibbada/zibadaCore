package com.zibada.zibadaCore.commands;

import com.zibada.zibadaCore.ZibadaCore;
import com.zibada.zibadaCore.blocks.BlockPersistentDataContainer;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;


public class TestCommand implements CommandExecutor {
    ZibadaCore core;

    public TestCommand(ZibadaCore core){
        this.core = core;
    }


    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player){
            Player p = (Player) sender;
            Block b = p.getTargetBlockExact(10);
            PersistentDataContainer blockPdc = new BlockPersistentDataContainer(b,core );
            blockPdc.set(new NamespacedKey(core,"owner"), PersistentDataType.STRING,p.getName());
        }
        return false;
    }
}
