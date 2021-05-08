package renderer.shapes;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import renderer.point.MyPoint;
import renderer.point.PointConverter;

public class MyPolygon {
    private MyPoint[] points;
    private Color color;

    public MyPolygon(Color color, MyPoint... points) {
        this.color = color;
        this.points = new MyPoint[points.length];
        for (int i = 0; i < points.length; i++) {
            MyPoint p = points[i];
            this.points[i] = new MyPoint(p.x, p.y, p.z);
        }
        // Copies all the contnets of the points to the array points
    }

    public MyPolygon(MyPoint... points) {
        this.color = Color.WHITE;
        this.points = new MyPoint[points.length];
        for (int i = 0; i < points.length; i++) {
            MyPoint p = points[i];
            this.points[i] = new MyPoint(p.x, p.y, p.z);
        }
        // Copies all the contnets of the points to the array points
    }

    public void render(Graphics g) {
        Polygon poly = new Polygon();
        for (int i = 0; i < this.points.length; i++) {
            Point p1 = PointConverter.convertPoint(this.points[i]);
            poly.addPoint(p1.x, p1.y);
        }
        g.setColor(color);
        g.fillPolygon(poly);
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for (MyPoint p : points) {
            PointConverter.rotateAxisX(p, CW, xDegrees);
            PointConverter.rotateAxisY(p, CW, yDegrees);
            PointConverter.rotateAxisZ(p, CW, zDegrees);
        }
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
