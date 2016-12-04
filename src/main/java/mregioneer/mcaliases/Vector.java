package mregioneer.mcaliases;

import mregioneer.utils.Point3d;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.max;
import static java.lang.Math.min;

/**
 * Represents a set of blocks included in a cuboid specified with two coordinates;
 */
public class Vector {

    private Point3d cords1;
    private Point3d cords2;

    public Vector(Point3d cords1, Point3d cords2) {
        int min_x = min(cords1.x, cords2.x);
        int max_x = max(cords1.x, cords2.x);

        int min_z = min(cords1.z, cords2.z);
        int max_z = max(cords1.z, cords2.z);

        int min_y = min(cords1.y, cords2.y);
        int max_y = max(cords1.y, cords2.y);

        this.cords1 = new Point3d(min_x, min_y, min_z);
        this.cords2 = new Point3d(max_x, max_y, max_z);
    }

    public int getXstart() {
        return cords1.x;
    }

    public int getYstart() {
        return cords1.y;
    }

    public int getZstart() {
        return cords1.z;
    }

    public int getXend() {
        return cords2.x;
    }

    public int getYend() {
        return cords2.y;
    }

    public int getZend() {
        return cords2.z;
    }


    public List<Point3d> getCoordinatesArray() {

        int height = getZend() - getZstart() + 1;
        int width = getXend() - getXstart() + 1;
        int depth = getYend() - getYstart() + 1;

        //Coordinates3d[] cords_arr = new Coordinates3d[depth * height * width];
        ArrayList cords_arr_list = new ArrayList<Point3d>();

        for(int z = getZstart(); z <= getZend(); z++) {
            for(int y = getYstart(); y <= getYend(); y++) {
                for(int x = getXstart(); x <= getXend(); x++) {
                    cords_arr_list.add(new Point3d(x, y, z));
                }
            }
        }
        return cords_arr_list;
    }
}
