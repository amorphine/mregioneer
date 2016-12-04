import mregioneer.mcaliases.World;
import mregioneer.utils.Point3d;
import org.ini4j.Ini;
import org.junit.Test;

import java.io.File;
import java.util.Random;

import static org.junit.Assert.assertTrue;

public class TestRegionAutoRemove {
    @Test
    public void testAdd() {
        World world;
        try {
            //initializing path to region folder
            String presets_url = TestReadWrite.class.getClassLoader().getResource("local_presets.ini").getPath();
            Ini ini = new Ini(new File(presets_url));
            String path_to_mca_folder = ini.get("paths", "world_path", String.class);

            world = new World(new File(path_to_mca_folder), 1);

            Random rand = new Random(1);
            byte id = (byte) rand.nextInt(24);

            Point3d point1 = new Point3d(1, 1, 64);
            Point3d point2 = new Point3d(-2,-2,64);

            world.changeBlockID(point1, id);
            world.changeBlockID(point2, id);

            world.save();

            assertTrue(world.getBlockId(point1) == world.getBlockId(point2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
