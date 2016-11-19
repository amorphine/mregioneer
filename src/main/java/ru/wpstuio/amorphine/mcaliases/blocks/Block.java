package ru.wpstuio.amorphine.mcaliases.blocks;


import ru.wpstuio.amorphine.mcaliases.chunks.Chunk;

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

    public Chunk getChunk() {
        int chunk_x = x % 16;
        int chunk_z = z % 16;

        Chunk chunk = new Chunk(chunk_x, chunk_z);
        return chunk;
    }
}
