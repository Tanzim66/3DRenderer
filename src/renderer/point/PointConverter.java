package renderer.point;

import java.awt.Point;
import renderer.Display;

public class PointConverter {

    public static double scale = 1;

    public static final double zoomFactor = 1.2;

    public static void zoomIn() {
        scale *= zoomFactor;
    }

    public static void zoomOut() {
        scale /= zoomFactor;
    }

    public static Point convertPoint(MyPoint point3D) {
        double x3d = point3D.y * scale;
        double y3d = point3D.z * scale;
        double depth = point3D.x * scale;

        double[] newVal = scale(x3d, y3d, depth);

        int x2d = (int) (Display.WIDTH / 2 + newVal[0]); // positioning on screen where (0,0) is top
                                                         // left
        int y2d = (int) (Display.LENGTH / 2 - newVal[1]); // minus because y increases as we go down

        Point point2d = new Point(x2d, y2d);
        return point2d;
    }

    private static double[] scale(double x3d, double y3d, double depth) {
        double distance = Math.sqrt(Math.pow(x3d, 2) + Math.pow(y3d, 2));
        double theta = Math.atan2(y3d, x3d);
        double depth2 = 15 - depth;
        double localScale = Math.abs(1400 / (depth2 + 1400));
        distance *= localScale;
        double[] newVal = new double[2];
        newVal[0] = Math.cos(theta) * distance;
        newVal[1] = Math.sin(theta) * distance;
        return newVal;
    }

    public static void rotateAxisX(MyPoint p, boolean CW, double degrees) {
        double radius = Math.sqrt(Math.pow(p.y, 2) + Math.pow(p.z, 2));
        double theta = Math.atan2(p.z, p.y);
        theta += 2 * Math.PI * degrees * (CW ? 1 : -1) / 360;
        p.y = radius * Math.cos(theta);
        p.z = radius * Math.sin(theta);
    }

    public static void rotateAxisY(MyPoint p, boolean CW, double degrees) {
        double radius = Math.sqrt(Math.pow(p.x, 2) + Math.pow(p.z, 2));
        double theta = Math.atan2(p.z, p.x);
        theta += 2 * Math.PI * degrees * (CW ? 1 : -1) / 360;
        p.x = radius * Math.cos(theta);
        p.z = radius * Math.sin(theta);
    }

    public static void rotateAxisZ(MyPoint p, boolean CW, double degrees) {
        double radius = Math.sqrt(Math.pow(p.y, 2) + Math.pow(p.x, 2));
        double theta = Math.atan2(p.y, p.x);
        theta += 2 * Math.PI * degrees * (CW ? 1 : -1) / 360;
        p.x = radius * Math.cos(theta);
        p.y = radius * Math.sin(theta);
    }

}
