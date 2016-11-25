package ru.wpstuio.amorphine.utils;

/**
 * The class has been created to use as a key for hash maps containing chunks, regions and so on.
 * All new inctanses of the class with simular params returns the same hash code
 */
public class Coordinates2d {
    private final int x;
    private final int z;

    private final String coordinates_as_string;

    public Coordinates2d(int x, int z) {
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
}
