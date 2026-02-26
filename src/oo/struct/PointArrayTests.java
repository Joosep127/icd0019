package oo.struct;

import org.junit.jupiter.api.Test;

public class PointArrayTests {

    @Test
    public void coordinatesAsArrays() {

        int[][] trianglePoints = {{1, 1, 0}, {5, 1, 0}, {3, 7, 1}};

        for (int[] each : trianglePoints) {
            System.out.println(each[2]);
        }

    }

    @Test
    public void coordinatesAsObjects() {

        Point3D[] trianglePoints = {new Point3D(1,1,0), new Point3D(5,1,0), new Point3D(3,7,1)};

        for (Point3D point : trianglePoints) {
            System.out.println(point.z);
        }
    }


}
