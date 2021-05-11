package levels;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;

/**
 * level drawing - Target.
 */
public class LevelDrawingsTarget implements Sprite {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int WIDTH_OF_BORDER = 25;
    static final int WIDTH_OF_BLOCK = 30;
    static final int HEIGHT_OF_BLOCK = 30;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.blue);
        int x = (WIDTH - 2 * WIDTH_OF_BORDER) / 2 + WIDTH_OF_BLOCK / 2;
        int y = HEIGHT / 4 + HEIGHT_OF_BLOCK / 2;
        int r = 75;
        for (int i = 0; i < 3; i++) {
            d.drawCircle(x, y, r + i * 30);
        }
        d.drawLine(x, y, x, 20);
        d.drawLine(x, y, x, y + 2 * r);
        d.drawLine(x - WIDTH_OF_BLOCK / 2, y, x - 2 * r, y);
        d.drawLine(x + WIDTH_OF_BLOCK / 2, y, x + 2 * r, y);

    }

    @Override
    public void timePassed() {

    }
}
