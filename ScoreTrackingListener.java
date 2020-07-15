package scores;

import collidable.Block;
import hitlisteners.HitListener;
import sprites.Ball;
import counter.Counter;

/**
 * The scores.ScoreTrackingListener class.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;
    /**
     * The scores.ScoreTrackingListener constructor.
     * @param scoreCounter the score counter
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() > 1) {
            this.currentScore.increase(5);
        }
        if (beingHit.getHitPoints() == 1) {
            this.currentScore.increase(10);
        }
    }
}