package ru.wpstuio.amorphine.mcaliases;


import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.NbtIo;
import net.minecraft.world.level.chunk.storage.RegionFile;

import java.io.IOException;
import java.nio.ByteOrder;

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
                    tag = NbtIo.read(region_file.getChunkDataInputStream(i, j));
                    region_file.getChunkDataInputStream(i, j);
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
}
