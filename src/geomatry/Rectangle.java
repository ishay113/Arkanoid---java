package geomatry;

import java.util.ArrayList;
import java.util.List;

/**
 * The class creates rectangle.
 * geomatry.Rectangle has upper left point, width and height.
 */
public class Rectangle {

    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor - create a new rectangle with location and width/height.
     *
     * @param upperLeft is the upper left point of the rectangle
     * @param width     is the width of the rectangle
     * @param height    is the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = new Point(upperLeft);
        this.height = height;
        this.width = width;
    }

    /**
     * constructor - create a new rectangle with exist rectangle.
     *
     * @param rectangle is a rectangle that we copy to the new rectangle.
     */
    public Rectangle(Rectangle rectangle) {
        this.upperLeft = new Point(rectangle.getUpperLeft());
        this.height = rectangle.getHeight();
        this.width = rectangle.getWidth();
    }

    /**
     * constructor - create a new rectangle with location and width/height.
     *
     * @param x      is the x value of the upper left point
     * @param y      is the y value of the upper left point
     * @param width  is the width of the rectangle
     * @param height is the height of the rectangle
     */
    public Rectangle(double x, double y, double width, double height) {
        this.upperLeft = new Point(x, y);
        this.height = height;
        this.width = width;
    }

    /**
     * List of intersection points with the specified line.
     *
     * @param line we check if intersection.
     * @return a (possibly empty) List of intersection points with the specified line.
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        // create a new point list
        List<Point> list = new ArrayList<>();
        // loop to add the intersection points with the line and the rectangle to the list
        for (int i = 0; i < 4; i++) {
            if (line.isIntersecting(this.linesFromRectangle()[i])) {
                list.add(line.intersectionWith(this.linesFromRectangle()[i]));
            }
        }
        return list;
    }

    /**
     * get the lines of the rectangle.
     *
     * @return lines - array of the rectangle lines
     */
    public Line[] linesFromRectangle() {
        Line[] lines = new Line[4];
        double x1 = this.upperLeft.getX();
        double y1 = this.upperLeft.getY();
        double x2 = x1 + this.width;
        double y2 = y1 + this.height;
        // the upper line of the rectangle
        lines[0] = new Line(upperLeft, new Point(x2, y1));
        // the left line
        lines[1] = new Line(upperLeft, new Point(x1, y2));
        // the bottom line
        lines[2] = new Line(x1, y2, x2, y2);
        // the right line
        lines[3] = new Line(x2, y2, x2, y1);
        return lines;
    }

    // Return

    /**
     * width of the rectangle.
     *
     * @return the width of the rectangle.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * height of the rectangle.
     *
     * @return the height of the rectangle.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * get the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * get the upper-Right point of the rectangle.
     *
     * @return the upper-Right point of the rectangle.
     */
    public Point getUpperRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY());
    }

    /**
     * get the bottom-Right point of the rectangle.
     *
     * @return the bottom-Right point of the rectangle.
     */
    public Point getBottomRight() {
        return new Point(this.upperLeft.getX() + width, this.upperLeft.getY() + height);
    }

    /**
     * get the bottom-Left point of the rectangle.
     *
     * @return the bottom-Left point of the rectangle.
     */
    public Point getBottomLeft() {
        return new Point(this.upperLeft.getX(), this.upperLeft.getY() + height);
    }

}
