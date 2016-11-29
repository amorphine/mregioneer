package mregioneer.utils;

/**
 * The class has been created to use as a key for hash maps containing chunks, regions and so on.
 * All new inctanses of the class with simular params returns the same hash code
 */
public class Coordinates3d {
    private final int x;
    private final int y;
    private final int z;

    private final String coordinates_as_string;

    public Coordinates3d(int x, int y, int z) {
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
            Coordinates3d tmp = (Coordinates3d)obj;
            return tmp.toString().equals(this.toString());
        }
    }
}
