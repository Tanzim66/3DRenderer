package renderer.entity;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import renderer.shapes.MyPolygon;
import renderer.shapes.Tetrahedron;

public class Entity implements IEntity {
    private List<Tetrahedron> tetra;
    private MyPolygon[] polygons;

    public Entity(List<Tetrahedron> tetra) {
        this.tetra = tetra;
        List<MyPolygon> tempList= new ArrayList<MyPolygon>();
        for(Tetrahedron t:tetra) {
        	tempList.addAll(Arrays.asList(t.getPolygon()));
        }
        this.polygons = new MyPolygon[tempList.size()];
        this.polygons = tempList.toArray(this.polygons);
        this.sortPolygons();
    }

    @Override
    public void render(Graphics g) {

        for (MyPolygon p : this.polygons) {
            p.render(g);
        }

    }

    @Override
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {

        for (Tetrahedron t : this.tetra) {
            t.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
        this.sortPolygons();

    }
    
    private void sortPolygons() {
    	MyPolygon.sortPolygons(this.polygons);
    }

}
