package com.zibada.zibadaCore.blocks;

import com.zibada.zibadaCore.ZibadaCore;
import io.papermc.paper.event.player.PlayerPickItemEvent;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.player.PlayerInteractEvent;

public class BlockListener implements Listener {
    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e) {
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        Block b = e.getBlock();
        BaseBlock baseBlock = BlockRegistry.getBlock(b);
        if (baseBlock != null) {
            baseBlock.onBlockBreak(e);
            e.setDropItems(false);
        }
    }

    @EventHandler
    public void onBlockInteraction(PlayerInteractEvent e) {

    }

    @EventHandler
    public void onNotePlay(NotePlayEvent e) {

    }
}
