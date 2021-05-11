package game;

import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;
import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geomatry.Point;
import geomatry.Rectangle;
import levels.LevelInformation;
import levels.LevelName;
import listeners.BallRemover;
import listeners.BlockRemover;
import listeners.ScoreTrackingListener;
import sprite.BackGround;
import sprite.Ball;
import sprite.Block;
import sprite.Collidable;
import sprite.Counter;
import sprite.Paddle;
import sprite.ScoreIndicator;
import sprite.Sprite;
import sprite.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * The class initialize and run a game.
 * game has sprite collection and game environment.
 */
public class GameLevel implements Animation {

    static final int WIDTH = 800;
    static final int HEIGHT = 600;
    static final double WIDTH_OF_BORDER_BLOCK = 25;
    static final int BONUS_POINTS = 100;

    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private Counter blocksCounter;
    private Counter ballsCounter;
    private Counter score;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInformation;

    /**
     * constructor - initialize spriteCollection and gameEnvironment.
     *
     * @param level is the level information.
     */

    public GameLevel(LevelInformation level) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.score = new Counter(0);
        this.gui = new GUI("Game", WIDTH, HEIGHT);
        this.runner = new AnimationRunner(this.gui);
        this.keyboard = gui.getKeyboardSensor();
        this.levelInformation = level;
        this.running = true;
    }

    /**
     * constructor - initialize spriteCollection, gameEnvironment, score, gui, runner, keyboard, level.
     *
     * @param level      is the level information.
     * @param ar         is the animations.Animation Runner.
     * @param ks         is the KeyboardSensor.
     * @param gui        is the gui for the game.
     * @param startScore is the start score of the game.
     */
    public GameLevel(LevelInformation level, AnimationRunner ar, KeyboardSensor ks, GUI gui, int startScore) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.score = new Counter(startScore);
        this.gui = gui;
        this.runner = ar;
        this.keyboard = ks;
        this.levelInformation = level;
        this.running = true;
    }

    /**
     * add collidable object to the game.
     *
     * @param c is a collidable object
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * add sprite object to the game.
     *
     * @param s is a sprite object
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and sprite.Ball and sprite.Paddle and add them to the game.
     */
    public void initialize() {


        this.environment.setBorderFromLeft(WIDTH_OF_BORDER_BLOCK);
        this.environment.setBorderFromRight(WIDTH - WIDTH_OF_BORDER_BLOCK);
        // set counters
        setBlocksCounter();
        setBallsCounter();
        // initialize all the objects to the game
        initializeBackgroundToTheGame();
        initializeBorderBlocksToTheGame();
        initializeDeathRegionBlockToTheGame();
        initializeBlocksToTheGame();
        initializePaddleToTheGame();
        initializeBallsToTheGame();
        initializeScoreIndicator();
        initializeLevelName();
    }

    /**
     * initialized the levelName to the game.
     */
    private void initializeLevelName() {
        LevelName levelName = new LevelName(this.levelInformation.levelName(), WIDTH, 15, 15);
        levelName.addToGame(this);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        CountdownAnimation countdownAnimation = new CountdownAnimation(2, 3, this.sprites.clone());
        this.runner.run(countdownAnimation, countdownAnimation.getNumOfSeconds(), 3);
        this.running = true;
        // use our runner to run the current animation -- which is one turn of the game.
        this.runner.run(this);
    }

    /**
     * create a blocks border list for the game.
     *
     * @return block border list
     */
    private List<Block> createBorderBlocks() {
        List<Block> list = new ArrayList<>();
        // create the border blocks
        list.add(new Block(new Rectangle(new Point(0, 0), WIDTH_OF_BORDER_BLOCK, HEIGHT), Color.gray));
        list.add(new Block(new Rectangle(
                new Point(WIDTH_OF_BORDER_BLOCK, 20),
                WIDTH - 2 * WIDTH_OF_BORDER_BLOCK, 20), Color.gray));
        list.add(new Block(new Rectangle(
                new Point(WIDTH - WIDTH_OF_BORDER_BLOCK, 0),
                WIDTH_OF_BORDER_BLOCK, HEIGHT), Color.gray));
        return list;
    }

    /**
     * initialize borders to the game.
     */
    private void initializeBorderBlocksToTheGame() {
        List<Block> list = createBorderBlocks();
        for (Block block : list) {
            block.addToGame(this);
        }
    }

    /**
     * initialize a death-region-block to the game.
     */
    private void initializeDeathRegionBlockToTheGame() {
        Block deathRegion = new Block(new Rectangle(
                new Point(WIDTH_OF_BORDER_BLOCK, HEIGHT),
                WIDTH - 2 * WIDTH_OF_BORDER_BLOCK, 20), Color.cyan);
        BallRemover ballRemover = new BallRemover(this, this.ballsCounter);
        deathRegion.addToGame(this);
        // add the hitListener ballRemover tho the death-region-block
        deathRegion.addHitListener(ballRemover);
    }

    /**
     * initialize a scoreIndicator to the game.
     */
    private void initializeScoreIndicator() {
        ScoreIndicator scoreIndicator = new ScoreIndicator(this.score, WIDTH, 20);
        scoreIndicator.addToGame(this);
    }

    /**
     * create a ball list for the game.
     *
     * @return ball list
     */
    private List<Ball> createBallList() {
        List<Ball> ballList = new ArrayList<>();
        for (int i = 0; i < levelInformation.numberOfBalls(); i++) {
            ballList.add(new Ball(WIDTH / 2, 570, 5, getColorFromNumber(i)));
            ballList.get(i).setVelocity(this.levelInformation.initialBallVelocities().get(i));
            ballList.get(i).setGm(this.environment);
        }

        return ballList;
    }

    /**
     * initialize balls to the game.
     */
    private void initializeBallsToTheGame() {
        List<Ball> ballList = createBallList();
        for (Ball ball : ballList) {
            ball.addToGame(this);
        }
    }

    /**
     * initialize blocks to the game.
     */
    private void initializeBlocksToTheGame() {
        List<Block> list = this.levelInformation.blocks();
        BlockRemover blockRemover = new BlockRemover(this, this.blocksCounter);
        ScoreTrackingListener scoreTrackingListener = new ScoreTrackingListener(this.score);
        for (Block block : list) {
            block.addToGame(this);
            // add Listeners to the each block
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
        }
    }

    /**
     * initialize background to the game.
     */
    private void initializeBackgroundToTheGame() {
        BackGround backGround = (BackGround) this.levelInformation.getBackground();
        backGround.addToGame(this);
    }

    /**
     * initialize paddle to the game.
     */
    private void initializePaddleToTheGame() {
        biuoop.KeyboardSensor keyboard1 = gui.getKeyboardSensor();
        Paddle paddle = new Paddle(new Block(
                (WIDTH - this.levelInformation.paddleWidth()) / 2,
                580, this.levelInformation.paddleWidth(), 10, Color.ORANGE),
                keyboard1, this.levelInformation.paddleSpeed());
        paddle.setGm(this.environment);
        paddle.addToGame(this);
    }

    /**
     * get color from a number.
     *
     * @param i is the number that according this number we return the color.
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

    /**
     * remove a collidable object from the game.
     *
     * @param c is a collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove a sprite.Sprite object from the game.
     *
     * @param s is a sprite.Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * set the block sprite.Counter for the game with the number of the blocks.
     */
    private void setBlocksCounter() {
//        List<sprite.Block> list = createBlockList();
        this.blocksCounter = new Counter(this.levelInformation.numberOfBlocksToRemove());
    }

    /**
     * set the ball sprite.Counter for the game with the number of the balls.
     */
    private void setBallsCounter() {
        this.ballsCounter = new Counter(this.levelInformation.numberOfBalls());
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        if (this.blocksCounter.getValue() == 0) {
            score.increase(BONUS_POINTS);
            this.running = false;
        }
        if (this.ballsCounter.getValue() == 0) {
            this.running = false;
        }
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(
                    this.keyboard, keyboard.SPACE_KEY, new PauseScreen(this.keyboard)));
        }
    }

    /**
     * get the final score.
     *
     * @return the final score.
     */
    public int getFinalScore() {
        return this.score.getValue();
    }

    /**
     * boolean method - is balls left in the level.
     * this helps to know when the player lost.
     *
     * @return true if balls left and false in no balls left.
     */
    public boolean isBallsLeft() {
        return this.ballsCounter.getValue() != 0;
    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }
}
