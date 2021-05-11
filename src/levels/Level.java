package levels;

import sprite.BackGround;
import sprite.Block;
import sprite.Sprite;
import sprite.Velocity;

import java.util.List;

/**
 * levels.Level implements all the method that declared in the levels.LevelInformation.
 */
public class Level implements LevelInformation {
    private int numberOfBalls;
    private int paddleSpeed;
    private int paddleWidth;
    private int numberOfBallsToRemove;
    private String levelName;
    private BackGround backGround;

    /**
     * constructor with number of balls, paddle speed, paddle width, level name, background, number of blocks to remove.
     *
     * @param numberOfBalls          the number of balls.
     * @param paddleSpeed            the paddle speed.
     * @param paddleWidth            the paddle width.
     * @param levelName              is the level name
     * @param backGround             is the background.
     * @param numberOfBlocksToRemove is the number of blocks that we need to remove.
     */
    public Level(int numberOfBalls, int paddleSpeed, int paddleWidth,
                 String levelName, BackGround backGround, int numberOfBlocksToRemove) {
        this.backGround = backGround;
        this.levelName = levelName;
        this.numberOfBalls = numberOfBalls;
        this.paddleSpeed = paddleSpeed;
        this.paddleWidth = paddleWidth;
        this.numberOfBallsToRemove = numberOfBlocksToRemove;
    }


    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return null;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.backGround;
    }

    @Override
    public List<Block> blocks() {
        return null;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBallsToRemove;
    }
}
