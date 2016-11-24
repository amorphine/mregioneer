package ru.wpstuio.amorphine.mcaliases;


import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.NbtIo;
import net.minecraft.world.level.chunk.storage.RegionFile;
import org.apache.commons.collections4.map.LRUMap;

import java.io.DataInputStream;

/**
 * Created by amorphine on 21.11.16.
 */
public class Region {

    private Chunk chunks[][] = new Chunk[32][32];

    public Region(RegionFile region_file) {
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
}
