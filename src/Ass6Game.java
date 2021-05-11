import animations.AnimationRunner;
import biuoop.GUI;
import game.GameFlow;
import levels.Level1;
import levels.Level2;
import levels.Level3;
import levels.Level4;
import levels.LevelInformation;

import java.util.ArrayList;
import java.util.List;

/**
 * The class initialize and run level games, according to the arguments.
 */
public class Ass6Game {
    /**
     * the main we run the program.
     *
     * @param args are the arguments from the commandline
     */
    public static void main(String[] args) {
        // if there are not arguments, then start a game with the default levels.
        if (args.length == 0) {
            startGame();
            return;
        }
        // create a list of integers
        List<Integer> numberList = new ArrayList<>();
        // loop for each element in args
        for (String s : args) {
            // add all the integers to numberList
            try {
                int x = Integer.parseInt(s);
                numberList.add(x);
            } catch (NumberFormatException e) {
            }
        }
        // if the list is empty, start the game with the default levels.
        if (numberList.size() == 0) {
            startGame();
        }
        // start the game with the levels we get by the arguments
        startGame(listFromArray(numberList));

    }

    /**
     * start the game with default levels.
     */
    private static void startGame() {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor(), gui);
        List<LevelInformation> level = new ArrayList<>();
        level.add(new Level1());
        level.add(new Level2());
        level.add(new Level3());
        level.add(new Level4());
        gameFlow.runLevels(level);
    }

    /**
     * start the game with specifics levels.
     *
     * @param levels is a list of levelInformation.
     */
    private static void startGame(List<LevelInformation> levels) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor(), gui);
        gameFlow.runLevels(levels);
    }

    /**
     * this method get a list of integers and returns list of levelInformation
     * according to the integers (if there are).
     *
     * @param list a list of integers
     * @return a list of levelInformation
     */
    private static List<LevelInformation> listFromArray(List<Integer> list) {
        List<LevelInformation> levelList = new ArrayList<>();
        for (Integer x : list) {
            // if the level exist, add it to the levelList
            if (getLevelFromNumber(x) != null) {
                levelList.add(getLevelFromNumber(x));
            }
        }
        return levelList;
    }

    /**
     * get level from number. 1 - level1, 2 - level2 and so on. if the level does not exist, return null.
     *
     * @param i the number.
     * @return the levelInformation according to the number.
     */
    private static LevelInformation getLevelFromNumber(int i) {
        switch (i) {
            case 1:
                return new Level1();
            case 2:
                return new Level2();
            case 3:
                return new Level3();
            case 4:
                return new Level4();
            default:
                return null;
        }
    }
}
