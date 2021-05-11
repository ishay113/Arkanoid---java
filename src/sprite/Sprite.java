package sprite;

import biuoop.DrawSurface;

/**
 * The sprite.Sprite interface is for all objects that should drawn on the animation.
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d is the drawSurface we draw on it.
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
