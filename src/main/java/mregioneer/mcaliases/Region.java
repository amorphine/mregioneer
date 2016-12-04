package mregioneer.mcaliases;


import com.mojang.nbt.CompoundTag;
import com.mojang.nbt.NbtIo;
import mregioneer.utils.Point3d;
import net.minecraft.world.level.chunk.storage.RegionFile;
import mregioneer.utils.Coordinates2d;
import mregioneer.utils.Formulae;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import static mregioneer.utils.Formulae.localChunkFromBlock;

public class Region {

    private final RegionFile region_file;

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

    /**
     * Returns a chunk with given local coordinates from region
     * @param cords XZ coordinates for chunk
     * @return
     */
    public Chunk getChunk(Coordinates2d cords) {
        return chunks[cords.getX()][cords.getZ()];
    }

    /**
     * Saves given chunk data to region file;
     * @param chunk chunk to be saved to mca-file
     * @throws IOException
     */
    public void saveChunk(Chunk chunk) throws IOException{

        int local_x = chunk.getLocalX();
        int local_z = chunk.getLocalZ();

        DataOutputStream stream = region_file.getChunkDataOutputStream(local_x, local_z);

        NbtIo.write(chunk.getTag(), stream);

        stream.close();
    }

    /**
     * Saves given tag as a chunk to mca-file;
     * @param cords local XZ coordinates the chunk will be saved to in mca file
     * @param tag
     * @throws IOException
     */
    public void saveTagAsChunk(Coordinates2d cords, CompoundTag tag) throws IOException{

        int global_x = cords.getX();
        int global_z = cords.getZ();

        int local_x = Formulae.localChunkFromChunk(global_x);
        int local_z = Formulae.localChunkFromChunk(global_z);

        DataOutputStream stream = region_file.getChunkDataOutputStream(local_x, local_z);

        NbtIo.write(tag, stream);

        stream.close();
    }

    /**
     * Saves all property chunks to region file
     */
    public void save() throws IOException {
        for(int x = 31; x >= 0; x--) {
            for (int z = 31; z >= 0; z--) {

                Chunk chunk = chunks[x][z];

                if (chunk == null)
                        continue;

                CompoundTag tag = chunk.getTag();

                DataOutputStream stream = region_file.getChunkDataOutputStream(x, z);

                NbtIo.write(tag, stream);

                stream.close();
            }
        }
    }

    /**
     * Writes block id with given coordinates
     * @param cords
     * @param id
     * @throws IOException
     */
    public void changeBlockId(Point3d cords, byte id) throws IOException {
        int block_global_x = cords.getX();
        int block_global_z = cords.getZ();

        int chunk_local_x = localChunkFromBlock(block_global_x);
        int chunk_local_z = localChunkFromBlock(block_global_z);

        Chunk chunk = chunks[chunk_local_x][chunk_local_z];
        chunk.changeBlockId(cords, id);

    }

    /**
     * Returns block id with given coordinates
     * @param cords
     * @return
     */
    public byte getBlockId(Point3d cords) {
        int block_global_x = cords.getX();
        int block_global_z = cords.getZ();
        int block_global_y = cords.getY();

        int chunk_local_x = localChunkFromBlock(block_global_x);
        int chunk_local_z = localChunkFromBlock(block_global_z);

        Chunk chunk = chunks[chunk_local_x][chunk_local_z];
        byte id = chunk.getBlockId(block_global_x, block_global_y, block_global_z);

        return id;
    }

    public byte getBlockAddId(Point3d cords) {
        int block_global_x = cords.getX();
        int block_global_z = cords.getZ();
        int block_global_y = cords.getY();

        int chunk_local_x = localChunkFromBlock(block_global_x);
        int chunk_local_z = localChunkFromBlock(block_global_z);

        Chunk chunk = chunks[chunk_local_x][chunk_local_z];
        byte id = chunk.getBlockAddId(block_global_x, block_global_y, block_global_z);

        return id;
    }

    public Block getBlock(Point3d cords) {
        int block_global_x = cords.getX();
        int block_global_z = cords.getZ();

        int chunk_local_x = localChunkFromBlock(block_global_x);
        int chunk_local_z = localChunkFromBlock(block_global_z);

        Chunk chunk = chunks[chunk_local_x][chunk_local_z];

        return chunk.getBlock(cords);
    }
}
