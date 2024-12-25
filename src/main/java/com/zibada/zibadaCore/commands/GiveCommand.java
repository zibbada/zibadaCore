package com.zibada.zibadaCore.commands;

import com.zibada.zibadaCore.items.ItemRegistry;
import io.papermc.paper.datacomponent.DataComponentType;
import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.Consumable;
import io.papermc.paper.datacomponent.item.consumable.ItemUseAnimation;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.components.FoodComponent;
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
