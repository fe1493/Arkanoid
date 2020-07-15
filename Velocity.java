package sprites;

import geommetry.Point;

/**
 * This class is a velocity class.
 */
public class Velocity {
    //fields
    private double dx;
    private double dy;
    // constructor
    /**
     * The method constructs sprites.Velocity.
     * @param dx the amount we want to change on the x axis
     * @param dy the amount we want to change on the y axis
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }
    /**
     * The method returns the dx.
     * @return this.dx the new x
     */
    public double getDx() {
        return this.dx;
    }
    /**
     * The method returns the dy.
     * @return this.dy the new y
     */
    public double getDy() {
        return this.dy;
    }
    /**
     * The method sets the dx.
     * @param dx1 the new dx
     */
    public void setDx(double dx1) {
        this.dx = dx1;
    }
    /**
     * The method sets the dy.
     * @param dy1 the new dy
     */
    public void setDy(double dy1) {
        this.dy = dy1;
    }
    /**
     * The method takes a point with position (x,y) and returns a new point with position (x+dx, y+dy).
     * @param p the point with the original positions
     * @return newP the new point
     */
    public Point applyToPoint(Point p) {
        double newX, newY;
        Point newP;
        //the new x
        newX = p.getX() + this.dx;
        //the new y
        newY = p.getY() + this.dy;
        //the new point
        newP = new Point((newX), (newY));
        return newP;
    }
    /**
     * The method changes the angle and the speed of the ball.
     * @param angle the angle we want the ball to move
     * @param speed the speed we want the ball to move
     * @return new sprites.Velocity the new velocity
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double adjustAngle = angle - 90;
        double nextX = speed * Math.cos(Math.toRadians(adjustAngle));
        double nextY = speed * Math.sin(Math.toRadians(adjustAngle));
        return new Velocity(nextX, nextY);
        }
    }
