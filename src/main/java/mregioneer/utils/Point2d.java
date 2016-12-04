package mregioneer.utils;

/**
 * The class has been created to use as a key for hash maps containing chunks, regions and so on.
 * All new inctanses of the class with simular params returns the same hash code
 */
public class Point2d {
    public final int x;
    public final int z;

    private final String coordinates_as_string;

    public Point2d(int x, int z) {
        this.x = x;
        this.z = z;

        this.coordinates_as_string = "(" + x + ";" + z + ")";
    }

    public int getX() {
        return x;
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
            Point2d tmp = (Point2d)obj;
            return tmp.toString().equals(this.toString());
        }
    }
}
