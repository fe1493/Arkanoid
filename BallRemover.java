package hitlisteners;

import collidable.Block;
import game.GameLevel;
import sprites.Ball;
import counter.Counter;

/**
 * The hitlisteners.BallRemover class.
 * a hitlisteners.BallRemover is in charge of removing balls from the game, as well as keeping count
 *  of the number of balls that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * The hitlisteners.BallRemover constructor.
     * @param game the game we are playing
     * @param removedBalls the balls that have been removed
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //if the ball hits the death block
        if (beingHit.getHitPoints() < 1) {
            //remove it from the game
            hitter.removeFromGame(this.game);
            this.remainingBalls.decrease(1);
        }
    }
}
