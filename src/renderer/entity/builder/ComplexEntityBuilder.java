package renderer.entity.builder;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import renderer.shapes.MyPolygon;
import renderer.shapes.Tetrahedron;
import renderer.entity.Entity;
import renderer.entity.IEntity;
import renderer.point.MyPoint;

public class ComplexEntityBuilder {
	public static IEntity createRubiksCube(double size, double centerX, double centerY, double centerZ){
		List<Tetrahedron> tetras = new ArrayList<Tetrahedron>();
		
		double cubeSpacing = 10;
		
		for(int i=-1; i<2; i++) {
			double cubeCenterX = i*(size + cubeSpacing) + centerX;
			for(int j=-1; j<2; j++) {
				double cubeCenterY = j*(size + cubeSpacing) + centerX;
				for(int k=-1; k<2; k++) {
					if(i == 0 && j == 0 && k==0)continue;
					double cubeCenterZ = k*(size + cubeSpacing) + centerX;
					MyPoint p1 = new MyPoint(cubeCenterX - size/2, cubeCenterY - size/2, cubeCenterZ - size/2);
					MyPoint p2 = new MyPoint(cubeCenterX - size/2, cubeCenterY - size/2, cubeCenterZ + size/2);
					MyPoint p3 = new MyPoint(cubeCenterX - size/2, cubeCenterY + size/2, cubeCenterZ - size/2);
					MyPoint p4 = new MyPoint(cubeCenterX - size/2, cubeCenterY + size/2, cubeCenterZ + size/2);
					MyPoint p5 = new MyPoint(cubeCenterX + size/2, cubeCenterY - size/2, cubeCenterZ - size/2);
					MyPoint p6 = new MyPoint(cubeCenterX + size/2, cubeCenterY - size/2, cubeCenterZ + size/2);
					MyPoint p7 = new MyPoint(cubeCenterX + size/2, cubeCenterY + size/2, cubeCenterZ - size/2);
					MyPoint p8 = new MyPoint(cubeCenterX + size/2, cubeCenterY + size/2, cubeCenterZ + size/2);
					
					MyPolygon poly1 = new MyPolygon(Color.RED, p5, p6, p8, p7);
					MyPolygon poly2 = new MyPolygon(Color.WHITE, p2, p4, p8, p6);
					MyPolygon poly3 = new MyPolygon(Color.BLUE, p3, p4, p8, p7);
					MyPolygon poly4 = new MyPolygon(Color.GREEN, p1, p2, p6, p5);
					MyPolygon poly5 = new MyPolygon(Color.ORANGE, p1, p3, p7, p5);
					MyPolygon poly6 = new MyPolygon(Color.PINK, p1, p2, p4, p3);
					
					Tetrahedron tetra = new Tetrahedron(poly1, poly2, poly3, poly4, poly5, poly6);
					tetras.add(tetra);
				}
			}
		}
		return new Entity(tetras);
	}
}
