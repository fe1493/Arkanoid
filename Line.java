package geommetry;

import biuoop.DrawSurface;

import java.util.List;


/**
 * This class does a lot of different actions on a line.
 */
public class Line {
    //fields
    private Point start; //start line
    private Point end;  //end line
    // constructors

    /**
     * The method initiates a start and end for the line.
     *
     * @param start the start point of the lineg
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * The method initiates a start and end for the line.
     *
     * @return this.line our current line
     */
    public Line getLine() {
        return this;
    }

    /**
     * The method intitates the first and last points.
     *
     * @param x1 the start x coordinate
     * @param y1 the start y coordinate
     * @param x2 the end x coordinate
     * @param y2 the end y coordinate
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * The method returns the length of a line.
     *
     * @return Math.sqrt(( start.getX () - start.getY()) * (start.getX() - start.getY())
     * + (end.getX() - end.getY()) * (end.getX() - end.getY()))
     */

    // Return the length of the line
    public double length() {
        return Math.sqrt((start.getX() - start.getY()) * (start.getX() - start.getY())
                + (end.getX() - end.getY()) * (end.getX() - end.getY()));
    }

    /**
     * The method returns the middle of the line.
     *
     * @return middleOfLine
     */
    // Returns the middle point of the line
    public Point middle() {
        Point middleOfLine;
        double middleOfX = (this.start.getX() + this.end.getX()) / 2;
        double middleOfY = (this.start.getY() + this.end.getY()) / 2;
        middleOfLine = new Point(middleOfX, middleOfY);
        return middleOfLine;
    }

    /**
     * The method returns the start point of the line.
     *
     * @return this.start
     */
    // Returns the start point of the line
    public Point start() {
        return this.start;
    }

    /**
     * The method returns he end point of the line.
     *
     * @return this.end
     */
    // Returns the end point of the line
    public Point end() {
        return this.end;
    }

    /**
     * The method returns the end point of the line.
     *
     * @return !(this.end.getX() == this.start.getX())
     */
    public boolean hasSlope() {
        if (this.start.getX() == this.end.getX()) {
            return false;
        }
        return true;
    }

    /**
     * The method retruns the slope of a line.
     *
     * @return (( this.end.getY () - this.start.getY()) / this.end.getX()
     * - this.start.getX())
     */
    // Returns the slope of our line
    public double linesSlope() {
        return ((this.end.getY() - this.start.getY()) / (this.end.getX() - this.start.getX()));
    }

    /**
     * The method is checks if the lines intersect.
     *
     * @param other the other line
     * @return true or false
     */
    // Returns true if the lines intersect, false otherwise
    public boolean isIntersecting(Line other) {
        //declare the intersection point
        Point intersectionPoint = intersectionPoint(other);
        //if the intersection point is null
        if (intersectionPoint == null) {
            return false;
        }
        //if the intersection point is in between our two lines
        if (((this.end.getX() <= intersectionPoint.getX() && intersectionPoint.getX() <= this.start.getX())
                || (this.start.getX() <= intersectionPoint.getX() && intersectionPoint.getX() <= this.end.getX()))
                && ((this.end.getY() <= intersectionPoint.getY() && intersectionPoint.getY() <= this.start.getY())
                || (this.start.getY() <= intersectionPoint.getY() && intersectionPoint.getY() <= this.end.getY()))
                && ((other.end.getX() <= intersectionPoint.getX() && intersectionPoint.getX() <= other.start.getX())
                || (other.start.getX() <= intersectionPoint.getX() && intersectionPoint.getX() <= other.end.getX()))
                && ((other.end.getY() <= intersectionPoint.getY() && intersectionPoint.getY() <= other.start.getY())
                || (other.start.getY() <= intersectionPoint.getY() && intersectionPoint.getY() <= other.end.getY()))) {
            return true;
        }
        return false;
    }

    /**
     * The method checks if there is an intersection point.
     *
     * @param other the other line
     * @return true or false
     */
    // Returns the intersection point if the lines intersect
    // and null otherwise.
    public Point intersectionWith(Line other) {
        if (isIntersecting(other)) {
            return intersectionPoint(other);
        }
        return null;
    }

    /**
     * The method is in charge of finding the intersecting point of a line.
     *
     * @param other the other line
     * @return the intersection point or null
     */
    public Point intersectionPoint(Line other) {
        //declare variables
        double m1 = 0, m2 = 0, c1, c2, newX = 0, newY = 0;
        // if the lines have no slope
        if (!hasSlope() && !other.hasSlope()) {
            //then the slopes are equal
            m1 = m2;
        } else if (!hasSlope()) {  //if this line has no slope, calculate the y point
            m2 = other.linesSlope();
            c2 = other.start.getY() - (m2 * other.start.getX());
            newX = this.start.getX();
            newY = (m2 * newX) + c2;
        } else if (!other.hasSlope()) {  //if the other line has no slope then calculate the y point
            m1 = this.linesSlope();
            c1 = this.start.getY() - (m1 * this.start.getX());
            newX = other.start.getX();
            newY = (m1 * newX) + c1;
        } else {
            //get the slopes
            m1 = this.linesSlope();
            m2 = other.linesSlope();
            //calculate the equation
            c1 = this.start.getY() - (m1 * this.start.getX());
            c2 = other.start.getY() - (m2 * other.start.getX());
            newX = (c2 - c1) / (m1 - m2);
            newY = (m1 * newX) + c1;
        }

        //if the slopes are equal
        if (m1 == m2) {
            // if the lines overlap return a point on those lines
            if (this.start.getX() < other.start.getX() && other.start.getX() < this.end.getX()
                    || this.end.getX() < other.start.getX() && other.start.getX() < this.start.getX()) {
                return other.start;
            }
            if (this.end.getX() < other.end.getX() && other.end.getX() < this.start.getX()
                    || this.start.getX() < other.end.getX() && other.end.getX() < this.end.getX()) {
                return other.end;
            }
            if (this.end.getX() == other.start.getX() || this.end.getX() == other.end.getX()) {
                return this.end;
            }
            if (this.start.getX() == other.start.getX() || this.start.getX() == other.end.getX()) {
                return this.start;
            }
            return null;
        }
        //return the intersection point
        return new Point(newX, newY);
    }

    /**
     * The method returns if the lines are equal or not.
     * @param other the other line
     * @return this.end.getY() == other.end.getY() && this.start.getY() == other.start.getY()
     * && this.end.getX() == other.end.getX() && this.start.getX() == this.start.getY());
     */
    // equals -- return true is the lines are equal, false otherwise
    public boolean equals(Line other) {
        return (this.end.getY() == other.end.getY() && this.start.getY() == other.start.getY()
                && this.end.getX() == other.end.getX() && this.start.getX() == this.start.getX());
    }

    /**
     * The method draws the line.
     * @param drawSurface a surface to draw on
     */
    public void draw(DrawSurface drawSurface) {
        drawSurface.drawLine((int) this.start().getX(), (int) this.start().getY(), (int) this.end().getX(),
                (int) this.end().getY());
    }
    /**
     * The method draws the line.
     * @param rect a rectangle to check what the closest point is
     * @return the closest point
     */

    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> listOfIntersectionPoints = rect.intersectionPoints(this);
        Point closestPoint;
        //if the list is empty
        if (listOfIntersectionPoints == null) {
            return null;
        }
        //start the point to be the first point in the list
        if (listOfIntersectionPoints.size() >= 1) {
            closestPoint = listOfIntersectionPoints.get(0);
            //loop over the list to see which point is the closest intersection point
            for (Point i : listOfIntersectionPoints) {
                if (this.start().distance(i) < this.start().distance(closestPoint)) {
                    closestPoint = i;
                }
            }
            return closestPoint;
        }
        return null;
    }

    /**
     * The method draws the line.
     * @param point a point to check if it is on the line
     * @return true if the point is on the line
     */
    public boolean checkIfOnLine(Point point) {
        //make an infi number to insure our calculation is correct
        double epsilon = 0.1;
        //return true if the point is on the line
        return (this.start().distance(this.end) - epsilon <= this.start().distance(point) + this.end().distance(point)
                && this.start().distance(point) + this.end().distance(point)
                <= this.start().distance(this.end) + epsilon);
    }
}