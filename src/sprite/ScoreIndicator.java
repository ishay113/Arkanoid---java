package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import geomatry.Point;
import geomatry.Rectangle;

import java.awt.Color;

/**
 * scoreIndicator display the scores at the top of the screen.
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle rectangle;
    private Color color;

    /**
     * constructor with counter, width and height (for the rectangle).
     *
     * @param score  is the score counter of the game.
     * @param width  is the width of the rectangle.
     * @param height is the height of the rectangle.
     */
    public ScoreIndicator(Counter score, double width, double height) {
        this.score = score;
        this.rectangle = new Rectangle(new Point(0, 0), width, height);
        this.color = Color.white;
    }

    /**
     * draw the scoreIndicator.
     *
     * @param d is the drawSurface we draw on it.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(color);
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.black);
        d.drawRectangle(x, y, width, height);
        d.drawText(350, 15, "Score: " + score.getValue(), 15);
    }

    /**
     * notify that time has passed.
     */
    public void timePassed() {

    }

    /**
     * add the scoreIndicator to the game.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
