package geommetry;

import biuoop.DrawSurface;

/**
 * This class is a point on a line class.
 */
public class Point {
    //fields
    //create a point
    private double x;
    private double y;
    /**
     * The method constructs the x and y coordinates for a point.
     *@param x the start point of the line
     *@param y the end point of the line
     */
    public Point(double x, double y) {
        //constructor of a point
        this.x = x;
        this.y = y;
    }
    /**
     * The method returns the distance of two points.
     *@param other the other line
     *@return Math.sqrt(( this.x - other.x) * (this.x - other.x)
     *                  + (this.y - other.y) * (this.y - other.y)) the distance;
     */
    public double distance(Point other) {
        // distance -- return the distance of this point to the other point
        return Math.sqrt((this.x - other.x) * (this.x - other.x)
                 + (this.y - other.y) * (this.y - other.y));
    }

    /**
     * The method tells us if the points are equal or not.
     *@param other the start point of the line
     *@return true if the points or equal or false if not
     */
    // equals -- return true if the points are equal, false otherwise
    public boolean equals(Point other) {
        return ((this.x == other.getX()) && (this.y == other.getY()));
    }
    /**
     * The method gets the x.
     * @return the x value of this point
     */
    public double getX() {
        // get the x
        return this.x;
    }
    /**
     * The method gets the y.
     * @return the y value of this point
     */
    public double getY() {
        //get the y
        return this.y;
    }
    /**
     * The method sets the x.
     * @param x1 the x
     */
    public void setX(double x1) {
        this.x = x1;
    }
    /**
     * The method sets the y.
     * @param y1 the y
     */
    public void setY(double y1) {
        this.y = y1;
    }
    /**
     * The method draws a point.
     * @param drawSurface a surface to drive on
     */
    public void draw(DrawSurface drawSurface) {
        //draw a circle
        int radius = 3;
        drawSurface.fillCircle((int) this.getX(), (int) this.getY(), radius);
    }
}