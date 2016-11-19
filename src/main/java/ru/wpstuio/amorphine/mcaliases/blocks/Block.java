package ru.wpstuio.amorphine.mcaliases.blocks;

/**
 * Created by amorphine on 19.11.16.
 */
public abstract class Block {
    private int x;
    private int z;
    private int y;

    public Block(int x, int z, int y) {
        this.x = x;
        this.z = z;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
}
