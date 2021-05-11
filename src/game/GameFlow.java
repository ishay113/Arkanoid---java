package game;

import animations.AnimationRunner;
import animations.EndScreen;
import animations.HighScoreAnimation;
import animations.KeyPressStoppableAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import files.ScoreFile;
import levels.LevelInformation;

import java.util.List;

/**
 * this class run the levels in flow.
 */
public class GameFlow {
    private KeyboardSensor ks;
    private GUI gui;
    private AnimationRunner ar;

    /**
     * constructor with animations.AnimationRunner, KeyboardSensor and GUI.
     *
     * @param ar  is the animations.AnimationRunner.
     * @param ks  is the KeyboardSensor.
     * @param gui is the GUI.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.gui = gui;
        this.ks = ks;
    }

    /**
     * run the levels.
     *
     * @param levels is the list of the levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        int startScore = 0;
        // loop for each levelInfo in the list levels
        for (LevelInformation levelInfo : levels) {

            GameLevel level = new GameLevel(levelInfo, this.ar, this.ks, this.gui, startScore);

            level.initialize();

            while (!level.shouldStop()) {
                level.run();
            }
            // get the final score
            startScore = level.getFinalScore();

            // if no balls left, then run game over screen.
            if (!level.isBallsLeft()) {
                ar.run(new KeyPressStoppableAnimation(ks, ks.SPACE_KEY, new EndScreen(ks, false, startScore)));
                ScoreFile scoreFile = new ScoreFile("highscores.txt", startScore);
                scoreFile.createScoreFile();
                ar.run(new KeyPressStoppableAnimation(ks, ks.SPACE_KEY, new HighScoreAnimation(
                        "highscores.txt", gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight())));
//                gui.close();
                return;
            }
        }
        // if the player wins, run the end screen 'you win'
        ar.run(new KeyPressStoppableAnimation(ks, ks.SPACE_KEY, new EndScreen(ks, true, startScore)));
        ScoreFile scoreFile = new ScoreFile("highscores.txt", startScore);
        scoreFile.createScoreFile();
        ar.run(new KeyPressStoppableAnimation(ks, ks.SPACE_KEY, new HighScoreAnimation(
                "highscores.txt", gui.getDrawSurface().getWidth(), gui.getDrawSurface().getHeight())));
//        gui.close();
    }
}
