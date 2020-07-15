package collidable;

import geommetry.Point;
import geommetry.Rectangle;
import sprites.Ball;
import sprites.Velocity;

/**
 * The collidable interface.
 */
public interface Collidable {
    /**
     * @return  the "collision shape" of the object.
     */
    Rectangle getCollisionRectangle();
    /**
     *Notify the object that we collided with it at collisionPoint with a given velocity.
     * @param collisionPoint the collision point
     * @param currentVelocity the current velocity
     * @param hitter the ball hitter
     *@return  the new velocity expected after the hit (based on he force the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}