// ID: 316609411

import biuoop.GUI;
import biuoop.DrawSurface;
import geomatry.Line;
import geomatry.Point;

import java.util.Random;
import java.awt.Color;

/**
 * The class creates a generate random pictures with 10 random lines, and mark the middle point in blue
 * and the intersection point in red.
 */
public class AbstractArtDrawing {
    /**
     * The method draws 10 lines in black. The middle point in each line is indicated in blue,
     * while the intersection points between the lines are indicated in red
     * and the points are filled circles with a radius of 3.
     */
    public void drawRandomLines() {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "Random Lines Task"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Random Lines Task", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        // create an array of lines
        Line[] lines = new Line[10];
        int r = 3;
        for (int i = 0; i < 10; ++i) {
            Line line = generateRandomLine();
            drawLine(line, d);
            d.setColor(Color.BLUE);
            d.fillCircle((int) line.middle().getX(), (int) line.middle().getY(), r);
            lines[i] = line;
        }
        for (int i = 0; i < 10; i++) {
            for (int j = i + 1; j < lines.length; j++) {
                if (lines[i].isIntersecting(lines[j])) {
                    drawRedCircle(lines[i].intersectionWith(lines[j]), r, d);
                }
            }
        }
        gui.show(d);
    }

    /**
     * The method return new random line in range (1,1) - (400, 300).
     *
     * @return new random line in range (1,1) - (400, 300)
     */
    private Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
        return new Line(x1, y1, x2, y2);
    }

    /**
     * The method draw a black line.
     *
     * @param d is the surface we draw the line
     * @param l is the line we draw on the surface
     */
    private void drawLine(Line l, DrawSurface d) {
        int x1 = (int) l.start().getX();
        int y1 = (int) l.start().getY();
        int x2 = (int) l.end().getX();
        int y2 = (int) l.end().getY();
        d.setColor(Color.BLACK);
        d.drawLine(x1, y1, x2, y2);
    }

    /**
     * The method draw a red circle.
     *
     * @param p is the center point of the circle
     * @param r is the radius of the circle
     * @param d is the surface we draw on it.
     */
    private void drawRedCircle(Point p, int r, DrawSurface d) {
        int x = (int) p.getX();
        int y = (int) p.getY();
        d.setColor(Color.RED);
        d.fillCircle(x, y, r);
    }

    /**
     * The main we run the program.
     *
     * @param args are the arguments we get from the command line
     */
    public static void main(String[] args) {
        AbstractArtDrawing example = new AbstractArtDrawing();
        example.drawRandomLines();
    }
}