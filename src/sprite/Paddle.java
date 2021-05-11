package sprite;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.GameEnvironment;
import game.GameLevel;
import geomatry.Point;
import geomatry.Rectangle;

/**
 * The class creates a paddle.
 * The sprite.Paddle is the player in the game. It is a rectangle that is controlled by the arrow keys,
 * and moves according to the player key presses.
 */
public class Paddle implements Sprite, Collidable {


    private biuoop.KeyboardSensor keyboard;
    private Block paddle;
    private double paddleRegion;
    private GameEnvironment gm;
    private double paddleSpeed;

    /**
     * constructor with block and keyboard.
     *
     * @param block    is the block of the paddle
     * @param keyboard is the keyboard of the paddle.
     * @param speed    is the paddle speed.
     */
    public Paddle(Block block, biuoop.KeyboardSensor keyboard, double speed) {
        this.paddle = new Block(block);
        this.keyboard = keyboard;
        this.paddleRegion = block.getCollisionRectangle().getWidth() / 5;
        this.paddleSpeed = speed;
    }

    /**
     * moving the paddle left.
     */
    public void moveLeft() {
        // distention x value of the paddle
        double x = paddle.getCollisionRectangle().getUpperLeft().getX() - paddleSpeed;
        // distention y value of the paddle
        double y = paddle.getCollisionRectangle().getUpperLeft().getY();
        // width of the paddle
        double width = paddle.getCollisionRectangle().getWidth();
        // height of the paddle
        double height = paddle.getCollisionRectangle().getHeight();

        // not allowed the paddle get out of the borders
        if (x < this.gm.getBorderFromLeft()) {
            // move the paddle to the border
            this.paddle = new Block(new Rectangle(gm.getBorderFromLeft(), y, width, height), paddle.getColor());
            // else move the paddle left according to the paddle speed.
        } else {
            this.paddle = new Block(new Rectangle(x, y, width, height), paddle.getColor());
        }
    }

    /**
     * moving the paddle right.
     */
    public void moveRight() {
        // distention x value of the paddle
        double x = paddle.getCollisionRectangle().getUpperLeft().getX() + paddleSpeed;
        // distention y value of the paddle
        double y = paddle.getCollisionRectangle().getUpperLeft().getY();
        // width of the paddle
        double width = paddle.getCollisionRectangle().getWidth();
        // height of the paddle
        double height = paddle.getCollisionRectangle().getHeight();

        // not allowed the paddle get out of the borders
        if ((x + width) > this.gm.getBorderFromRight()) {
            // move the paddle to the border
            this.paddle = new Block(new Rectangle(gm.getBorderFromRight() - width, y, width, height),
                    paddle.getColor());
            // else move the paddle left according to the paddle speed.
        } else {
            this.paddle = new Block(new Rectangle(x, y, width, height), paddle.getColor());
        }
    }

    /**
     * implements the timePassed. if is left key pressed move lefr, if right key pressed movr right.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * draw the paddle.
     *
     * @param d is the drawSurface
     */
    public void drawOn(DrawSurface d) {
        this.paddle.drawOn(d);
    }

    /**
     * get the collision rectangle.
     *
     * @return the collision rectangle.
     */
    public Rectangle getCollisionRectangle() {
        return this.paddle.getCollisionRectangle();
    }

    /**
     * get the velocity after the hit occurred.
     *
     * @param collisionPoint  the collision point.
     * @param currentVelocity the current velocity before the hit.
     * @param hitter          is the hitter ball.
     * @return velocity after the hit occurred.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        // separate the paddle to 5 different regions, and do casting to int type
        int regionHit = (int) (((collisionPoint.getX() - this.paddle.getCollisionRectangle().getUpperLeft().getX())
                / this.paddleRegion) + 1);
        switch (regionHit) {

            case 0:
            case 1:
                return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
            case 2:
                return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
            case 3:
                return paddle.hit(hitter, collisionPoint, currentVelocity);
            case 4:
                return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
            case 5:
            case 6:
                return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
            default:
                throw new IllegalStateException("Unexpected value: " + regionHit);
        }
    }

    /**
     * add the paddle to the game.
     *
     * @param g is the game.
     */
    // Add this paddle to the game.
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * set the game environment to the paddle.
     *
     * @param gm1 is the game environment.
     */
    public void setGm(GameEnvironment gm1) {
        this.gm = gm1;
    }
}
