package ru.wpstuio.amorphine.mcaliases;

import com.mojang.nbt.ByteArrayTag;
import com.mojang.nbt.ByteTag;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.Tag;

import java.util.Arrays;


public class Section {
    private final byte index;
    private CompoundTag tag;


    public Section(byte index, CompoundTag tag) {
        this.index = index;
        this.tag = tag;
    }

    public Section(byte index) {
        this.index = index;

        CompoundTag main_tag = new CompoundTag();

        byte[] byte4096 = new byte[4096];
        Arrays.fill(byte4096, (byte) 0);

        byte[] byte2048 = new byte[2048];
        Arrays.fill(byte2048, (byte) 0);

        main_tag.put("Add",         new ByteArrayTag("Add", byte2048.clone()));
        main_tag.put("Blocks",      new ByteArrayTag("Blocks", byte4096.clone()));
        main_tag.put("SkyLight",    new ByteArrayTag("SkyLight", byte2048.clone()));
        main_tag.put("Y",           new ByteTag("Y", index));
        main_tag.put("BlockLight",  new ByteArrayTag("BlockLight", byte2048.clone()));
        main_tag.put("Data",        new ByteArrayTag("Data", byte2048.clone()));

        this.tag = main_tag;
    }

    public byte getIndex() {
        return index;
    }

    public CompoundTag getTag() {
        return tag;
    }

    /**
     * Returns tag with given name
     * @param name string name of the tag to be returned
     * @return
     */
    public Tag get(String name) {
        return this.tag.get(name);
    }
}
