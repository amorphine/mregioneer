package ru.wpstuio.amorphine.mcaliases;

import net.minecraft.world.level.chunk.storage.RegionFile;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by amorphine on 21.11.16.
 */
public class World {
    private File world_dir;

    private HashMap region_files = new HashMap<int[], File>();
    private HashMap regions = new HashMap<int[], Region>();

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
                region_files.put(cords, region_file);

                RegionFile rg_file = new RegionFile(region_file);
                Region region = new Region(rg_file);
                regions.put(cords, region);
            }
        }
    }

}
