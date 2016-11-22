package ru.wpstuio.amorphine;

import com.flowpowered.nbt.stream.NBTOutputStream;
import com.mojang.nbt.NbtIo;
import net.minecraft.world.level.chunk.storage.RegionFile;
import ru.wpstuio.amorphine.mcaliases.Chunk;
import ru.wpstuio.amorphine.mcaliases.Region;

import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteOrder;

import static com.sun.javafx.PlatformUtil.isWindows;

public class Main {

    //all this stuff is a test code to undestand how .mca files work.
    public static void main(String[] args) {


        File file;
        if (isWindows()) {
            file = new File("C:\\Users\\User\\Downloads\\r.-1.0.mca");
        } else {
            file = new File("/home/amorphine/mine-thermos/world/region/r.-1.0.mca");
        }

        RegionFile region = new RegionFile(file);

        Region rg = new Region(region);
        Chunk[][] chunks = rg.getChunks();

        byte id = 54;
        chunks[31][0].changeBlockId(-3, 64, 6,  id);

        try {
            DataOutputStream stream = region.getChunkDataOutputStream(31, 0);
            NbtIo.write(chunks[31][0].getTag(), stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
/*


        try {

            NBTInputStream stream = new NBTInputStream(region.getChunkDataInputStream(31, 0), false, ByteOrder.BIG_ENDIAN);

            CompoundTag tag = (CompoundTag) stream.readTag();

            Map levels = tag.getValue();
            CompoundTag level_tag  = (CompoundTag) levels.get("Level");

            Map level_chars_map = (Map) level_tag.getValue();
            List sections_list = ((ListTag) level_chars_map.get("Sections")).getValue();

            int x = -3;
            int z = 6;
            int y = 64;

            int region_x = regionFromBlock(x);
            int region_z = regionFromBlock(z);

            int section_index = sectionFromBlock(y);

            int local_chunk_x = localChunkFromBlock(x);
            int local_chunk_z = localChunkFromBlock(z);

            int local_block_x = localBlockFromBlock(x);
            int local_block_y = localBlockFromBlock(y);
            int local_block_z = localBlockFromBlock(z);

            CompoundTag section = (CompoundTag) sections_list.get(section_index);
            Map chars = section.getValue();

            ByteArrayTag block_bytes_tag = (ByteArrayTag) chars.get("Blocks");
            byte[] block_bytes = block_bytes_tag.getValue();
            int offset_a = local_block_y * 16 * 16 + local_block_z * 16 + local_block_x;
            byte target_a = block_bytes[offset_a];
            block_bytes[offset_a] = 54;

            ByteArrayTag add_bytes_tag = (ByteArrayTag) chars.get("Add");
            byte[] add_bytes = add_bytes_tag.getValue();
            byte target_b = Nibble4(add_bytes, offset_a);

            short BlockID = (short) (target_a + (target_b << 8));

            ByteArrayTag blocks_data_tag = (ByteArrayTag) chars.get("Blocks");
            byte[] blocks_data = blocks_data_tag.getValue();
            byte block_data = Nibble4(blocks_data, offset_a);


            stream.close();

            NBTOutputStream outputStream = new NBTOutputStream(region.getChunkDataOutputStream(31, 0), false, ByteOrder.BIG_ENDIAN);

            outputStream.writeTag(tag);

            outputStream.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        */
    }

    static byte Nibble4(byte[] arr, int index){
        return (byte) (index%2 == 0 ? arr[index/2]&0x0F : (arr[index/2]>>4)&0x0F);
    }

}