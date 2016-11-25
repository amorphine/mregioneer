package ru.wpstuio.amorphine.mcaliases;


import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.NbtIo;
import net.minecraft.world.level.chunk.storage.RegionFile;
import org.apache.commons.collections4.map.LRUMap;
import ru.wpstuio.amorphine.utils.Coordinates2d;
import ru.wpstuio.amorphine.utils.Formulae;

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

                Coordinates2d local_cords = new Coordinates2d(i, j);

                Chunk chunk = null;
                if (tag != null)
                    chunk = new Chunk(local_cords, tag);

                this.chunks[i][j] = chunk;
            }
        }
    }

    public Chunk[][] getChunks() {
        return chunks;
    }

    public Chunk getChunk(Coordinates2d cords) {
        return chunks[cords.getX()][cords.getZ()];
    }

    public void saveChunk(Chunk chunk) throws IOException{

        int local_x = chunk.getLocalX();
        int local_z = chunk.getLocalZ();

        DataOutputStream stream = region_file.getChunkDataOutputStream(local_x, local_z);

        NbtIo.write(chunks[local_x][local_z].getTag(), stream);

        stream.close();
    }

    /**
     *
     * @param cords
     * @param tag
     * @throws IOException
     */
    public void saveTagAsChunk(Coordinates2d cords, CompoundTag tag) throws IOException{

        int global_x = cords.getX();
        int global_z = cords.getZ();

        int local_x = Formulae.localChunkFromChunk(global_x);
        int local_z = Formulae.localChunkFromChunk(global_z);

        DataOutputStream stream = region_file.getChunkDataOutputStream(local_x, local_z);

        NbtIo.write(chunks[local_x][local_z].getTag(), stream);

        stream.close();
    }
}
