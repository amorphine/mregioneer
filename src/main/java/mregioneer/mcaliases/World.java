package mregioneer.mcaliases;

import com.googlecode.concurrentlinkedhashmap.ConcurrentLinkedHashMap;
import com.googlecode.concurrentlinkedhashmap.EvictionListener;
import mregioneer.utils.Point2d;
import mregioneer.utils.Point3d;
import net.minecraft.world.level.chunk.storage.RegionFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static mregioneer.utils.Formulae.regionFromBlock;


public class World {

    private final File world_dir;

    private Map<Point2d, RegionFile> region_files = new HashMap<Point2d, RegionFile>();

    EvictionListener<Point2d, Region> listener;
    {
        listener = new EvictionListener<Point2d, Region>() {
            final ExecutorService executor = Executors.newSingleThreadExecutor();

            public void onEviction(Point2d region_point, final Region region) {
                executor.submit(new Callable<Void>() {
                    public Void call() throws IOException {
                        region.save();
                        return null;
                    }
                });
            }
        };
    }

    private ConcurrentMap<Point2d, Region> regions = new ConcurrentLinkedHashMap.Builder<Point2d, Region>()
            .maximumWeightedCapacity(10)
            .listener(listener)
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

                Point2d region_file_coord = new Point2d(x, z);
                region_files.put(region_file_coord, region_file);
            }
        }
    }
    
    public World(File world_dir, int region_limit) throws IOException {
        this(world_dir);

        this.regions = new ConcurrentLinkedHashMap.Builder<Point2d, Region>()
                .maximumWeightedCapacity(region_limit)
                .listener(listener)
                .build();
    }

    /**
     * Save all cashed regions to their files
     * @throws IOException
     */
    public void save() throws IOException {
        for(Map.Entry entry: regions.entrySet()) {
            Region region = (Region) entry.getValue();
            region.save();
        }
    }

    /**
     * Returns a region with specified coordinates from cache. If it is not in the cache, it is put there.
     * @param x
     * @param z
     * @return
     */
    public Region getRegion(int x, int z) {

        Point2d cords = new Point2d(x, z);

        if(regions.containsKey(cords)) {
            return  regions.get(cords);
        } else {
            RegionFile region_file = region_files.get(cords);

            Region region = new Region(region_file);
            regions.put(cords, region);

            return region;
        }
    }

    /**
     * Changes specified block basic id to with the given one
     * @param cords
     * @param id
     * @throws IOException
     */
    public void changeBlockID(Point3d cords, byte id) throws IOException {
        int region_x = regionFromBlock(cords.getX());
        int region_z = regionFromBlock(cords.getZ());

        Region region = this.getRegion(region_x, region_z);
        region.changeBlockId(cords, id);
    }

    /**
     * Returns id of the block with specified XYZ coordinates
     * @param cords
     * @return
     */
    public byte getBlockId(Point3d cords) {
        int region_x = regionFromBlock(cords.getX());
        int region_z = regionFromBlock(cords.getZ());

        Region region = this.getRegion(region_x, region_z);
        return region.getBlockId(cords);
    }

    public byte getBlockAddId(Point3d cords) {
        int region_x = regionFromBlock(cords.getX());
        int region_z = regionFromBlock(cords.getZ());

        Region region = this.getRegion(region_x, region_z);
        return region.getBlockAddId(cords);
    }

    public Block getBlock(Point3d cords) {
        int region_x = regionFromBlock(cords.getX());
        int region_z = regionFromBlock(cords.getZ());

        Region region = this.getRegion(region_x, region_z);
        return region.getBlock(cords);
    }
}
