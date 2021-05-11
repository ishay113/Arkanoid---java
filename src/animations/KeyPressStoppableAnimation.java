package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * animations.KeyPressStoppableAnimation is a decorator-class that will wrap an existing animation
 * and add a "waiting-for-key" behavior to it.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * constructor with sensor, key and animation.
     *
     * @param sensor    is the KeyboardSensor.
     * @param key       is the key, presented as a string.
     * @param animation is the animation.
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.key = key;
        this.ks = sensor;
        this.stop = false;
        this.isAlreadyPressed = true;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        // invoke the method doOneFrame of the animation
        this.animation.doOneFrame(d);
        // if the key is pressed and it was not press before, change the stop to true.
        if (this.ks.isPressed(this.key) && !isAlreadyPressed) {
            this.stop = true;
        }
        if (!this.ks.isPressed(this.key)) {
            isAlreadyPressed = false;
        }

    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
