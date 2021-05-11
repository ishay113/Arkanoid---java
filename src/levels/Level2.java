package levels;

import geomatry.Rectangle;
import sprite.BackGround;
import sprite.Block;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * levels.Level 2 - "Wide Easy".
 */
public class Level2 extends Level implements LevelInformation {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int WIDTH_OF_BORDER = 25;

    /**
     * constructor with defaults values.
     */
    public Level2() {
        super(10,
                10,
                500,
                "Wide Easy",
                new BackGround(new Rectangle(
                        WIDTH_OF_BORDER, 20, WIDTH - 2 * WIDTH_OF_BORDER, HEIGHT - 20),
                        Color.white, new LevelDrawingsSun()),
                15);
    }

    /**
     * The initial velocity of each ball.
     *
     * @return list of the ball's velocities.
     */
    public java.util.List<Velocity> initialBallVelocities() {
        java.util.List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(Velocity.fromAngleAndSpeed(-60 + i * 10, 10));
        }
        for (int i = 0; i < 5; i++) {
            list.add(Velocity.fromAngleAndSpeed(20 + i * 10, 10));
        }

        return list;
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return a list of  the level's blocks.
     */
    public java.util.List<Block> blocks() {
        List<Block> list = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            list.add(new Block(25 + i * 50, HEIGHT / 3, 50, 30, getColorFromNumber(i)));
        }
        return list;
    }

    /**
     * get color from a number.
     *
     * @param i is the number.
     * @return color.
     */
    private Color getColorFromNumber(int i) {
        switch (i) {
            case 0:
            case 1:
                return Color.RED;
            case 2:
            case 3:
                return Color.ORANGE;
            case 4:
            case 5:
                return Color.yellow;
            case 6:
            case 7:
            case 8:
                return Color.green;
            case 9:
            case 10:
                return Color.blue;
            case 11:
            case 12:
                return Color.pink;
            case 13:
            case 14:
                return Color.CYAN;
            default:
                return Color.MAGENTA;
        }
    }

}
