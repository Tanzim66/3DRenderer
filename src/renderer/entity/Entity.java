package renderer.entity;

import java.awt.Graphics;
import java.util.List;
import renderer.shapes.Tetrahedron;

public class Entity implements IEntity {
    private List<Tetrahedron> tetra;

    public Entity(List<Tetrahedron> tetra) {
        this.tetra = tetra;
    }

    @Override
    public void render(Graphics g) {

        for (Tetrahedron t : this.tetra) {
            t.render(g);
        }

    }

    @Override
    public void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {

        for (Tetrahedron t : this.tetra) {
            t.rotate(CW, xDegrees, yDegrees, zDegrees);
        }

    }

}
