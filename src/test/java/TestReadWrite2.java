import org.ini4j.Ini;
import org.junit.Test;
import mregioneer.mcaliases.World;
import mregioneer.utils.Coordinates3d;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;


public class TestReadWrite2 {
    @Test
    public void testAdd() {

        World world;
        byte id_to_make = 14;

        try {
            //initializing path to region folder
            String presets_url = TestReadWrite.class.getClassLoader().getResource("local_presets.ini").getPath();
            Ini ini = new Ini(new File(presets_url));
            String path_to_mca_folder = ini.get("paths", "world_path", String.class);

            world = new World(new File (path_to_mca_folder));

            Coordinates3d cords;
            for(int inty = 6; inty < 100; inty++){
                for(int intz = -10; intz < 10; intz++){
                    for(int intx = -10; intx < 10; intx++){
                        world.changeBlockID(new Coordinates3d(intx, inty, intz), id_to_make);
                    }
                }
            }
            world.save();

            world = new World(new File (path_to_mca_folder));
            cords = new Coordinates3d(1, 66 ,1);
            byte id = world.getBlockId(cords);

            assertTrue(id == id_to_make);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
