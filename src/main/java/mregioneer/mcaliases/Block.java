package mregioneer.mcaliases;


import com.mojang.nbt.ByteArrayTag;
import mregioneer.utils.Coordinates3d;
import mregioneer.utils.Formulae;
import mregioneer.utils.Helpers;

public class Block {
    private final int x;
    private final int z;
    private final int y;

    private final int section_index;

    private final int offset;

    private byte id_a;
    private byte id_b;

    private byte[] blocks;
    private byte[] add;

    private TileEntity tileEntity = null;

    protected Block(Coordinates3d cords, Section section) {
        this.x = cords.getX();
        this.z = cords.getZ();
        this.y = cords.getY();

        this.blocks = ((ByteArrayTag)section.get("Blocks")).data;

        int local_x = Formulae.localBlockFromBlock(x);
        int local_y = Formulae.localBlockFromBlock(y);
        int local_z = Formulae.localBlockFromBlock(z);

        int section_index = Formulae.sectionFromBlock(y);
        this.section_index = section_index;

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

    /* method should work with 4-bits
    public void setId_b(byte id_b) {
        this.id_b = id_b;
    }
    */

    /* not implemented yet
    public TileEntity getTileEntity() {
        return tileEntity;
    }
    */

    public void setTileEntity(TileEntity tileEntity) {
        this.tileEntity = tileEntity;
    }
}
