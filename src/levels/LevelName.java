package levels;

import biuoop.DrawSurface;
import game.GameLevel;
import sprite.Sprite;

/**
 * The level name presented as a sprite.
 */
public class LevelName implements Sprite {
    private String string;
    private int x;
    private int y;
    private int size;

    /**
     * constructor with string and location and size.
     *
     * @param string is the level name.
     * @param locate is the x value
     * @param y      is the y value.
     * @param size   is the size.
     */
    public LevelName(String string, int locate, int y, int size) {
        this.x = locate;
        this.size = size;
        this.string = string;
        this.y = y;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.drawText((3 * x) / 4, y, "Level Name: " + string, size);

    }

    @Override
    public void timePassed() {

    }

    /**
     * add the levelName to the game.
     *
     * @param gameLevel is the game level.
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }
}
