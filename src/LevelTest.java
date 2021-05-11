import animations.AnimationRunner;
import biuoop.GUI;
import files.LevelSpecificationReader;
import game.GameFlow;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class LevelTest {

    public static void main(String[] args) throws IOException {
        if (args.length != 0) {
            readFromPath(args[0]);
            return;
        }
        File file = new File("src\\resources\\definitions\\hard_level_definitions.txt");
//        InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("highscores.txt");
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        boolean t = file.exists();
        LevelSpecificationReader lr = new LevelSpecificationReader();
        Reader reader = new InputStreamReader(new FileInputStream(file));
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor(), gui);
        gameFlow.runLevels(lr.fromReader(reader));
    }

    public static void readFromPath(String path) throws IOException {
        File file = new File(path);
        boolean t = file.exists();
        LevelSpecificationReader lr = new LevelSpecificationReader();
        Reader reader = new InputStreamReader(new FileInputStream(file));
        GUI gui = new GUI("Arkanoid", 800, 600);
        AnimationRunner ar = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(ar, gui.getKeyboardSensor(), gui);
        gameFlow.runLevels(lr.fromReader(reader));
    }
}
