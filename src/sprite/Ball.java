package sprite;
// ID: 316609411

import biuoop.DrawSurface;
import game.GameEnvironment;
import game.GameLevel;
import geomatry.Line;
import geomatry.Point;

import java.awt.Color;


/**
 * The class creates a ball
 * A ball (actually a circle) has point center, radius, color and velocity.
 * the class supports methods like draw a ball, set velocity to the ball and move one step according to the velocity.
 */
public class Ball implements Sprite {
    // the center point of the ball
    private Point center;
    // the radius of the ball
    private int radius;
    // the color of the ball
    private java.awt.Color color;
    // the velocity of the ball
    private Velocity velocity;
    // the game environment
    private GameEnvironment gm;


    /**
     * constructor with center point, radius, and color.
     *
     * @param center is the center point of the ball.
     * @param r      is the radius of the ball.
     * @param color  is the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = new Point(center);
        this.radius = r;
        this.color = color;
    }

    /**
     * constructor with double x1, double x2, radius, and color.
     *
     * @param x      is the x value of the center point.
     * @param y      is the y value of the center point.
     * @param radius is the radius of the ball.
     * @param color  is the color of the ball.
     */
    public Ball(double x, double y, int radius, java.awt.Color color) {
        this.center = new Point(x, y);
        this.radius = radius;
        this.color = color;
    }


    /**
     * The x value of the center point.
     *
     * @return the x value of the center point.
     */
    public int getX() {
        return (int) this.center.getX();

    }

    /**
     * The y value of the center point.
     *
     * @return the y value of the center point.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * The radius of the ball.
     *
     * @return The radius of the ball.
     */
    public int getSize() {
        return this.radius;

    }

    /**
     * The color of the ball.
     *
     * @return The color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;

    }

    /**
     * Draw the ball on the given DrawSurface.
     *
     * @param surface is the surface we draw on it.
     */

    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), radius);
        surface.setColor(Color.black);
        surface.drawCircle(this.getX(), this.getY(), radius);
    }

    @Override
    public void timePassed() {
        moveOneStep();
    }

    /**
     * The velocity of the ball.
     *
     * @param v is the velocity of the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v);
    }

    /**
     * Set velocity to the ball.
     *
     * @param dx is change in the x axe.
     * @param dy is change in the y axe.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * Set the game environment for the ball.
     *
     * @param gm1 is the game environment.
     */
    public void setGm(GameEnvironment gm1) {
        this.gm = gm1;
    }

    /**
     * The velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }


    /**
     * Move the ball one step according the velocity.
     */
    public void moveOneStep() {
        // compute the trajectory line
        Line trajectory = this.computeTrajectory();
        // get the closest collisionInfo
        CollisionInfo c = this.gm.getClosestCollision(trajectory);
        // if the collisionInfo is null, then it's mean the path is clean and move the ball
        if (c == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            // else there is a hit
        } else {
            // move the ball almost to the collision point and change the velocity
            this.center = new Point(c.collisionPoint().getX() - epsilonX(), c.collisionPoint().getY() - epsilonY());
            this.velocity = c.collisionObject().hit(this, c.collisionPoint(), this.getVelocity());
        }

    }

    /**
     * Move the ball one step according the velocity, and make sure the ball doesn't go outside of the screen
     * or the border we defined.
     *
     * @param leftUp      is the left upper vertex of the border that the ball must not goes outside.
     * @param rightBottom is the right bottom vertex of the border that the ball must not goes outside.
     */
    public void moveOneStep(Point leftUp, Point rightBottom) {
        // if the ball goes out of the right border
        if ((this.velocity.applyToPoint(center).getX() > (rightBottom.getX() - this.radius))) {
            // locate the center in the opposite side so the next move will be inside the window, in the right point.
            this.center = new Point(2 * rightBottom.getX() - this.center.getX() - 2 * this.radius, this.center.getY());
            // change the velocity.
            this.velocity = new Velocity(-1 * this.velocity.getDx(), this.velocity.getDy());
            // call recursive to moveOneStep.
            moveOneStep(leftUp, rightBottom);
            return;
        }
        // if the ball goes out of the left border
        if (this.velocity.applyToPoint(center).getX() < (leftUp.getX() + this.radius)) {
            // locate the center in the opposite side so the next move will be inside the window, in the right point.
            this.center = new Point(-1 * this.center.getX() + 2 * this.radius + 2 * leftUp.getX(), this.center.getY());
            // change the velocity.
            this.velocity = new Velocity(-1 * this.velocity.getDx(), this.velocity.getDy());
            // call recursive to moveOneStep.
            moveOneStep(leftUp, rightBottom);
            return;
        }
        // if the ball goes out of the bottom border
        if ((this.velocity.applyToPoint(center).getY() > (rightBottom.getY() - this.radius))) {
            // locate the center in the opposite side so the next move will be inside the window, in the right point.
            this.center = new Point(this.center.getX(), 2 * rightBottom.getY() - this.center.getY() - 2 * this.radius);
            // change the velocity.
            this.velocity = new Velocity(this.velocity.getDx(), -1 * this.velocity.getDy());
            // call recursive to moveOneStep.
            moveOneStep(leftUp, rightBottom);
            return;
        }
        // if the ball goes out of the top border
        if (this.velocity.applyToPoint(center).getY() < leftUp.getY() + this.radius) {
            // locate the center in the opposite side so the next move will be inside the window, in the right point.
            this.center = new Point(this.center.getX(), -1 * this.center.getY() + 2 * this.radius + 2 * leftUp.getY());
            // change the velocity.
            this.velocity = new Velocity(this.velocity.getDx(), -1 * this.velocity.getDy());
            // call recursive to moveOneStep.
            moveOneStep(leftUp, rightBottom);
            return;
        }
        this.center = this.getVelocity().applyToPoint(this.center);
    }

    /**
     * compute the line trajectory of the ball according to the velocity.
     *
     * @return the line trajectory of the ball
     */
    public Line computeTrajectory() {
        return new Line(this.center, this.getVelocity().applyToPoint(this.center));
    }

    /**
     * epsilonX for the ball when collision occurred, matches the radius and the velocity.
     *
     * @return epsilonX for the ball when collision occurred.
     */
    private double epsilonX() {
        // if the velocity in dx is 0, then return the epsilonX to be 0
        if (this.getVelocity().getDx() == 0) {
            return 0;
        }
        // matches the radius and the direction
        double epsilonX = (this.getVelocity().getDx() > 0) ? this.radius : (-1 * this.radius);
        return epsilonX;
    }

    /**
     * epsilonY for the ball when collision occurred, matches the radius and the velocity.
     *
     * @return epsilonY for the ball when collision occurred.
     */
    private double epsilonY() {
        // if the velocity in dy is 0, then return the epsilonY to be 0
        if (this.getVelocity().getDy() == 0) {
            return 0;
        }
        //// matches the radius and the direction
        double epsilonY = (this.getVelocity().getDy() > 0) ? this.radius : (-1 * this.radius);
        return epsilonY;
    }

    /**
     * add the ball to the game.
     *
     * @param g is the game we add the ball
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * remove the ball from the game.
     *
     * @param g is the game.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }
}



