package sprite;

import geomatry.Point;
import geomatry.Rectangle;


/**
 * The sprite.Collidable interface is for all objects that are collidable.
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return rectangle
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us)
     *
     * @param currentVelocity the velocity before the hit
     * @param collisionPoint  the collision point
     * @param hitter          is the hitter ball.
     * @return the velocity after the hit occurred.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
