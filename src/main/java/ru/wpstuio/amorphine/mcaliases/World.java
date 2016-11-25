package ru.wpstuio.amorphine.mcaliases;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import net.minecraft.world.level.chunk.storage.RegionFile;
import ru.wpstuio.amorphine.utils.Coordinates2d;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by amorphine on 21.11.16.
 */
public class World {

    private final File world_dir;

    Map<Coordinates2d, RegionFile> region_files = new HashMap<Coordinates2d, RegionFile>();

    private ConcurrentMap<Coordinates2d, Region> regions = new ConcurrentLinkedHashMap.Builder<Coordinates2d, Region>()
            .maximumWeightedCapacity(10)
            .build();

    public World(File world_dir) throws IOException {

        this.world_dir = world_dir;
        if (!world_dir.exists()) {
            throw new IOException("Cannot access " + world_dir.getAbsolutePath() + ": No such file or directory");
        }

        String[] file_list = world_dir.list();
        for(String file_name: file_list) {
            if (file_name.endsWith(".mca")) {

                String[] splitted_file_name = file_name.split("\\.");

                if (splitted_file_name.length < 4) {
                    continue;
                }

                String path_to_region = world_dir.getAbsolutePath() + "/" + file_name;
                File mca_file = new File(path_to_region);
                RegionFile region_file = new RegionFile(mca_file);

                int x = Integer.parseInt(splitted_file_name[1]);
                int z =Integer.parseInt(splitted_file_name[2]);

                Coordinates2d region_file_coord = new Coordinates2d(x, z);
                region_files.put(region_file_coord, region_file);
            }
        }
    }

    public Region getRegion(int x, int z) {

        Coordinates2d cords = new Coordinates2d(x, z);

        if(regions.containsKey(cords)) {
            return  regions.get(cords);
        } else {
            RegionFile region_file = region_files.get(cords);

            Region region = new Region(region_file);
            regions.put(cords, region);

            return region;
        }
    }
}
