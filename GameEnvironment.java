package game;

import collidable.Collidable;
import collisioninfo.CollisionInfo;
import geommetry.Line;
import geommetry.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * The block class.
 */
public class GameEnvironment {
   private List<Collidable> collidables;

    /**
     * Arrange the collidables in a list.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();

    }
    /**
     * Add the given collidable to the environment.
     * @param c the collidable to add
     */
    //
    public void addCollidable(Collidable c) {
        collidables.add(c);
    }
    /**
     * Checks if a moving objesct will collide with a collidable, if it does return the closest collision point.
     * If not return Null
     * @param trajectory the line we want to check if collided with
     * @return the closest collision point
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        List<CollisionInfo> allCollisionInfos = new ArrayList<>();
        List<Collidable> collidablesList = new ArrayList<>(this.collidables);
        //run over the list we just created
        for (Collidable c : collidablesList) {
            Rectangle r = c.getCollisionRectangle();
            //see if there are collision points with our rectangle
            if (!(r.intersectionPoints(trajectory).isEmpty())) {
                //add a new collision info to the collision info list with the collidabe object and the closest point
                // on it
                allCollisionInfos.add(new CollisionInfo(c, trajectory.closestIntersectionToStartOfLine(r)));
            }
        }
        //if the list is not empty
        if (!allCollisionInfos.isEmpty()) {
            //start from the beginning of the list
            CollisionInfo closestCollisionInfo = allCollisionInfos.get(0);
            double minDistance = trajectory.start().distance(allCollisionInfos.get(0).collisionPoint());
            //search for the closest collision point
            for (int i = 1; i < allCollisionInfos.size(); i++) {
                if (allCollisionInfos.get(i).collisionPoint().distance(trajectory.start()) < minDistance) {
                    closestCollisionInfo = allCollisionInfos.get(i);
                    minDistance = allCollisionInfos.get(i).collisionPoint().distance(trajectory.start());
                }
            }
            //return the closest collision point
            return closestCollisionInfo;
        }
        return null;
    }
    /**
     * The method removes the collidable from the game.
     * @param c the collidable we want to remove
     */
    public void removeCollidable(Collidable c) {
        collidables.remove(c);
    }

}