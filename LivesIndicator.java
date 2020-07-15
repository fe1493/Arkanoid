package hitlisteners;

import collidable.Block;
import sprites.Ball;
import sprites.Sprite;
import biuoop.DrawSurface;
import counter.Counter;

import java.awt.Color;

/**
 * The hitlisteners.LivesIndicator class.
 */
public class LivesIndicator implements Sprite, HitListener {
    private Counter livesCounter;
    /**
     * The hitlisteners.LivesIndicator constructor.
     * @param lives the amount of lives
     */
    public LivesIndicator(Counter lives) {
        this.livesCounter = lives;
    }

    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.black);
        d.drawText(50, 17, "Lives: " + (this.livesCounter.getValue()), 15);
    }
    @Override
    public void timePassed() {

    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
    }
}

