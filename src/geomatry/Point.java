package geomatry;
// ID: 316609411

/**
 * The class creates a point
 * A point has an x and a y value, and can measure the distance to other points, and if it is equal to another point.
 */
public class Point {

    static final double THRESHOLD = 0.0001;
    // a is the x value of the point
    private double x;
    // b is the y value of the point
    private double y;

    /**
     * constructor with x value and y value.
     *
     * @param x is the x value of the point.
     * @param y is the y value of the point.
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * constructor with point as a parameter.
     *
     * @param p is the point.
     */
    public Point(Point p) {
        this.x = p.getX();
        this.y = p.getY();
    }

    /**
     * Distance return the distance of this point to the other point.
     *
     * @param other is the point we calculate the distance to it
     * @return the distance of this point to the other point.
     */
    public double distance(Point other) {
        double root = Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
        return root;
    }

    /**
     * return true is the points are equal, false otherwise.
     *
     * @param other is the point we compare
     * @return true is the points are equal, false otherwise
     */
    public boolean equals(Point other) {
        return ((Math.abs(x - other.getX()) < THRESHOLD) && Math.abs(y - (other.getY())) < THRESHOLD);
    }

    /**
     * Return the x and y values of this point.
     *
     * @return the x value of this point.
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the x and y values of this point.
     *
     * @return the x value of this point.
     */
    public double getY() {
        return this.y;
    }
}
