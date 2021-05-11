package animations;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import sprite.SpriteCollection;

import java.awt.Color;

/**
 * Count down before the animation begins.
 */
public class CountdownAnimation implements Animation {
    private double numOfSeconds;
    private int countFrom;
    private SpriteCollection gameScreen;
    private Sleeper sleeper;
    private boolean stop;

    /**
     * constructor with numOfSeconds, countFrom and the game screen.
     *
     * @param numOfSeconds is the num of seconds that this animation run
     * @param countFrom    the number we count from.
     * @param gameScreen   the game screen with all the sprites objects.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.countFrom = countFrom;
        this.gameScreen = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.stop = false;
        this.sleeper = new Sleeper();
    }

    /**
     * move-on count - decrease the count.
     */
    private void moveOnCount() {
        this.countFrom--;
    }

    /**
     * do one frame - draw the count.
     *
     * @param d is the drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        this.gameScreen.drawAllOn(d);
        d.setColor(Color.red);
        d.drawText(350, 100, countFrom + "", 80);
        moveOnCount();
        if (this.countFrom == 0) {
            this.stop = true;
        }
    }

    /**
     * this method tells us if the animation should stop or not.
     *
     * @return if the animation should stop or not.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * get teh number of seconds.
     *
     * @return numOfSeconds.
     */
    public double getNumOfSeconds() {
        return numOfSeconds;
    }
}
