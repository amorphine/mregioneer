package mregioneer.mcaliases;


import com.mojang.nbt.ByteArrayTag;
import com.mojang.nbt.CompoundTag;
import mregioneer.utils.Coordinates3d;
import mregioneer.utils.Formulae;
import mregioneer.utils.Helpers;

import java.util.Arrays;

public class Block {
    public final int x;
    public final int z;
    public final int y;

    private final CompoundTag section;
    private final int section_index;
    private final int offset;

    private byte id_a;
    private byte id_b;

    private byte[] blocks;
    private byte[] add;

    private TileEntity tileEntity = null;

    protected Block(Coordinates3d cords, Section section) {
        this.blocks = ((ByteArrayTag)section.get("Blocks")).data;

        this.x = cords.getX();
        this.z = cords.getZ();
        this.y = cords.getY();

        int section_index = Formulae.sectionFromBlock(y);
        this.section_index = section_index;
        this.section = section.getTag();

        int local_x = Formulae.localBlockFromBlock(x);
        int local_y = Formulae.localBlockFromBlock(y);
        int local_z = Formulae.localBlockFromBlock(z);
        this.offset = local_y * 16 * 16 + local_z * 16 + local_x;

        this.id_a = blocks[offset];
        try {
            this.add = ((ByteArrayTag)section.get("Add")).data;
            this.id_b = Helpers.Nibble4(add, offset);
        } catch (NullPointerException e) {
            add = null;
            this.id_b = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getZ() {
        return z;
    }

    public int getY() {
        return y;
    }

    public byte getId_a() {
        return id_a;
    }

    public void setId_a(byte id_a) {
        this.blocks[offset] = id_a;
    }

    public byte getId_b() {
        return id_b;
    }

    public void setId_b(byte id_b) {
        if ((add == null) && (id_b != 0)) {
            add = new byte[2048];
            Arrays.fill(add, (byte) 0);

            add = Helpers.writeNibble4(add, id_b, this.offset);

            ByteArrayTag bytes_tag = new ByteArrayTag("Add", add);

            section.put("Add", bytes_tag);
        }
    }


    /* not implemented yet
    public TileEntity getTileEntity() {
        return tileEntity;
    }
    */

    public void setTileEntity(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }
}
