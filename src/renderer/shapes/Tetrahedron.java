package renderer.shapes;

import java.awt.*;

public class Tetrahedron {
    private MyPolygon[] polygons;
    private Color color;

    public Tetrahedron(Color color, boolean decayingColor,  MyPolygon... polygons) {
        this.color = color;
        this.polygons = polygons;
        if(decayingColor) {
        	this.setDifferentPolygonColor();
        }
        else {
        	this.setPolygonColor();
        }
        this.sortPolygons();
    }

    public Tetrahedron(MyPolygon... polygons) {
        this.color = Color.WHITE;
        this.polygons = polygons;
        this.sortPolygons();
    }

    public void render(Graphics g) {
        for (MyPolygon poly : this.polygons) {
            poly.render(g);
        }
    }

    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for (MyPolygon p : this.polygons) {
            p.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();
    }
    
    public MyPolygon[] getPolygon(){
    	return this.polygons;
    }

    private void sortPolygons() {
        MyPolygon.sortPolygons(polygons);
    }

    private void setPolygonColor() {
        for (MyPolygon poly : this.polygons) {
            poly.setColor(this.color);
        }
    }
    
    private void setDifferentPolygonColor() {
    	double decayFactor = 0.95;
    	for (MyPolygon poly : this.polygons) {
            poly.setColor(this.color);
            int r = (int)(this.color.getRed() * decayFactor);
            int g = (int)(this.color.getGreen() * decayFactor);
            int b = (int)(this.color.getBlue() * decayFactor);
            this.color = new Color(r,g,b);
        }
    }
}
