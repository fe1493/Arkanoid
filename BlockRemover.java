package hitlisteners;

import collidable.Block;
import sprites.Ball;
import game.GameLevel;
import counter.Counter;

/**
 * The hitlisteners.BlockRemover class.
 * a hitlisteners.BlockRemover is in charge of removing blocks from the game, as well as keeping count
 *  of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;
    /**
     * The hitlisteners.BlockRemover constructor.
     * @param game the game we are playing
     * @param removedBlocks the blocks that have been removed
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            this.remainingBlocks.decrease(1);
        }
    }
}