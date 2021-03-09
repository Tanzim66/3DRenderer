package renderer.point;

import java.awt.Point;
import renderer.Display;

public class PointConverter {

    public static Point convertPoint(MyPoint point3D) {
        int x2d = (int) (Display.WIDTH / 2 + point3D.y); // positioning on screen where (0,0) is top
                                                         // left
        int y2d = (int) (Display.HEIGHT / 2 - point3D.z); // minus because y increases as we go down

        Point point2d = new Point(x2d, y2d);
        return point2d;
    }

}
