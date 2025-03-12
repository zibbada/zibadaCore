package com.zibada.zibadaCore.blocks;

import org.bukkit.Chunk;
import org.bukkit.NamespacedKey;
import org.bukkit.block.Block;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;
import org.checkerframework.checker.units.qual.N;
import org.jetbrains.annotations.NotNull;
import org.jspecify.annotations.Nullable;

import java.io.IOException;
import java.util.Set;

public class BlockPersistentDataContainer implements PersistentDataContainer{
    private final Block block;
    private final Chunk chunk;
    private PersistentDataContainer chunkPdc;
    private PersistentDataContainer blockDataPdc;
    private PersistentDataContainer blockPdc;

    private final NamespacedKey chunkKey;
    private final NamespacedKey blockKey;


    public BlockPersistentDataContainer(Block block, Plugin plugin){
        this.block = block;
        this.chunk = block.getChunk();
        this.chunkPdc = this.chunk.getPersistentDataContainer();

        this.chunkKey = new NamespacedKey(plugin,"blockData");
        this.blockKey = generateBlockKey(plugin,this.block);

        //Get/Create shared pdc for block data
        if(chunkPdc.has(chunkKey,PersistentDataType.TAG_CONTAINER)){
            blockDataPdc = chunkPdc.get(chunkKey,PersistentDataType.TAG_CONTAINER);
        }else{
            blockDataPdc = chunkPdc.getAdapterContext().newPersistentDataContainer();
            chunkPdc.set(chunkKey,PersistentDataType.TAG_CONTAINER,blockDataPdc);
        }

        //Get/Create specific pdc
        if(blockDataPdc.has(blockKey)){
            blockPdc = blockDataPdc.get(blockKey,PersistentDataType.TAG_CONTAINER);
        }else{
            blockPdc = blockDataPdc.getAdapterContext().newPersistentDataContainer();
            blockDataPdc.set(blockKey,PersistentDataType.TAG_CONTAINER,blockPdc);
        }
    }

    public static NamespacedKey generateBlockKey(Plugin plugin,Block block){
        int x = Math.abs(block.getX() % 16);
        int y = block.getY();
        int z = Math.abs(block.getZ() % 16);

        return new NamespacedKey(plugin, x + "_" + y + "_" + z);
    }

    private void save(){
        if(isEmpty()){
            blockDataPdc.remove(blockKey);
        } else {
            blockDataPdc.set(blockKey,PersistentDataType.TAG_CONTAINER,blockPdc);
        }

        if(blockDataPdc.isEmpty()){
            chunkPdc.remove(chunkKey);
        } else {
            chunkPdc.set(chunkKey,PersistentDataType.TAG_CONTAINER,blockDataPdc);
        }

        chunk.setForceLoaded(true);
        chunk.setForceLoaded(false);
    }

    @Override
    public <P, C> void set(@NotNull NamespacedKey key, @NotNull PersistentDataType<P, C> type, @NotNull C value) {
        blockPdc.set(key,type,value);
        save();
    }

    @Override
    public void remove(@NotNull NamespacedKey key) {
        blockPdc.remove(key);
        save();
    }

    @Override
    public void readFromBytes(byte @NotNull [] bytes, boolean clear) throws IOException {
        blockPdc.readFromBytes(bytes,clear);
    }

    @Override
    public <P, C> boolean has(NamespacedKey key, PersistentDataType<P, C> type) {
        return blockPdc.has(key,type);
    }

    @Override
    public boolean has(NamespacedKey key) {
        return blockPdc.has(key);
    }

    @Override
    public <P, C> @Nullable C get(NamespacedKey key, PersistentDataType<P, C> type) {
        return blockPdc.get(key,type);
    }

    @Override
    public <P, C> C getOrDefault(NamespacedKey key, PersistentDataType<P, C> type, C defaultValue) {
        return blockPdc.getOrDefault(key,type,defaultValue);
    }

    @Override
    public Set<NamespacedKey> getKeys() {
        return blockPdc.getKeys();
    }

    @Override
    public boolean isEmpty() {
        return blockPdc.isEmpty();
    }

    @Override
    public void copyTo(PersistentDataContainer other, boolean replace) {
        blockPdc.copyTo(other,replace);
    }

    @Override
    public PersistentDataAdapterContext getAdapterContext() {
        return blockPdc.getAdapterContext();
    }

    @Override
    public byte[] serializeToBytes() throws IOException {
        return blockPdc.serializeToBytes();
    }
}
