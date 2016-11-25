/**
 * Created by amorphine on 23.11.16.
 */

import org.junit.Test;
import ru.wpstuio.amorphine.mcaliases.Chunk;
import ru.wpstuio.amorphine.mcaliases.Region;
import ru.wpstuio.amorphine.mcaliases.World;
import ru.wpstuio.amorphine.utils.Coordinates2d;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class TestReadWrite {
    @Test
    public void testAdd() {

        World world;
        byte id_to_make = 26;

        try {
            world = new World(new File ("/home/amorphine/mine-thermos/world/region"));
            Region rg = world.getRegion(-1, 0);

            Coordinates2d chunk_cords = new Coordinates2d(31, 0);

            Chunk chunk = rg.getChunk(chunk_cords);
            chunk.changeBlockId(-3, 64,6, id_to_make);
            rg.saveChunk(chunk);

            world = new World(new File ("/home/amorphine/mine-thermos/world/region"));
            rg = world.getRegion(-1, 0);
            chunk = rg.getChunk(chunk_cords);
            byte id = chunk.getBlockId(-3, 64,6);

            assertTrue(id == id_to_make);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}