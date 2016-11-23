package ru.wpstuio.amorphine.mcaliases;


import com.mojang.nbt.ByteArrayTag;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.IntTag;
import com.mojang.nbt.ListTag;

import static ru.wpstuio.amorphine.utils.Formulae.localBlockFromBlock;
import static ru.wpstuio.amorphine.utils.Formulae.sectionFromBlock;

/**
 * Created by amorphine on 21.11.16.
 */
public class Chunk {

    private CompoundTag tag;

    private int x;
    private int z;

    private CompoundTag[] sections = new CompoundTag[15];

    public Chunk(CompoundTag tag) {
        this.tag = tag;

        CompoundTag c_tag_level = (CompoundTag) tag.get("Level");
        IntTag x = (IntTag) c_tag_level.get("xPos");
        IntTag z = (IntTag) c_tag_level.get("zPos");

        this.x = x.data;
        this.z = z.data;

        ListTag sections_tag =  (ListTag) c_tag_level.get("Sections");
        for(int i = 0; i <= 15; i++){
            CompoundTag section;
            try{
                section = (CompoundTag) sections_tag.get(i);
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
            sections[i] = section;
        }
    }

    public CompoundTag getTag() {
        return tag;
    }

    public void changeBlockId(int x, int y, int z, byte id) {

        int local_x = localBlockFromBlock(x);
        int local_y = localBlockFromBlock(y);
        int local_z = localBlockFromBlock(z);

        int offset = local_y * 16 * 16 + local_z * 16 + local_x;

        int section_index = sectionFromBlock(y);

        ByteArrayTag block_bytes_tag = (ByteArrayTag) sections[section_index].get("Blocks");

        block_bytes_tag.data[offset] = id;
    }

    public byte getBlockId(int x, int y, int z) {
        int local_x = localBlockFromBlock(x);
        int local_y = localBlockFromBlock(y);
        int local_z = localBlockFromBlock(z);

        int section_index = sectionFromBlock(y);

        int offset = local_y * 16 * 16 + local_z * 16 + local_x;

        ByteArrayTag block_bytes_tag = (ByteArrayTag) sections[section_index].get("Blocks");

        return block_bytes_tag.data[offset];
    }
}
