package hitlisteners;

import collidable.Block;
import sprites.Ball;


/**
 * The hitlisteners.HitListener interface.
 */
public interface HitListener {
    /**
     *  This method is called whenever the beingHit object is hit.
     *  @param hitter The hitter parameter is the sprites.Ball that's doing the hitting
     * @param beingHit the block being hit
     */
    // This method is called whenever the beingHit object is hit.
    // The hitter parameter is the sprites.Ball that's doing the hitting.
    void hitEvent(Block beingHit, Ball hitter);
}
