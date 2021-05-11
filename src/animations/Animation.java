package animations;

import biuoop.DrawSurface;

/**
 * Animations objects, implement this interface.
 */
public interface Animation {
    /**
     * do one frame from the animation.
     *
     * @param d is the drawSurface
     */
    void doOneFrame(DrawSurface d);

    /**
     * this method tells us if the animation should stop or not.
     *
     * @return if the animation should stop or not.
     */
    boolean shouldStop();
}
