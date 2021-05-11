package listeners;

import sprite.Ball;
import sprite.Block;
import sprite.Counter;

/**
 * listeners.ScoreTrackingListener is a listeners.HitListener
 * to update this counter when blocks are being hit and removed.
 */
public class ScoreTrackingListener implements HitListener {
    static final int HIT_POINTS = 5;
    // current score counter
    private Counter currentScore;

    /**
     * constructor with counter.
     *
     * @param scoreCounter is the score counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprite.Ball that's doing the hitting.
     * every hit add 5 points to the score.
     *
     * @param beingHit is the block that being hit.
     * @param hitter   the sprite.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeHitListener(this);
        this.currentScore.increase(HIT_POINTS);
    }
}
