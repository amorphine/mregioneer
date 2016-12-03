import mregioneer.mcaliases.Vector;
import mregioneer.utils.Coordinates3d;
import org.junit.Test;

import java.util.List;

public class TestVector {
    @Test
    public void testAdd() {
        Coordinates3d cords1 = new Coordinates3d(3, 3, 3);
        Coordinates3d cords2 = new Coordinates3d(3, 4, 4);

        Vector vector = new Vector(cords1, cords2);

        List arr = vector.getCoordinatesArray();
    }
}
