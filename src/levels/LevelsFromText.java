package levels;

import sprite.BackGround;
import sprite.Block;
import sprite.Velocity;

import java.util.List;

/**
 * create level from file.
 */
public class LevelsFromText extends Level {
    private List<Velocity> velocities;
    private List<Block> blocks;

    /**
     * constructor with number of balls, paddle speed, paddle width, level name, background, number of blocks to remove.
     *
     * @param paddleSpeed            the paddle speed.
     * @param paddleWidth            the paddle width.
     * @param levelName              is the level name
     * @param backGround             is the background.
     * @param numberOfBlocksToRemove is the number of blocks that we need to remove.
     * @param blocks                 are the blocks.
     * @param velocities             are the balls velocities.
     */
    public LevelsFromText(
            int paddleSpeed,
            int paddleWidth,
            String levelName,
            BackGround backGround,
            int numberOfBlocksToRemove,
            List<Velocity> velocities,
            List<Block> blocks) {
        super(velocities.size(), paddleSpeed, paddleWidth, levelName, backGround, numberOfBlocksToRemove);
        this.velocities = velocities;
        this.blocks = blocks;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.velocities;
    }
}
