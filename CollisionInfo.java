package collisioninfo;

import collidable.Collidable;
import geommetry.Point;

/**
 * The collisioninfo.collisioninfo class.
 */
public class CollisionInfo {
    private Collidable collsionObject;
    private Point collisionPoint;
    /**
     * The method constructs the collision info.
     * @param collsionObject the collidable object
     * @param collisionPoint the collision point
     */
    public CollisionInfo(Collidable collsionObject, Point collisionPoint) {
        this.collsionObject = collsionObject;
        this.collisionPoint = collisionPoint;
    }
    /**
     * The method gives us the point at which the collision occurs.
     * @return collision point
     */

    public Point collisionPoint() {
        return this.collisionPoint;
    }
    /**
     * The method gives us the collidable object involved in the collision.
     * @return collision object
     */
    public Collidable collisionObject() {
        return this.collsionObject;
    }
}
