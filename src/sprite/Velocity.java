package sprite;
// ID: 316609411

import geomatry.Point;

/**
 * The class creates a velocity
 * sprite.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    // the change in position on the 'x' axe.
    private double dx;
    // the change in position on the 'y' axe.
    private double dy;

    /**
     * constructor with double dx and double dy.
     *
     * @param dx is the change in position on the 'x' axe.
     * @param dy is the change in position on the 'y' axe.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * constructor with velocity v.
     *
     * @param v is the velocity we copy to this velocity.
     */
    public Velocity(Velocity v) {
        this.dx = v.getDx();
        this.dy = v.getDy();
    }

    /**
     * Get dx, change in position on the 'x' axe.
     *
     * @return dx, change in position on the 'x' axe.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Get dy, change in position on the 'y' axe.
     *
     * @return dx, change in position on the 'y' axe.
     */
    public double getDy() {
        return this.dy;
    }

    /**
     * Set velocity by angle and speed.
     *
     * @param angle the angle of the velocity (assuming 0 is up).
     * @param speed the speed of the velocity.
     * @return new velocity, that we set by angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // set dx and dy by trigonometry.
        double dx = Math.round(speed * Math.sin(Math.toRadians(angle)));
        // because 0 is up, we had to double with (-1).
        double dy = Math.round((-1) * speed * Math.cos(Math.toRadians(angle)));
        return new Velocity((int) dx, (int) dy);
    }

    /**
     * compute the speed of the velocity.
     *
     * @return the speed of the ball.
     */
    public double getSpeed() {
        double speed = Math.sqrt(Math.pow(this.getDx(), 2) + Math.pow(this.getDy(), 2));
        return speed;
    }

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p is the point in the start position.
     * @return a new point with position (x+dx, y+dy).
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + this.dx, p.getY() + this.dy);
    }
}
