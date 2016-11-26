package ru.wpstuio.amorphine.mcaliases;


import com.mojang.nbt.ByteArrayTag;
import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.IntTag;
import com.mojang.nbt.ListTag;
import ru.wpstuio.amorphine.utils.Coordinates2d;

import static ru.wpstuio.amorphine.utils.Formulae.localBlockFromBlock;
import static ru.wpstuio.amorphine.utils.Formulae.sectionFromBlock;


public class Chunk {

    private CompoundTag tag;

    private final int global_x;
    private final int global_z;

    private final int local_x;
    private final int local_z;

    private CompoundTag[] sections = new CompoundTag[15];

    public Chunk(Coordinates2d local_cords, CompoundTag tag) {
        this.tag = tag;

        CompoundTag c_tag_level = (CompoundTag) tag.get("Level");

        IntTag x = (IntTag) c_tag_level.get("xPos");
        IntTag z = (IntTag) c_tag_level.get("zPos");

        this.global_x = x.data;
        this.global_z = z.data;

        this.local_x = local_cords.getX();
        this.local_z = local_cords.getZ();

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

    public int getGlobalX() {
        return global_x;
    }

    public int getGlobalZ() {
        return global_z;
    }

    public int getLocalX() {
        return local_x;
    }

    public int getLocalZ() {
        return local_z;
    }

    public CompoundTag getTag() {
        return tag;
    }

    /**
     * Changes specified block basic id to with the given one
     * @param x represents EAST - SOUTH axe
     * @param y represents height axe
     * @param z represents NORTH - SOUTH axe
     * @param id which the block should be
     */
    public void changeBlockId(int x, int y, int z, byte id) {

        int local_x = localBlockFromBlock(x);
        int local_y = localBlockFromBlock(y);
        int local_z = localBlockFromBlock(z);

        int offset = local_y * 16 * 16 + local_z * 16 + local_x;

        int section_index = sectionFromBlock(y);

        ByteArrayTag block_bytes_tag = (ByteArrayTag) sections[section_index].get("Blocks");

        block_bytes_tag.data[offset] = id;
    }

    /**
     * Returns id of the block with specified XYZ coordinates
     * @param x represents EAST - SOUTH axe
     * @param y represents height axe
     * @param z represents NORTH - SOUTH axe
     * @return byte of the basic id of the block
     */
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
