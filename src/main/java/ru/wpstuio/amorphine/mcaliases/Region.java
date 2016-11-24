package ru.wpstuio.amorphine.mcaliases;


import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.NbtIo;
import net.minecraft.world.level.chunk.storage.RegionFile;
import org.apache.commons.collections4.map.LRUMap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by amorphine on 21.11.16.
 */
public class Region {

    private RegionFile region_file;

    private Chunk chunks[][] = new Chunk[32][32];

    public Region(RegionFile region_file) {
        this.region_file = region_file;

        for(int i = 31; i >= 0; i--) {
            for(int j = 31; j >= 0; j--) {
                CompoundTag tag;
                try {
                    DataInputStream stream = region_file.getChunkDataInputStream(i, j);
                    tag = NbtIo.read(stream);
                    stream.close();

                } catch (Exception e) {
                    tag = null;
                }

                Chunk chunk = null;
                if (tag != null)
                    chunk = new Chunk(tag);

                this.chunks[i][j] = chunk;
            }
        }
    }

    public Chunk[][] getChunks() {
        return chunks;
    }

    public Chunk getChunk(int x, int z) {
        return chunks[x][z];
    }

    public void saveChunk(Chunk chunk) throws IOException{
        DataOutputStream stream = region_file.getChunkDataOutputStream(chunk.getX(), chunk.getZ());

        NbtIo.write(chunks[chunk.getX()][chunk.getZ()].getTag(), stream);

        stream.close();
    }
}
