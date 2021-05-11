package sprite;

import geomatry.Point;


/**
 * The class creates collision info
 * collisionInfo has a collision point and the collidable object.
 * the class supports to get the point at which the collision occurs,
 * and to get the collidable object involved in the collision.
 */
public class CollisionInfo {
    // the collision point
    private Point collisionPoint;
    // the collidable object
    private Collidable object;

    /**
     * constructor with collision point and collidable object.
     *
     * @param collisionPoint is the collision point.
     * @param object         is the collidable object.
     */
    public CollisionInfo(Point collisionPoint, Collidable object) {
        this.collisionPoint = new Point(collisionPoint);
        this.object = object;
    }

    /**
     * the point at which the collision occurs.
     *
     * @return the collision point
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return the collision object
     */
    public Collidable collisionObject() {
        return this.object;
    }
}

