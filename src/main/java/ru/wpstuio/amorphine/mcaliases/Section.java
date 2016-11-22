package ru.wpstuio.amorphine.mcaliases;

import com.mojang.nbt.CompoundTag;

/**
 * Created by amorphine on 22.11.16.
 */
public class Section {
    private byte index;
    private CompoundTag tag;

    public Section(byte index, CompoundTag tag) {
        this.index = index;
        this.tag = tag;
    }
}
