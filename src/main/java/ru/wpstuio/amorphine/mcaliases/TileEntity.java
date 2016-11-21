package ru.wpstuio.amorphine.mcaliases;

import com.flowpowered.nbt.CompoundTag;

/**
 * Created by amorphine on 21.11.16.
 */
public class TileEntity {
    private CompoundTag tag;

    private int x;
    private int z;
    private int y;

    public TileEntity(CompoundTag tag) {
        this.tag = tag;
    }
}
