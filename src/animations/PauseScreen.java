package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Pause Screen is a simple animation, that will display a screen with the message paused -- press space to continue
 * until a key is pressed.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor with keyboardSensor.
     *
     * @param k is a keyboard sensor.
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * do one frame from the animation.
     * display a screen with the message paused -- press space to continue.
     *
     * @param d is the drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
        }
    }

    /**
     * this method tells us if the animation should stop or not.
     *
     * @return if the animation should stop or not.
     */
    public boolean shouldStop() {
//        return this.stop;
        return false;
    }
}
