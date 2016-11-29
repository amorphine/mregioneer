import mregioneer.mcaliases.Block;
import mregioneer.mcaliases.World;
import mregioneer.utils.Coordinates3d;
import org.ini4j.Ini;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestBlock {
    @Test
    public void testAdd() {

        World world;
        Coordinates3d cords = new Coordinates3d(-3, 64, 6);
        byte id_to_make = 6;

        try {

            //initializing path to region folder
            String presets_url = TestReadWrite.class.getClassLoader().getResource("local_presets.ini").getPath();
            Ini ini = new Ini(new File(presets_url));
            String path_to_mca_folder = ini.get("paths", "world_path", String.class);

            world = new World(new File (path_to_mca_folder));
            Block test_block = world.getBlock(cords);
            test_block.setId_a(id_to_make);

            world.save();

            world = new World(new File (path_to_mca_folder));
            test_block = world.getBlock(cords);
            byte id = world.getBlockId(cords);

            assertTrue(id == id_to_make);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
