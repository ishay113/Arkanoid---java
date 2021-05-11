package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import geomatry.Rectangle;

import java.awt.Color;
import java.awt.Image;


/**
 * The class creates background.
 */
public class BackGround implements Sprite {

    static final int DEFAULT_WIDTH = 750;
    static final int DEFAULT_HEIGHT = 575;


    private Rectangle rectangle;
    private java.awt.Color color;
    private Sprite drawings;
    private Image image;

    /**
     * constructor with rectangle and color.
     *
     * @param rectangle is the rectangle of the background.
     * @param color     is the color of the background.
     */
    public BackGround(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
    }

    /**
     * constructor with rectangle, color and sprite.
     *
     * @param rectangle is the rectangle.
     * @param color     is the color.
     * @param drawings  is the sprite drawing.
     */
    public BackGround(Rectangle rectangle, Color color, Sprite drawings) {
        this.rectangle = rectangle;
        this.color = color;
        this.drawings = drawings;
    }

    /**
     * constructor with defaults values and color.
     *
     * @param color is the color.
     */
    public BackGround(Color color) {
        this.rectangle = new Rectangle(25, 25, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.color = color;
    }

    /**
     * constructor with defaults values and color.
     *
     * @param image is image background.
     */
    public BackGround(Image image) {
        this.rectangle = new Rectangle(25, 25, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        this.image = image;
    }

    @Override
    public void drawOn(DrawSurface d) {
        if (this.image != null) {
            drawOnImage(d);
            return;
        }
        d.setColor(color);
        int x = (int) rectangle.getUpperLeft().getX();
        int y = (int) rectangle.getUpperLeft().getY();
        int width = (int) rectangle.getWidth();
        int height = (int) rectangle.getHeight();
        d.fillRectangle(x, y, width, height);
        d.setColor(Color.black);
        d.drawRectangle(x, y, width, height);
        if (this.drawings != null) {
            drawings.drawOn(d);
        }
    }

    /**
     * draw the backGround with the image.
     *
     * @param d is the drawSurface.
     */
    public void drawOnImage(DrawSurface d) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        d.drawImage(x, y, image);
    }

    @Override
    public void timePassed() {
    }

    /**
     * add the background to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }
}
