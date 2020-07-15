package scores;

import collidable.Block;
import hitlisteners.HitListener;
import sprites.Ball;
import sprites.Sprite;
import biuoop.DrawSurface;
import counter.Counter;
import java.awt.Color;

/**
 * The scores.ScoreIndicator class.
 */
public class ScoreIndicator implements Sprite, HitListener {
    private Counter scoresCounter;
    /**
     * The scores.ScoreIndicator constructor.
     * @param score the score
     */
    public ScoreIndicator(Counter score) {
        this.scoresCounter = score;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(300, 17, "Score: " + (this.scoresCounter.getValue()),
                15);
    }
    @Override
    public void timePassed() {
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
    }
}
