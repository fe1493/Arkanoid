package sprites;

import game.GameLevel;
import game.GameEnvironment;
import geommetry.Line;
import geommetry.Point;
import collisioninfo.CollisionInfo;
import biuoop.DrawSurface;
import java.awt.Color;
import geommetry.DrawCircle;

/**
 * This class is a ball class.
 */
public class Ball implements Sprite {
    //fields
    private Point center;
    private int r;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    private GameLevel ourGame;
    private static final int MODIFYYSOWONTGETSTUCK = 4;

    /**
     * The method constructs the ball.
     *
     * @param x               the x coordinate of the center point
     * @param y               the y coordinate of the center point
     * @param r               the radius of the ball
     * @param color           the color of the ball
     * @param gameEnvironment the game environment
     */
    // constructors
    public Ball(int x, int y, int r, Color color, GameEnvironment gameEnvironment) {
        this.center = new Point((double) x, (double) y);
        this.r = r;
        this.color = color;
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * The method gives us the ball with the center point.
     *
     * @param center the center point of the ball
     * @param r      the radius of the ball
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }
    // accessors

    /**
     * The method gives us the x point of the center.
     *
     * @return the x point of the center
     */
    public double getX() {
        //get the center x
        return this.center.getX();
    }

    /**
     * The method gives us the y point of the center.
     *
     * @return the y point of the center
     */
    public double getY() {
        // get the center y
        return this.center.getY();
    }

    /**
     * The method gives us the size of the ball.
     *
     * @return the size of the ball
     */
    public int getSize() {
        //get the size
        return this.r;
    }

    /**
     * The method gives us the color of the ball.
     *
     * @return the color of the ball
     */
    public Color getColor() {
        //get the color
        return this.color;
    }

    /**
     * The method gets us the game environment.
     *
     * @return the game environment
     */

    public GameEnvironment getGameEnvironment() {
        return this.gameEnvironment;
    }

    /**
     * The method sets the game environment.
     *
     * @param ballsGameEnvironment the game environment
     */

    public void setGameEnvironment(GameEnvironment ballsGameEnvironment) {
        this.gameEnvironment = ballsGameEnvironment;
    }
    // draw the ball on the given DrawSurface

    /**
     * The method draws the ball.
     *
     * @param surface the surface to draw on
     */
    public void drawOn(DrawSurface surface) {
        //set the color
        surface.setColor(getColor());
        // draw the ball on the given DrawSurface
        surface.fillCircle((int) getX(), (int) getY(), getSize());
        DrawCircle circle  = new DrawCircle(getSize(), center, Color.BLACK);
        circle.drawOn(surface);
    }

    @Override
    public void timePassed() {

        moveOneStep();
    }

    /**
     * The method adds the ball to the game.
     *
     * @param g the game
     */

    public void addToGame(GameLevel g) {
        this.ourGame = g;
        g.addSprite(this);
    }

    /**
     * The method gets the velocity.
     *
     * @param v the surface to draw on
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }


    /**
     * The method sets the velocity.
     *
     * @param dx the change on the X axis
     * @param dy the change on the Y axis
     */
    public void setVelocity2(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }

    /**
     * The method gets the velocity.
     *
     * @return velocity the velocity
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * The method moves the point.
     */
    public void moveOneStep() {
        //get the next point from our center
        Point nextPoint = this.getVelocity().applyToPoint(this.center);
        //calculate the line trajectory
        Line trajectory = new Line(this.center, nextPoint);
        //get the collision info for the trajectory
        CollisionInfo currentCollisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        //if we didnt collide with anything
        if (currentCollisionInfo == null) {
            this.center = nextPoint;
            //if not
        } else {
            //initialize a new velocity with the hit point and the velocity
            Velocity newVelocity = currentCollisionInfo.collisionObject().hit(this,
                    currentCollisionInfo.collisionPoint(), this.velocity);
            //if the new velocity is the same as the current velocity ie: the hit points are the same
            if (newVelocity.getDx() == getVelocity().getDx() && newVelocity.getDy() == getVelocity().getDy()) {
                //the center is the current x but we make the y earlier
                this.center = new Point(center.getX(), center.getY() - MODIFYYSOWONTGETSTUCK);
            }
            //set the new velocity
            this.setVelocity(newVelocity);
        }
    }

    /**
     * The method removes the ball from the game.
     * @param game the game we want the ball to be removed from
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}