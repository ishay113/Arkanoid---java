package levels;

import biuoop.DrawSurface;
import sprite.Sprite;

import java.awt.Color;
import java.awt.Polygon;

/**
 * levels.Level Drawing - cloud and thunder.
 */
public class LevelDrawingsCloud implements Sprite {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.white);
        for (int i = 0; i < 10; i++) {
            d.drawLine(100 + 10 * i, 450, 80 + 10 * i, HEIGHT);
        }
        Color color = new Color(200, 200, 200);
        d.setColor(Color.lightGray);
        d.fillCircle(100, 430, 30);
        d.setColor(color);
        d.fillCircle(140, 420, 30);
        d.setColor(Color.lightGray);
        d.fillCircle(180, 430, 30);
        d.setColor(color);
        d.fillCircle(120, 400, 30);
        color = new Color(210, 210, 210);
        d.setColor(color);
        d.fillCircle(160, 400, 30);
        int[] xPoints = {600, 610, 590, 580};
        int[] yPoints = {300, 300, 400, 400};
        d.setColor(Color.yellow);
        d.fillPolygon(new Polygon(xPoints, yPoints, 4));
        int[] xPoints1 = {590, 600, 580};
        int[] yPoints1 = {390, 390, 500};
        d.fillPolygon(new Polygon(xPoints1, yPoints1, 3));
    }

    @Override
    public void timePassed() {

    }
}
