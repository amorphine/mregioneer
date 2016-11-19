package ru.wpstuio.amorphine;

import com.flowpowered.nbt.CompoundMap;
import com.flowpowered.nbt.CompoundTag;
import com.flowpowered.nbt.ListTag;
import com.flowpowered.nbt.Tag;
import com.flowpowered.nbt.regionfile.SimpleRegionFileReader;
import com.flowpowered.nbt.stream.NBTInputStream;
import net.minecraft.world.level.chunk.storage.RegionFile;

import java.io.File;
import java.io.InputStream;
import java.nio.ByteOrder;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Main {

    public static void main(String[] args) {
        File file = new File("C:\\Users\\User\\Downloads\\r.-1.0.mca");

        RegionFile region = new RegionFile(file);

        try {
            NBTInputStream stream = new NBTInputStream(region.getChunkDataInputStream(31, 0), false, ByteOrder.BIG_ENDIAN);

            CompoundTag tag = (CompoundTag) stream.readTag();

            Map levels = tag.getValue();
            CompoundTag level_tag  = (CompoundTag) levels.get("Level");

            Map level_chars_map = (Map) level_tag.getValue();
            List sections_list = ((ListTag) level_chars_map.get("Sections")).getValue();
            byte[][][] bytearr = new byte[16][16][16];

            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}