package levels;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;

/**
 * level drawing - Sun.
 */
public class LevelDrawingsSun implements Sprite {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.orange);
        for (int i = 0; i < 100; i++) {
            d.drawLine(WIDTH / 6, HEIGHT / 6, 25 + 7 * i, HEIGHT / 3);
        }
        Color color = new Color(250, 225, 150);
        d.setColor(color);
        d.fillCircle(WIDTH / 6, HEIGHT / 6, 60);
        color = new Color(250, 200, 0);
        d.setColor(color);
        d.fillCircle(WIDTH / 6, HEIGHT / 6, 50);
        color = new Color(250, 225, 30);
        d.setColor(color);
        d.fillCircle(WIDTH / 6, HEIGHT / 6, 40);

    }

    @Override
    public void timePassed() {

    }
}
