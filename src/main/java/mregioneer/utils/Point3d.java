package mregioneer.utils;

/**
 * The class has been created to use as a key for hash maps containing chunks, regions and so on.
 * All new inctanses of the class with simular params returns the same hash code
 */
public class Point3d {
    public final int x;
    public final int y;
    public final int z;

    private final String coordinates_as_string;

    public Point3d(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;

        this.coordinates_as_string = "(" + x + ";" + y + ";" + z + ")";
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getZ() {
        return z;
    }

    public String toString() {
        return coordinates_as_string;
    }

    public int hashCode() {
        return this.coordinates_as_string.hashCode();
    }

    public boolean equals(Object obj) {
        if(obj == this)
            return true;


        if(obj == null)
            return false;

        if(!(getClass() == obj.getClass()))
            return false;
        else
        {
            Point3d tmp = (Point3d)obj;
            return tmp.toString().equals(this.toString());
        }
    }
}
