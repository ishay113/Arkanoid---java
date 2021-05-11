// ID: 316609411

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geomatry.Point;
import sprite.Ball;


/**
 * The class creates a bouncing ball animation.
 * The main method gets 4 integers from the command line and runs the drawAnimation method accordingly
 */
public class BouncingBallAnimation {
    /**
     * The method draws bouncing ball animation.
     *
     * @param start is the point the method gets and put it as the center of the ball
     * @param dx    is the change in position on the 'x' axe.
     * @param dy    is the change in position on the 'y' axe.
     */
    static void drawAnimation(Point start, double dx, double dy) {
        // the width of the window
        int width = 200;
        // the height of the window
        int height = 200;
        GUI gui = new GUI("title", width, height);
        Sleeper sleeper = new Sleeper();
        // create the new ball with the parameters we get
        Ball ball = new Ball(start.getX(), start.getY(), 30, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        // play the animation
        while (true) {
            ball.moveOneStep(new Point(0, 0), new Point(200, 200));
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * The main we run the program.
     *
     * @param args are the arguments we get from the command line, which will be center and the velocity of the ball.
     */
    public static void main(String[] args) {
        if (args.length != 4) {
            System.out.println("error");
            return;
        }
        int x = Integer.parseInt(args[0]);
        int y = Integer.parseInt(args[1]);
        int dx = Integer.parseInt(args[2]);
        int dy = Integer.parseInt(args[3]);
        BouncingBallAnimation ballAnimation = new BouncingBallAnimation();
        ballAnimation.drawAnimation(new Point(x, y), dx, dy);
    }
}
