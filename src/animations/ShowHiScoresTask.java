package animations;

import biuoop.KeyboardSensor;
import game.Task;

/**
 * Task - show the high score.
 */
public class ShowHiScoresTask implements Task<Void> {
    private AnimationRunner runner;
    private Animation highScoresAnimation;
    private KeyboardSensor ks;

    /**
     * constructor with animation runner, animation, and key sensor.
     *
     * @param runner              is the runner.
     * @param highScoresAnimation is the high score animation
     * @param ks                  is the KeyBoardSensor.
     */
    public ShowHiScoresTask(AnimationRunner runner, Animation highScoresAnimation, KeyboardSensor ks) {
        this.runner = runner;
        this.highScoresAnimation = highScoresAnimation;
        this.ks = ks;
    }

    /**
     * run the task.
     *
     * @return null. (Void)
     */
    public Void run() {
        KeyPressStoppableAnimation animation = new KeyPressStoppableAnimation(ks, ks.SPACE_KEY, highScoresAnimation);
        this.runner.run(animation);
        return null;
    }
}
