package levels;

import geomatry.Rectangle;
import sprite.BackGround;
import sprite.Block;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * levels.Level 1 - "Direct Hit".
 */
public class Level1 extends Level implements LevelInformation {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int WIDTH_OF_BORDER = 25;
    static final int WIDTH_OF_BLOCK = 30;
    static final int HEIGHT_OF_BLOCK = 30;

    /**
     * constructor with defaults values.
     */
    public Level1() {
        super(1,
                10,
                100,
                "Direct Hit",
                new BackGround(new Rectangle(
                        WIDTH_OF_BORDER, 20, WIDTH - 2 * WIDTH_OF_BORDER, HEIGHT - 20),
                        Color.black, new LevelDrawingsTarget()),
                1);
    }

    /**
     * The initial velocity of each ball.
     *
     * @return list of the ball's velocities.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> list = new ArrayList<>();
        list.add(new Velocity(0, -5));
        return list;
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return a list of  the level's blocks.
     */
    public List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        list.add(new Block(
                (WIDTH - 2 * WIDTH_OF_BORDER) / 2, HEIGHT / 4, WIDTH_OF_BLOCK, HEIGHT_OF_BLOCK, Color.red));
        return list;
    }
}
