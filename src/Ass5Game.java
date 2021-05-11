import game.GameLevel;
import levels.Level1;

/**
 * The class initialize and run a game.
 */
public class Ass5Game {
    /**
     * the main we run the program.
     *
     * @param args are the arguments from the commandline
     */
    public static void main(String[] args) {
        GameLevel gameLevel = new GameLevel(new Level1());
        gameLevel.initialize();
        gameLevel.run();
    }
}
