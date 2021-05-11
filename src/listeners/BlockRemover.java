package listeners;

import game.GameLevel;
import sprite.Ball;
import sprite.Block;
import sprite.Counter;

/**
 * The class removes blocks from the game when a hit event is notify.
 */
public class BlockRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * constructor with game and counter.
     *
     * @param gameLevel          is the game
     * @param removedBlocks is the counter that count the blocks of the game.
     */
    public BlockRemover(GameLevel gameLevel, Counter removedBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = removedBlocks;
    }


    /**
     * This method is called whenever the beingHit object is hit.
     * The method removes the blocks that are hit from the game.
     *
     * @param beingHit the block is being hit.
     * @param hitter   the sprite.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        beingHit.removeFromGame(this.gameLevel);
        this.remainingBlocks.decrease(1);

    }
}
