package animations;

import biuoop.DrawSurface;
import files.ScoreFile;
import geomatry.Rectangle;
import levels.LevelDrawingsSun;
import sprite.BackGround;

import java.awt.Color;

/**
 * HighScore Animation - show the high score.
 */
public class HighScoreAnimation implements Animation {
    private int score;
    private String fileName;
    private BackGround backGround;

    /**
     * constructor with the file name, with of the gui and the height of the gui.
     *
     * @param fileName is the file name.
     * @param width    the width of the gui.
     * @param height   the height of the gui.
     */
    public HighScoreAnimation(String fileName, int width, int height) {
        this.fileName = fileName;
        this.backGround = new BackGround(new Rectangle(0, 0, width, height), Color.blue, new LevelDrawingsSun());
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        backGround.drawOn(d);
        ScoreFile scoreFile = new ScoreFile(fileName);
        this.score = scoreFile.getScoreFromFile();
        d.drawText(d.getWidth() / 20, d.getHeight() / 2, "The High Score Is: " + score, 50);
    }

    @Override
    public boolean shouldStop() {
        return false;
    }
}
