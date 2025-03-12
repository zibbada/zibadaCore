package com.zibada.zibadaCore.blocks;

import java.util.HashMap;

public class BlockRegistry {
    private static final HashMap<String,BaseBlock> ITEM_MAP = new HashMap<>();

    public static void register(String id, BaseBlock block){
        ITEM_MAP.put(id,block);
    }

    public static BaseBlock getBlock(String id){
        return  ITEM_MAP.get(id);
    }

}
