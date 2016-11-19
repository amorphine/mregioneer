package ru.wpstuio.amorphine.mcaliases.chunks;

import ru.wpstuio.amorphine.mcaliases.blocks.Block;

import static java.lang.Math.floor;

/**
 * Created by User on 19.11.2016.
 */
public class Chunk {
    int x;
    int z;

    private Region region;

    public Chunk(Block block) {
        Chunk chunk = block.getChunk();
        this.x = chunk.x;
        this.z = chunk.z;
    }

    public Chunk (int x, int z) {
        this.x = x;
        this.z = z;
    }

    public Region getRegion() {
        int regionX = (int)floor(x / 32.0);
        int regionZ = (int)floor(z / 32.0);

        Region region = new Region(regionX, regionZ);

        return region;
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
}
