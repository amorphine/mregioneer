package mregioneer.mcaliases;


import mregioneer.utils.Coordinates3d;

public class Block {
    private final int x;
    private final int z;
    private final int y;

    private short id_a;
    private short id_b;

    private TileEntity tileEntity = null;

    public Block(Coordinates3d cords, short id_a) {
        this.x = cords.getX();
        this.z = cords.getZ();
        this.y = cords.getY();

        this.id_a = id_a;
    }
}
