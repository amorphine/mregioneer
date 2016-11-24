package ru.wpstuio.amorphine.mcaliases;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import net.minecraft.world.level.chunk.storage.RegionFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * Created by amorphine on 21.11.16.
 */
public class World {

    private File world_dir;

    private HashMap<int[], RegionFile> region_files = new HashMap<int[], RegionFile>();

    private ConcurrentMap<int[], Region> regions = new ConcurrentLinkedHashMap.Builder<int[], Region>()
            .maximumWeightedCapacity(2)
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
                File region_file = new File(path_to_region);

                int[] cords = {
                        Integer.parseInt(splitted_file_name[1]),
                        Integer.parseInt(splitted_file_name[2])
                };

                region_files.put(cords, new RegionFile(region_file));
            }
        }
    }
    public Region getRegion(int x, int z) {
        int[] coords = {x, z};

        if(regions.containsKey(coords)) {
            return  regions.get(coords);
        } else {
            Region region = new Region(region_files.get(coords));
            regions.put(coords, region);

            return region;
        }
    }
}
