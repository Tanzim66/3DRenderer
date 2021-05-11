package renderer.shapes;

import java.awt.*;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
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

    public double getAverageX() {
        double sum = 0;
        for (MyPoint p : this.points) {
            sum += p.x;
        }
        return sum / this.points.length;
    }

    public static MyPolygon[] sortPolygons(MyPolygon[] polygons) {
        List<MyPolygon> polygonsList = new ArrayList<MyPolygon>();
        for (MyPolygon poly : polygons) {
            polygonsList.add(poly);
        }
        Collections.sort(polygonsList, new Comparator<MyPolygon>() {
            @Override
            public int compare(MyPolygon p1, MyPolygon p2) {
            	double p1AvgX = p1.getAverageX();
            	double p2AvgX = p2.getAverageX();
            	double diff = p2AvgX - p1AvgX;
            	if(diff == 0)
            		return 0;
            	
                return diff < 0 ? 1 : -1;
            }
        });

        for (int i = 0; i < polygons.length; i++) {
            polygons[i] = polygonsList.get(i);
        }
        return polygons;
    }
}
