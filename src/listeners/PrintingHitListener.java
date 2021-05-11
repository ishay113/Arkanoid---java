package listeners;

import sprite.Ball;
import sprite.Block;

/**
 * listeners.HitListener to test the hitEvent.
 */
public class PrintingHitListener implements HitListener {
    /**
     * every block that being hit print "A sprite.Block was hit.".
     *
     * @param beingHit is the block that being hit.
     * @param hitter   the sprite.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A sprite.Block was hit.");
    }
}
