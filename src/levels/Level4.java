package levels;

import geomatry.Point;
import geomatry.Rectangle;
import sprite.BackGround;
import sprite.Block;
import sprite.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * levels.Level 4 - "Final Four".
 */
public class Level4 extends Level implements LevelInformation {
    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final int WIDTH_OF_BORDER = 25;
    static final java.awt.Color COLOR = new Color(10, 100, 240);

    /**
     * constructor with defaults values.
     */
    public Level4() {
        super(3,
                10,
                150,
                "Final Four",
                new BackGround(new Rectangle(WIDTH_OF_BORDER, 20, WIDTH - 2 * WIDTH_OF_BORDER, HEIGHT - 20),
                        COLOR, new LevelDrawingsCloud()),
                105);
    }

    /**
     * The initial velocity of each ball.
     *
     * @return list of the ball's velocities.
     */
    public java.util.List<Velocity> initialBallVelocities() {
        java.util.List<Velocity> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            list.add(Velocity.fromAngleAndSpeed(-30 + i * 30, 7));
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
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 15; j++) {
                list.add(new Block(new Rectangle(new Point(25 + j * 50, 150 + i * 25),
                        50, 25), getColorFromNumber(i)));
            }
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
            case 5:
                return Color.RED;
            case 1:
                return Color.GREEN;
            case 2:
                return Color.pink;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.YELLOW;
            case 6:
                return Color.GRAY;
            default:
                return Color.MAGENTA;
        }
    }

}
