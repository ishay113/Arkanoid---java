package sprite;

import biuoop.DrawSurface;
import game.GameLevel;
import geomatry.Line;
import geomatry.Point;
import geomatry.Rectangle;
import listeners.HitListener;
import listeners.HitNotifier;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * The class creates a block.
 * A block is an object with rectangle and color
 * Lines have lengths, and may intersect with other lines. It can also tell if it is the same as another line segment.
 */
public class Block implements Collidable, Sprite, HitNotifier {

    private List<HitListener> hitListeners;
    private Rectangle rectangle;
    private java.awt.Color color;
    private Color stroke;
    private boolean isImage;
    private String fill;
    private Image image;

    /**
     * constructor with rectangle and color.
     *
     * @param rect  the rectangle of the block.
     * @param color is the color of the block.
     */
    public Block(Rectangle rect, Color color) {
        this.hitListeners = new ArrayList<>();
        this.rectangle = new Rectangle(rect);
        this.color = color;
        this.stroke = Color.black;
        this.isImage = false;
    }

    /**
     * constructor with block.
     *
     * @param block is the block.
     */
    public Block(Block block) {
        this.rectangle = new Rectangle(block.rectangle);
        this.color = block.color;
        this.hitListeners = new ArrayList<>();
        this.stroke = Color.black;
        this.isImage = block.isImage;

    }

    /**
     * constructor with x, y, width, height and color.
     *
     * @param x      is the x value of the upperLeft point
     * @param y      is the y value of the upperLeft point
     * @param width  is the width of the block
     * @param height is the height of the block
     * @param color  is the color of the block
     */
    public Block(double x, double y, double width, double height, Color color) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.stroke = Color.black;
        this.isImage = false;
    }

    /**
     * constructor with x, y, width, height and color.
     *
     * @param x      is the x value of the upperLeft point
     * @param y      is the y value of the upperLeft point
     * @param width  is the width of the block
     * @param height is the height of the block
     * @param color  is the color of the block
     * @param stroke is the color of the stroke
     */
    public Block(double x, double y, double width, double height, Color color, Color stroke) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<>();
        this.stroke = stroke;
        this.isImage = false;
    }

    /**
     * constructor with x, y, width, height and color.
     *
     * @param x      is the x value of the upperLeft point
     * @param y      is the y value of the upperLeft point
     * @param width  is the width of the block
     * @param height is the height of the block
     * @param stroke is the color of the stroke
     * @param image  is an image.
     */
    public Block(double x, double y, double width, double height, Color stroke, Image image) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = Color.white;
        this.hitListeners = new ArrayList<>();
        this.stroke = stroke;
        this.image = image;
        this.isImage = true;
    }

    /**
     * constructor with x, y, width, height and color.
     *
     * @param x      is the x value of the upperLeft point
     * @param y      is the y value of the upperLeft point
     * @param width  is the width of the block
     * @param height is the height of the block
     * @param image  is an image.
     */

    public Block(double x, double y, double width, double height, Image image) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = Color.white;
        this.hitListeners = new ArrayList<>();
        this.isImage = true;
        this.image = image;
    }


    /**
     * get the collision rectangle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * get the velocity after the hot occurred.
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity before the hit.
     * @param hitter          is the hitter ball.
     * @return velocity after the hot occurred.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        /* get the lines of the rectangle
         lines[0] = upper line
         lines[1] = left line
         lines[2] = bottom line
         lines[3] = right line
        */
        Line[] lines = this.rectangle.linesFromRectangle();
        // copy the current velocity to a new velocity
        Velocity hitVelocity = new Velocity(currentVelocity);
        // if the collision point is in the upper line and dy is positive,
        // or if the collision point is in the bottom line and the dy is negative
        if ((lines[0].isInRange(collisionPoint) && (currentVelocity.getDy() > 0))
                || (lines[2].isInRange(collisionPoint) && (currentVelocity.getDy() < 0))) {
            // change the velocity
            hitVelocity = new Velocity(hitVelocity.getDx(), -1 * hitVelocity.getDy());
        }
        // if the collision point is in the left line and dx is positive,
        // or if the collision point is in the right line and the dx is negative
        if ((lines[1].isInRange(collisionPoint) && (currentVelocity.getDx() > 0))
                || (lines[3].isInRange(collisionPoint) && (currentVelocity.getDx() < 0))) {
            // change the velocity
            hitVelocity = new Velocity(-1 * hitVelocity.getDx(), hitVelocity.getDy());
        }
        this.notifyHit(hitter);
        return hitVelocity;
    }

    /**
     * draw the block on the surface.
     *
     * @param d is the draw surface
     */
    public void drawOn(DrawSurface d) {
        if (isImage) {
            drawOnWithImage(d);
            return;
        }
        d.setColor(this.color);
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        d.fillRectangle(x, y, width, height);
        if (stroke == null) {
            return;
        }
        d.setColor(stroke);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * draw the block with the image.
     *
     * @param d is the drawSurface.
     */
    private void drawOnWithImage(DrawSurface d) {
        int x = (int) this.rectangle.getUpperLeft().getX();
        int y = (int) this.rectangle.getUpperLeft().getY();
        int width = (int) this.rectangle.getWidth();
        int height = (int) this.rectangle.getHeight();
        d.drawImage(x, y, image);
        if (stroke == null) {
            return;
        }

        d.setColor(stroke);
        d.drawRectangle(x, y, width, height);
    }

    /**
     * timePassed for the block.
     */
    public void timePassed() {
    }

    /**
     * add the block to the game.
     *
     * @param g is the game.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * get the block color.
     *
     * @return color of the block.
     */
    public java.awt.Color getColor() {
        return this.color;

    }

    /**
     * removes the block from the game.
     *
     * @param gameLevel is the game.
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * the method notifies all the hitListeners that hit occurred.
     *
     * @param hitter is the hitter ball.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * get the Block width.
     *
     * @return block width.
     */
    public double getBlockWidth() {
        return this.rectangle.getWidth();
    }


}
