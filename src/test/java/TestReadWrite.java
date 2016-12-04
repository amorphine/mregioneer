/**
 * Created by amorphine on 23.11.16.
 */

import org.ini4j.Ini;
import org.junit.Test;
import mregioneer.mcaliases.Chunk;
import mregioneer.mcaliases.Region;
import mregioneer.mcaliases.World;
import mregioneer.utils.Point2d;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestReadWrite {
    @Test
    public void testAdd() {

        World world;
        byte id_to_make = 3;

        try {

            //initializing path to region folder
            String presets_url = TestReadWrite.class.getClassLoader().getResource("local_presets.ini").getPath();
            Ini ini = new Ini(new File(presets_url));
            String path_to_mca_folder = ini.get("paths", "world_path", String.class);


            world = new World(new File (path_to_mca_folder));
            Region rg = world.getRegion(-1, 0);

            Point2d chunk_cords = new Point2d(31, 0);

            Chunk chunk = rg.getChunk(chunk_cords);
            chunk.changeBlockId(-3, 64,6, id_to_make);
            rg.saveChunk(chunk);

            world = new World(new File (path_to_mca_folder));
            rg = world.getRegion(-1, 0);
            chunk = rg.getChunk(chunk_cords);
            byte id = chunk.getBlockId(-3, 64,6);

            assertTrue(id == id_to_make);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}