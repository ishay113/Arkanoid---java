package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geomatry.Rectangle;
import levels.LevelDrawingsSun;
import levels.LevelDrawingsTarget;
import sprite.BackGround;

import java.awt.*;

/**
 * The end screen of the game.
 */
public class EndScreen implements Animation {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    private KeyboardSensor keyboard;
    private boolean stop;
    private int score;
    private boolean isWin;
    private BackGround backGround1;
    private BackGround backGround2;


    /**
     * constructor with keyboardSensor, boolean is win or lose, score.
     *
     * @param k     the keyboardSensor.
     * @param b     boolean argument - is win or lose.
     * @param score the final score.
     */
    public EndScreen(KeyboardSensor k, boolean b, int score) {
        this.keyboard = k;
        this.stop = false;
        this.isWin = b;
        this.score = score;
        this.backGround1 = new BackGround(new Rectangle(0, 0, WIDTH, HEIGHT), Color.black, new LevelDrawingsTarget());
        this.backGround2 = backGround1;

    }

    /**
     * do one frame.
     *
     * @param d is the drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        if (isWin) {
            backGround1.drawOn(d);
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 32);
        } else {
            backGround2.drawOn(d);
            d.setColor(Color.red);
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 32);


        }
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * this method tells us if the animation should stop or not.
     *
     * @return if the animation should stop or not.
     */
    public boolean shouldStop() {
        return false;
    }
}
