import animations.Animation;
import animations.AnimationRunner;
import animations.HighScoreAnimation;
import animations.Menu;
import animations.MenuAnimation;
import animations.ShowHiScoresTask;
import biuoop.GUI;
import files.LevelSpecificationReader;
import game.GameFlow;
import game.QuitGame;
import game.Task;
import geomatry.Rectangle;
import levels.LevelDrawingsCloud;
import levels.LevelInformation;
import sprite.BackGround;

import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

/**
 * The class initialize and run the games, according to the arguments.
 */
public class Ass7Game {
    /**
     * the main static method.
     *
     * @param args the command line arguments.
     * @throws IOException IOException.
     */
    public static void main(String[] args) throws IOException {
        String path = getPath(args);
        GUI gui = new GUI("Arknoid", 800, 600);
        AnimationRunner runner = new AnimationRunner(gui);
        Color color = new Color(0, 153, 255);
        BackGround backGround = new BackGround(new Rectangle(
                0, 0, gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight()),
                color, new LevelDrawingsCloud());
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>("Menu Title", gui.getKeyboardSensor(), backGround);
        GameFlow game = new GameFlow(runner, gui.getKeyboardSensor(), gui);

        Animation highScore = new HighScoreAnimation(
                "highscores.txt", gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight());

        Task<Void> start = new Task<Void>() {
            private List<LevelInformation> list = (path == null) ? defaultLevelsFromFiles() : levelsFromPath(path);

            @Override
            public Void run() {
                game.runLevels(list);
                return null;
            }
        };

        // the parameters to addSelection are:
        // key to wait for, line to print, what to return
        menu.addSelection("s", "Press 's' to start a new game.", start);
        menu.addSelection("h", "Press 'h' to see the highest score.", new ShowHiScoresTask(
                runner, highScore, gui.getKeyboardSensor()));
        menu.addSelection("q", "Press 'q' to quit", new QuitGame());

        // run the menu in loop while user does not press 'q'
        while (true) {
            runner.run(menu);
            // get status and run the task
            Task<Void> task = menu.getStatus();
            task.run();
        }
    }

    /**
     * the default levels if there are no arguments.
     *
     * @return list of level information
     * @throws IOException IOException if files can't redden or write.
     */
    private static List<LevelInformation> defaultLevelsFromFiles() throws IOException {
        return levelsFromPath("src\\resources\\definitions\\hard_level_definitions.txt");
    }

    /**
     * levels with path to the file.
     *
     * @param path is the file path.
     * @return list of levelInformation.
     * @throws IOException if the file can not read.
     */
    private static List<LevelInformation> levelsFromPath(String path) throws IOException {
        File file = new File(path);
        boolean t = file.exists();
        LevelSpecificationReader lr = new LevelSpecificationReader();
        Reader reader = new InputStreamReader(new FileInputStream(file));
        return lr.fromReader(reader);

    }

    /**
     * return the path if there is one.
     *
     * @param args is the arguments from the command line.
     * @return the path if there is one, null otherwise.
     */
    private static String getPath(String[] args) {
        String path = null;
        for (String s : args) {
            if (s.contains("\\")) {
                path = s;
            }
        }
        return path;
    }
}
