package ru.wpstuio.amorphine.mcaliases.blocks;

/**
 * Created by amorphine on 19.11.16.
 */
public class Vector {
    private int x0;
    private int x1;

    private int z0;
    private int z1;

    private int y0;
    private int y1;

    public Vector(int x0, int x1, int z0, int z1, int y0, int y1) {

        this.x0 = x0;
        this.x1 = x1;

        this.z0 = z0;
        this.z1 = z1;

        this.y0 = y0;
        this.y1 = y1;
    }

    public Vector(Block block1, Block block2) {
        this.x0 = block1.getX();
        this.y0 = block1.getY();
        this.z0 = block1.getZ();

        this.x1 = block2.getX();
        this.y1 = block2.getY();
        this.z1 = block2.getZ();
    }
}
