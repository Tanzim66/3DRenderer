package renderer.entity;

import java.util.ArrayList;
import java.util.List;
//import renderer.entity.builder.BasicEntityBuilder;
import renderer.entity.builder.ComplexEntityBuilder;
import renderer.input.ClickType;
import renderer.input.Mouse;
import renderer.point.PointConverter;
import java.awt.*;

public class EntityManager {
    private List<IEntity> entities;

    private int initialX;
    private int initialY;
    private double mouseSensitivity = 2.5;

    public EntityManager() {
        this.entities = new ArrayList<IEntity>();
    }

    public void init() {
        //this.entities.add(BasicEntityBuilder.createDiamond(Color.BLUE, 100, 0, 0, 0));
    	this.entities.add(ComplexEntityBuilder.createRubiksCube(100, 0, 0, 0));
    }

    public void update(Mouse mouse) {
        int x = mouse.getX();
        int y = mouse.getY();
        if (mouse.getB() == ClickType.leftClick) {
            int xDif = x - initialX;
            int yDif = y - initialY;
            this.rotate(true, 0, -yDif / mouseSensitivity, xDif / mouseSensitivity);
        } else if (mouse.getB() == ClickType.rightClick) {
            int xDif = x - initialX;
            this.rotate(true, -xDif / mouseSensitivity, 0, 0);
        }

        if (mouse.isScrollingUp()) {
            PointConverter.zoomIn();
        } else if (mouse.isScrollingDown()) {
            PointConverter.zoomOut();
        }

        mouse.resetScroll();

        initialX = x;
        initialY = y;
    }

    private void rotate(boolean CW, double xDegrees, double yDegrees, double zDegrees) {
        for (IEntity e : this.entities) {
            e.rotate(CW, xDegrees, yDegrees, zDegrees);
        }
    }

    public void render(Graphics g) {
        for (IEntity e : this.entities) {
            e.render(g);
        }
    }
}
