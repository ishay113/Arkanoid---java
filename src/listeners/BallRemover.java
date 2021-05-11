package listeners;

import game.GameLevel;
import sprite.Ball;
import sprite.Block;
import sprite.Counter;

/**
 * The class removes balls from the game when a hit event is notify.
 */
public class BallRemover implements HitListener {
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * constructor with game and counter.
     *
     * @param gameLevel         is the game.
     * @param removedBalls is the counter that count the balls of the games.
     */
    public BallRemover(GameLevel gameLevel, Counter removedBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = removedBalls;
    }


    /**
     * This method is called whenever the beingHit object is hit.
     * this method removes the hitting ball from the game, when the ball hit the 'death' regions.
     *
     * @param beingHit the block is being hit.
     * @param hitter   the sprite.Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {

        hitter.removeFromGame(this.gameLevel);
        // decrease the number of the game's balls
        this.remainingBalls.decrease(1);

    }
}
