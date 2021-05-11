package animations;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * animations.Animation Runner runs the animation.
 */
public class AnimationRunner {
    private GUI gui;
    private int framesPerSecond;
    private Sleeper sleeper;

    /**
     * constructor with string, int width and int height.
     *
     * @param title  string, the title of the gui.
     * @param width  the width for the gui.
     * @param height the height for the gui.
     */
    public AnimationRunner(String title, int width, int height) {
        this.gui = new GUI(title, width, height);
        this.sleeper = new Sleeper();
        this.framesPerSecond = 60;
    }

    /**
     * constructor with GUI gui.
     *
     * @param gui is the GUI to display the animation.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.framesPerSecond = 60;
    }

    /**
     * run the animation.
     *
     * @param animation is the animation we run.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;
        ;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);

            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }

    /**
     * run the animation, with specific sleep time.
     *
     * @param animation    is the animation we run.
     * @param numOfSeconds is the number of seconds the animation display.
     * @param countFrom    is the number we start to count from.
     */
    public void run(Animation animation, double numOfSeconds, int countFrom) {
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();

            animation.doOneFrame(d);
            gui.show(d);
            this.sleeper.sleepFor((long) (numOfSeconds * 1000 / countFrom));
        }
    }
}
