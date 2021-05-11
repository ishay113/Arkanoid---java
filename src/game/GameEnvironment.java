package game;

import geomatry.Line;
import geomatry.Point;
import sprite.Collidable;
import sprite.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * The class creates a game environment.
 * game environment has list of collidables objects
 * the class supports to add collidables objects to the game, and get the closest collision info when hit occurred
 */
public class GameEnvironment {
    // list of collidables objects
    private List<Collidable> collidables;
    // the border size from left
    private double borderFromLeft;
    // the border size from right
    private double borderFromRight;

    /**
     * constructor  - initialize an empty list of collidable.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c is the collidable object
     */
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }

    // Assume an object moving from line.start() to line.end().
    //

    /**
     * if this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory is the trajectory line of the object.
     * @return the closest collision that is going to occur
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        // an ArrayList of sprite.CollisionInfo to store the information about the closest collision
        List<CollisionInfo> closestCollision = new ArrayList<>();
        // loop over the collidables list
        for (Collidable c : collidables) {
            // if there is an intersection point then add this point to the closestCollision list
            if (trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()) != null) {
                Point p = new Point(trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle()));
                closestCollision.add(new CollisionInfo(p, c));
            }
        }
        // if this object will not collide with any of the collidables in this collection, return null
        if (closestCollision.isEmpty()) {
            return null;
        }
        // if it's not empty, then find the closest collision point
        CollisionInfo temp = closestCollision.get(0);
        for (CollisionInfo c : closestCollision) {
            double dis1 = trajectory.start().distance(temp.collisionPoint());
            double dis2 = trajectory.start().distance(c.collisionPoint());
            temp = (dis1 < dis2) ? temp : c;
        }
        return temp;

    }

    /**
     * set the border from left.
     *
     * @param width is the width of the block that block the border from left
     */
    public void setBorderFromLeft(double width) {
        this.borderFromLeft = width;
    }

    /**
     * set the border from right.
     *
     * @param width is the width of the block that block the border from right.
     */
    public void setBorderFromRight(double width) {
        this.borderFromRight = width;
    }

    /**
     * get the border from left.
     *
     * @return border from left
     */
    public double getBorderFromLeft() {
        return borderFromLeft;
    }

    /**
     * get the border from right.
     *
     * @return border from right
     */
    public double getBorderFromRight() {
        return borderFromRight;
    }
    /**
     * remove a collidable object from the game environment.
     *
     * @param c is a collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }
}
