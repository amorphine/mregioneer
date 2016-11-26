package ru.wpstuio.amorphine.mcaliases;

import com.mojang.nbt.CompoundTag;


public class Section {
    private byte index;
    private CompoundTag tag;

    public Section(byte index, CompoundTag tag) {
        this.index = index;
        this.tag = tag;
    }
}
