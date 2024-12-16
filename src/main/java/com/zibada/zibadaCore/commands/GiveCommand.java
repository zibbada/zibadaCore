package com.zibada.zibadaCore.commands;

import com.zibada.zibadaCore.items.ItemRegistry;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class GiveCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player && sender.isOp()){
            ItemStack is = ItemRegistry.getItem(args[0]).getBaseItemStack();
            ((Player) sender).getInventory().addItem(is);
        }
        return false;
    }
}
