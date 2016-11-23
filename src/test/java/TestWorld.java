/**
 * Created by amorphine on 23.11.16.
 */

import com.mojang.nbt.NbtIo;
import net.minecraft.world.level.chunk.storage.RegionFile;
import org.ini4j.Ini;
import org.ini4j.Profile;
import org.junit.Test;
import ru.wpstuio.amorphine.mcaliases.Chunk;
import ru.wpstuio.amorphine.mcaliases.Region;
import ru.wpstuio.amorphine.mcaliases.World;

import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class TestWorld {
    @Test
    public void testAdd() {

        World world;

        try {
            world = new World(new File ("/home/amorphine/mine-thermos/world/region"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        /*
        String path_to_local_ini = "local_presets.ini";
        String path_to_ini = "presets.ini";

        InputStream local_ini_stream = TestWorld.class.getResourceAsStream(path_to_local_ini);
        InputStream ini_stream = TestWorld.class.getResourceAsStream(path_to_ini);

        Ini ini = null;
        try {
             ini = new Ini(local_ini_stream);
        } catch (IOException e){
            fail("Can not read presets file");
        }

        Profile.Section section = ini.get("paths");
        String world_path = section.get("world_path");

        File world_folder = new File(world_path);
        if(!world_folder.exists())
            fail("World folder was not found");

        File region_folder = new File(world_folder.getAbsolutePath() + "/region");
        File region_file = new File(region_folder.getAbsolutePath() + "/r.-1.0.mca");

        RegionFile region = new RegionFile(region_file);

        Region rg = new Region(region);
        Chunk[][] chunks = rg.getChunks();

        byte id_to_make = 16;
        chunks[31][0].changeBlockId(-3, 64, 6,  id_to_make);

        try {
            DataOutputStream stream = region.getChunkDataOutputStream(31, 0);
            NbtIo.write(chunks[31][0].getTag(), stream);
            stream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        rg = new Region(region);
        chunks = rg.getChunks();

        byte result = chunks[31][0].getBlockId(-3, 64, 6);
        assertTrue(id_to_make == result);
        */
    }
}