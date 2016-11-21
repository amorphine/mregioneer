package ru.wpstuio.amorphine.mcaliases;


import com.flowpowered.nbt.ListTag;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.IntTag;
import com.sun.istack.internal.Nullable;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Created by amorphine on 21.11.16.
 */
public class Chunk {
    private CompoundTag tag;

    private int x;
    private int z;

    private byte[] blocks;

    public Chunk(CompoundTag tag) {
        this.tag = tag;

        CompoundTag c_tag_level = (CompoundTag) tag.get("Level");
        IntTag x = (IntTag) c_tag_level.get("xPos");
        IntTag z = (IntTag) c_tag_level.get("zPos");

        this.x = x.data;
        this.z = z.data;
    }
}
