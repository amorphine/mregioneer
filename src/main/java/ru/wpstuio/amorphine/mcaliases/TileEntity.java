package ru.wpstuio.amorphine.mcaliases;

import com.flowpowered.nbt.CompoundTag;


public class TileEntity {
    private CompoundTag tag;

    private int x;
    private int z;
    private int y;

    public TileEntity(CompoundTag tag) {
        this.tag = tag;
    }
}
