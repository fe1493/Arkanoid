package geommetry;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is a rectangle class.
 */
public class Rectangle {
    //fields
    private Point point;
    private Point upperLeft;
    private Point lowerLeft;
    private Point lowerRight;
    private Point upperRight;
    private double width;
    private double height;

    /**
     * The method creates a new rectangle with location and width/height.
     * @param upperLeft the upper left corner of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.width = width;
        this.height = height;
        this.upperLeft = upperLeft;
    }
    /**
     * The method Return a (possibly empty) List of intersection points with the specified line.
     * @param line the line which we are checking the intersection points
     * @return the list of intersection points
     */
    // Return a (possibly empty) List of intersection points
    // with the specified line.
    public List<Point> intersectionPoints(Line line) {
        List<Point> listOfIntersectionPoints = new ArrayList<>();
        if (this.getTopLine().isIntersecting(line)) {
            Point topIntersecting = this.getTopLine().intersectionWith(line);
            listOfIntersectionPoints.add(topIntersecting);
        }
        if (this.getRightLine().isIntersecting(line)) {
            Point rightIntersecting = this.getRightLine().intersectionWith(line);
            listOfIntersectionPoints.add(rightIntersecting);
        }
        if (this.getBottomLine().isIntersecting(line)) {
            Point bottomIntersecting = this.getBottomLine().intersectionWith(line);
            listOfIntersectionPoints.add(bottomIntersecting);
        }
        if (this.getLeftLine().isIntersecting(line)) {
            Point leftIntersecting = this.getLeftLine().intersectionWith(line);
            listOfIntersectionPoints.add(leftIntersecting);
        }
        return listOfIntersectionPoints;
    }
    /**
     * The method returns the width of the rectangle.
     * @return  the width
     */
    public double getWidth() {
        return this.width;
    }
    /**
     * The method returns the height of the rectangle.
     * @return  the height
     */
    public double getHeight() {
        return this.height;
    }
    /**
     * The method returns the upper left point.
     * @return  the upper left point
     */

    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * The method sets the upper left point.
     * @param thisUpperLeft the current upper left
     * @return  the upper left point
     */
    public Point setUpperLeft(Point thisUpperLeft) {
        this.upperLeft = thisUpperLeft;
        return this.upperLeft;
    }
    /**
     * The method gets the upper right point.
     * @return  the upper right point
     */
    public Point getUpperRight() {
        this.upperRight = new Point(this.getUpperLeft().getX() + this.getWidth(),
                this.getUpperLeft().getY());
        return this.upperRight;
    }
    /**
     * The method sets the upper right point.
     * @param thisUpperRight the current upper right
     * @return  the upper right point
     */
    public Point setUpperRight(Point thisUpperRight) {
        this.upperRight = thisUpperRight;
        return this.upperRight;
    }
    /**
     * The method gets the lower left point.
     * @return  the lower left point
     */
    public Point getLowerLeft() {
        this.lowerLeft = new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight());
        return this.lowerLeft;
    }
    /**
     * The method sets the lower left point.
     * @param thisLowerLeft the current lower left
     * @return  the lower left point
     */
    public Point setLowerLeft(Point thisLowerLeft) {
        this.lowerLeft = thisLowerLeft;
        return this.lowerLeft;
    }
    /**
     * The method gets the lower right point.
     * @return  the lower right point
     */
    public Point getLowerRight() {
        this.lowerRight = new Point(this.getUpperLeft().getX() + getWidth(),
                this.getUpperLeft().getY() + this.getHeight());
        return this.lowerRight;
    }
    /**
     * The method sets the lower right point.
     * @param thisLowerRight the current lower left
     * @return  the lower right point
     */
    public Point setLowerRight(Point thisLowerRight) {
        this.lowerRight = thisLowerRight;
        return this.lowerRight;
    }
    /**
     * The method returns the top line  of the rectangle.
     * @return  the  top line
     */
    public Line getTopLine() {
        return new Line(getUpperLeft(), getUpperRight());
    }
    /**
     * The method returns the bottom line  of the rectangle.
     * @return  the  bottom line
     */
    public Line getBottomLine() {
        return  new Line(getLowerLeft(), getLowerRight());
    }
    /**
     * The method returns the left line  of the rectangle.
     * @return  the  left line
     */
    public Line getLeftLine() {
        return  new Line(getUpperLeft(), getLowerLeft());
    }
    /**
     * The method returns the right line  of the rectangle.
     * @return  the  right line
     */
    public Line getRightLine() {
        return new Line(getUpperRight(), getLowerRight());
    }
}