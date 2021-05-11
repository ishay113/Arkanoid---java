package geomatry;
// ID: 316609411

import java.util.List;

/**
 * The class creates a line
 * A line (actually a line-segment) connects two points -- a start point and an end point.
 * Lines have lengths, and may intersect with other lines. It can also tell if it is the same as another line segment.
 */
public class Line {

    static final double THRESHOLD = 0.0001;
    // the start point of the line
    private Point start;
    // the start point of the line
    private Point end;
    // is the line vertical or not
    private boolean isVertical;


    /**
     * constructor with start point and end point.
     *
     * @param start is the start point of the line.
     * @param end   is the end point of the line.
     */
    public Line(Point start, Point end) {
        this.start = new Point(start);
        this.end = new Point(end);
        // if the x values are equals it's mean the line is vertical
        isVertical = (Math.abs(start.getX() - end.getX()) < THRESHOLD);
    }

    /**
     * constructor with x value and y value.
     *
     * @param x1 is the x value of the start point.
     * @param y1 is the y value of the start point.
     * @param x2 is the x value of the end point.
     * @param y2 is the y value of the end point.
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
        // if the x values are equals it's mean the line is vertical
        isVertical = (Math.abs(x1 - x2) < THRESHOLD);
    }


    /**
     * The length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * The middle point of the line.
     *
     * @return the middle point of the line.
     */
    public Point middle() {
        double middleX = ((start.getX() + end.getX()) / 2);
        double middleY = ((start.getY() + end.getY()) / 2);
        return new Point(middleX, middleY);
    }

    /**
     * The start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * The end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * The incline the line.
     *
     * @return the incline of the line
     */
    public double slope() {
        double incline = (this.end.getY() - this.start.getY()) / (this.end.getX() - this.start().getX());
        return incline;
    }

    /**
     * Get the n in the equation of the line.
     *
     * @return the intercept of the line's equation.
     */

    private double getIntercept() {
        double intercept = (this.end.getY() - this.slope() * end.getX());
        return intercept;
    }

    /**
     * Tell us if point is in the line segment.
     *
     * @param point is the point we want to check
     * @return true if the point is in the line segment, false otherwise.
     */
    public boolean isInRange(Point point) {
        //
        double minX = Math.min(this.start.getX(), this.end.getX());
        double maxX = Math.max(this.start.getX(), this.end.getX());
        double minY = Math.min(this.start.getY(), this.end.getY());
        double maxY = Math.max(this.start.getY(), this.end.getY());
        //
        return ((minX < point.getX()) || (Math.abs(minX - point.getX()) < THRESHOLD))
                && ((maxX > point.getX()) || (Math.abs(maxX - point.getX()) < THRESHOLD))
                && ((minY < point.getY()) || (Math.abs(minY - point.getY()) < THRESHOLD))
                && ((maxY > point.getY()) || (Math.abs(maxY - point.getY()) < THRESHOLD));

    }

    /**
     * Tell us if lines continue each other.
     *
     * @param other is the line we want to check.
     * @return true if the lines continue each other, false otherwise.
     */
    private boolean isContinueLine(Line other) {
        // if there is intersection point and the rest of the points are not in range,
        // then the lines continue each other
        return ((this.start.equals(other.end) && (!this.isInRange(other.start)) && (!other.isInRange(this.end)))
                || (this.start.equals(other.start) && (!this.isInRange(other.end)) && (!other.isInRange(this.end)))
                || (this.end.equals(other.start) && (!this.isInRange(other.end)) && (!other.isInRange(this.start)))
                || (this.end.equals(other.end) && (!this.isInRange(other.start)) && (!other.isInRange(this.start))));
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other is the line we want to check if the lines intersect.
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        // if the lines are equals or the lines are the same but opposite
        if (this.equals(other) || (this.start.equals(other.end) && this.end.equals(other.start))) {
            return false;
        }
        // if lines continue each other, return true
        if (isContinueLine(other)) {
            return true;
        }
        // if both lines are verticals they are not intersect
        if (this.isVertical && other.isVertical) {
            return false;
        }
        // if this line is vertical
        if (this.isVertical) {
            double x = this.start.getX();
            double y = (other.slope() * x + other.getIntercept());
            Point p = new Point(x, y);
            return (this.isInRange(p) && other.isInRange(p));
        }
        // if other line is vertical
        if (other.isVertical) {
            double x = other.start.getX();
            double y = (this.slope() * x + this.getIntercept());
            Point p = new Point(x, y);
            return (this.isInRange(p) && other.isInRange(p));
        }
        // if the slope is equal then the lines parallels or the same
        if (Math.abs(this.slope() - other.slope()) < THRESHOLD) {
            return false;
        }
        // find the intersection point of the line's equations
        double x = (other.getIntercept() - this.getIntercept()) / (this.slope() - other.slope());
        double y = this.slope() * x + this.getIntercept();
        Point intersectPoint = new Point(x, y);
        // check if the point is in the lines
        return (this.isInRange(intersectPoint) && other.isInRange(intersectPoint));
    }

    /**
     * The intersection point if the lines intersect.
     *
     * @param other is the line we want to check if the lines intersect.
     * @return the intersection point if the lines intersect, and null otherwise.
     */
    public Point intersectionWith(Line other) {
        if (!this.isIntersecting(other)) {
            return null;
        }
        // if the lines continue each other return the intersection point
        if (this.start.equals(other.end) || (this.start.equals(other.start))) {
            return this.start;
        }
        if ((this.end.equals(other.end) || (this.end.equals(other.start)))) {
            return this.end;
        }

        // if this line is vertical, find the intersection point
        if (this.isVertical) {
            double x = this.start.getX();
            double y = (other.slope() * x + other.getIntercept());
            return new Point(x, y);
        }
        // if other line is vertical, find the intersection point
        if (other.isVertical) {
            double x = other.start.getX();
            double y = (this.slope() * x + this.getIntercept());
            return new Point(x, y);
        }
        // else find the intersection point
        double x = (other.getIntercept() - this.getIntercept()) / (this.slope() - other.slope());
        double y = this.slope() * x + this.getIntercept();
        return new Point(x, y);
    }

    /**
     * Return true is the lines are equal, false otherwise.
     *
     * @param other is the line we want to check if the lines equals.
     * @return return true is the lines are equal, false otherwise
     */

    public boolean equals(Line other) {
        return (this.start.equals(other.start()) && this.end.equals(other.end()));
    }

    /**
     * if this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the
     * start of the line.
     *
     * @param rect is the rectangle we check if intersect the line.
     * @return the closest intersection point.
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> pointList = rect.intersectionPoints(this);
        if (pointList.isEmpty()) {
            return null;
        }
        if (pointList.size() == 1) {
            return pointList.get(0);
        }
        if (pointList.size() == 2) {
            Point point = (start.distance(pointList.get(0)) < start.distance(pointList.get(1)))
                    ? pointList.get(0) : pointList.get(1);
            return point;
        }
        if (pointList.get(0).equals(pointList.get(1))) {
            Point point = (start.distance(pointList.get(0)) < start.distance(pointList.get(2)))
                    ? pointList.get(0) : pointList.get(2);
            return point;
        }
        Point point = (start.distance(pointList.get(0)) < start.distance(pointList.get(1)))
                ? pointList.get(0) : pointList.get(1);
        return point;
    }
}

