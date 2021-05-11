// ID: 316609411

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import geomatry.Point;
import sprite.Ball;
import sprite.Velocity;

import java.awt.Color;
import java.util.Random;

/**
 * The class creates multiple bouncing balls animation.
 * The size of the balls get as an arguments from the command line.
 * The bigger the size, the slower the speed
 */
public class MultipleBouncingBallsAnimation {

    // CONST speed
    static final int CONST_SPEED = 200;

    /**
     * The method return new random line in range (1,1) - (400, 300).
     *
     * @param r is the radius of the ball.
     * @return new random point in range (1,1) - (400, 300).
     */
    private Point getRandomCenter(int r) {
        Random rand = new Random(); // create a random-number generator
        double x1 = rand.nextInt(400 - r) + r + 1;
        double y1 = rand.nextInt(300 - r) + r + 1;
        return new Point(x1, y1);
    }

    /**
     * The method draws a multiple bouncing balls animation.
     *
     * @param array is an array of string we received from the main, that presents the ball's radius.
     * @param size  the size of the array.
     */
    public void drawAnimationMultipleBalls(String[] array, int size) {
        // Create a window with the title "MultipleBouncingBallsAnimation"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("MultipleBouncingBallsAnimation", 400, 300);
        Sleeper sleeper = new Sleeper();
        // create array to store the balls
        Ball[] balls = new Ball[size];
        // loop to initialize the balls with random center point and the radius we get as an argument
        for (int i = 0; i < size; i++) {
            int r = Integer.parseInt(array[i]);
            balls[i] = new Ball(getRandomCenter(r), r, Color.ORANGE);
            // set velocity for the ball, so the bigger the size, the slower the speed
            double speed = (r >= 50) ? CONST_SPEED / 50 : CONST_SPEED / r;
            Velocity v = Velocity.fromAngleAndSpeed(45, speed);
            balls[i].setVelocity(v);
        }
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < size; i++) {
                balls[i].moveOneStep(new Point(0, 0), new Point(400, 300));
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * The main we run the program.
     *
     * @param args are the arguments we get in the command line, which are the radius of the balls.
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation example = new MultipleBouncingBallsAnimation();
        example.drawAnimationMultipleBalls(args, args.length);
    }
}
