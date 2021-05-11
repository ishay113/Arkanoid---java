package levels;

import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.util.List;

/**
 * levels.LevelInformation contains all the important information for the level.
 */
public interface LevelInformation {
    /**
     * number of the balls that in the level.
     *
     * @return the number of the balls.
     */
    int numberOfBalls();


    /**
     * The initial velocity of each ball.
     *
     * @return list of the ball's velocities.
     */
    List<Velocity> initialBallVelocities();

    /**
     * The paddle speed.
     *
     * @return the paddle speed.
     */
    int paddleSpeed();

    /**
     * The paddle width.
     *
     * @return the paddle width.
     */
    int paddleWidth();


    /**
     * The level name.
     *
     * @return string -  the level name.
     */
    String levelName();


    /**
     * get the background of the level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();


    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return a list of  the level's blocks.
     */
    List<Block> blocks();


    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     * This number should be <= blocks.size().
     *
     * @return the number of blocks that should be removed.
     */
    int numberOfBlocksToRemove();
}
