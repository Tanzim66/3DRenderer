package renderer;

import java.awt.Color;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;
import renderer.entity.EntityManager;
import renderer.input.Mouse;

public class Display extends Canvas implements Runnable {
    // inherits all the methods from Canvas and we implement Runnable
    // Canvas is from java.awt.Canvas library
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private Thread thread;
    // These are the variables needed for the frame
    private JFrame frame;
    // JFrame from java.swing.JFrame library
    private static String title = "3DRenderer";
    public static final int WIDTH = 800;
    public static final int LENGTH = 600;
    private static boolean running = false;

    private EntityManager eMan;

    private Mouse mouse;

    public Display() {
        this.frame = new JFrame(); // initialize JFrame
        Dimension size = new Dimension(Display.WIDTH, Display.LENGTH);
        this.setPreferredSize(size); // inherited from the Canvas class
        this.mouse = new Mouse();
        this.addMouseListener(this.mouse);
        this.addMouseMotionListener(this.mouse);
        this.addMouseWheelListener(this.mouse);
        this.eMan = new EntityManager();
    }

    public static void main(String[] args) {
        Display display = new Display(); // Create an instance of display
        display.frame.setTitle(title); // sets the title of the canvas to static vatiable title
        display.frame.add(display);
        display.frame.pack();
        display.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // when we exit out of the
                                                                      // program we want the
                                                                      // program to stop running
        display.frame.setLocationRelativeTo(null); // centers display on our screen
        display.frame.setResizable(false); // makes the window not resizable
        display.frame.setVisible(true); // makes the JFrame visible

        display.start();
    }

    public synchronized void start() {
        running = true;
        this.thread = new Thread(this, "Display");
        this.thread.start(); // keeps the display in a thread where the JFrame is visible
    }

    public synchronized void stop() {
        running = false;
        try {
            this.thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() { // This is what runs when the thread is started
        long lastTime = System.nanoTime(); // used to record what time it was for the previous
                                           // iteration of the while
                                           // loop
        long timer = System.currentTimeMillis(); // used to record current time
        final double ns = 1000000000.0 / 60; // number of updates per second (60 updates per second)
        double delta = 0; // percent progress towards the next update
        int frames = 0;

        this.eMan.init();

        while (running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns; // stores how close we are to the next update
            lastTime = now;
            while (delta >= 1) { // we use while loop isntead of if in the case that one update was
                                 // missed and
                                 // delta goes over 1
                update();
                delta--;
                // we want a set number of times we want to update
                render();
                frames++;
            }

            if (System.currentTimeMillis() - timer > 1000) { // if one second has passed
                timer += 1000; // update the timer
                this.frame.setTitle(title + " | " + frames + " fps"); // display the fps
                frames = 0;
            }
        }

        stop();

    }

    private void render() {

        BufferStrategy bs = this.getBufferStrategy(); // organizes memory for media output
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }

        // set the background to a color
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, WIDTH * 2, LENGTH * 2);

        this.eMan.render(g);

        g.dispose();
        bs.show();
        //

    }

    private void update() {
        this.eMan.update(this.mouse);
    }

}
