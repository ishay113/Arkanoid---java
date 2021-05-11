package levels;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;

/**
 * levels.Level Drawing - building.
 */
public class LevelDrawingsBuilding implements Sprite {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.darkGray);
        d.fillRectangle(WIDTH / 8, HEIGHT / 4, 16, 3 * HEIGHT / 4);
        d.setColor(Color.pink);
        d.fillCircle(WIDTH / 8 + 8, HEIGHT / 4, 11);
        d.setColor(Color.red);
        d.fillCircle(WIDTH / 8 + 8, HEIGHT / 4, 7);
        d.setColor(Color.white);
        d.fillCircle(WIDTH / 8 + 8, HEIGHT / 4, 3);
        d.setColor(Color.GRAY);
        d.fillRectangle(WIDTH / 8 - 16, 2 * HEIGHT / 3, 48, 2 * HEIGHT / 3);
        d.setColor(Color.BLACK);
        d.fillRectangle(WIDTH / 8 - 16 - 48, 3 * HEIGHT / 4, 48 * 3, HEIGHT / 3);
        d.setColor(Color.WHITE);
        d.fillRectangle(WIDTH / 8 - 48, 3 * HEIGHT / 4 + 16, 48 * 3 - 32, HEIGHT / 3 - 16);
        d.setColor(Color.BLACK);
        d.fillRectangle(WIDTH / 8 - 48, 3 * HEIGHT / 4 + 30, 48 * 3 - 32, 10);
        d.fillRectangle(WIDTH / 8 - 48, 3 * HEIGHT / 4 + 60, 48 * 3 - 32, 10);
        d.fillRectangle(WIDTH / 8 - 48, 3 * HEIGHT / 4 + 90, 48 * 3 - 32, 10);
        d.fillRectangle(WIDTH / 8 - 48, 3 * HEIGHT / 4 + 120, 48 * 3 - 32, 10);
        d.fillRectangle(WIDTH / 8 - 36, 3 * HEIGHT / 4 + 16, 10, 200);
        d.fillRectangle(WIDTH / 8 - 12, 3 * HEIGHT / 4 + 16, 10, 200);
        d.fillRectangle(WIDTH / 8 + 12, 3 * HEIGHT / 4 + 16, 10, 200);
        d.fillRectangle(WIDTH / 8 + 36, 3 * HEIGHT / 4 + 16, 10, 200);

    }

    @Override
    public void timePassed() {

    }
}
