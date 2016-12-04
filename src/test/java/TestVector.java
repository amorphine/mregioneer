import mregioneer.mcaliases.Vector;
import mregioneer.utils.Point3d;
import org.junit.Test;

import java.util.List;

public class TestVector {
    @Test
    public void testAdd() {
        Point3d cords1 = new Point3d(3, 3, 3);
        Point3d cords2 = new Point3d(3, 4, 4);

        Vector vector = new Vector(cords1, cords2);

        List arr = vector.getCoordinatesArray();
    }
}
