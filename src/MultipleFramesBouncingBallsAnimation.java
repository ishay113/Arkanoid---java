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
 * The class creates multiple frames bouncing balls animation
 * The class creates two frames: one is a grey rectangle from (50,50) to (500,500),
 * and the other is a yellow rectangle from (450,450) to (600,600).
 * The first half of the balls bounce inside the first frame,
 * and the second half of the balls bounce inside the second frame.
 */
public class MultipleFramesBouncingBallsAnimation {

    // CONST speed
    static final int CONST_SPEED = 200;

    /**
     * The method draws a multiple bouncing balls animation.
     *
     * @param array is an array of string we received from the main, that presents the radius of the ball.
     * @param size  the size of the array.
     */
    public void drawAnimationMultipleFrames(String[] array, int size) {
        Random rand = new Random(); // create a random-number generator
        // create array to store the balls
        Ball[] balls = new Ball[size];
        // loop to initialize balls with random center point and the radius we get as an argument
        for (int i = 0; i < size; i++) {
            int r = Integer.parseInt(array[i]);
            // the first half of the balls
            if (i < size / 2 + (size % 2)) {
                double x1 = rand.nextInt(400 - r) + r + 50;
                double y1 = rand.nextInt(450 - r) + r + 50;
                // create random color
                java.awt.Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                balls[i] = new Ball(x1, y1, r, color);
                // the second half of the balls
            } else {
                double x1 = rand.nextInt(600 - r) + r + 450;
                double y1 = rand.nextInt(600 - r) + r + 450;
                java.awt.Color color = new Color(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
                balls[i] = new Ball(x1, y1, r, color);
            }
            // set velocity for the ball, so the bigger the size, the smaller the speed
            double speed = (r >= 50) ? CONST_SPEED / 50 : CONST_SPEED / r;
            Velocity v = Velocity.fromAngleAndSpeed(45, speed);
            balls[i].setVelocity(v);
        }
        playAnimation(balls, size);
    }

    /**
     * The method plays the multiple frames bouncing balls animation.
     *
     * @param balls is an array of balls for the multiple frames Bouncing balls animation.
     * @param size  the size of the balls array.
     */
    private void playAnimation(Ball[] balls, int size) {
        Random rand = new Random(); // create a random-number generator
        // Create a window with the title "MultipleBouncingBallsAnimation"
        // which is 1000 pixels wide and 600 pixels high.
        GUI gui = new GUI("MultipleFramesBouncingBallsAnimation", 1000, 600);
        Sleeper sleeper = new Sleeper();
        // play the animation
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            // loop to move one step and draw it on the surface
            for (int i = 0; i < size / 2 + (size % 2); i++) {
                balls[i].moveOneStep(new Point(50, 50), new Point(500, 500));
                balls[i].drawOn(d);
                if ((size / 2 + i + (size % 2)) < size) {
                    balls[size / 2 + i + (size % 2)].moveOneStep(new Point(450, 450), new Point(600, 600));
                    balls[size / 2 + (size % 2) + i].drawOn(d);
                }
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
        MultipleFramesBouncingBallsAnimation ex = new MultipleFramesBouncingBallsAnimation();
        ex.drawAnimationMultipleFrames(args, args.length);
    }
}
