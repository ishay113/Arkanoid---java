package sprite;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * The class creates sprite collection
 * sprite collection has a list of sprites objects.
 * the class supports to get the point at which the collision occurs,
 * and to get the collidable object involved in the collision.
 */
public class SpriteCollection implements Cloneable {
    // create an array list of sprites objects
    private List<Sprite> spriteList = new ArrayList<>();

    /**
     * add a sprite object to the list.
     *
     * @param s is the sprite object.
     */
    public void addSprite(Sprite s) {
        spriteList.add(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spriteListCopy = new ArrayList<>(this.spriteList);
        for (Sprite s : spriteListCopy) {
            s.timePassed();
        }
    }

    /**
     * call drawOn(d) on all sprites.
     *
     * @param d is the drawSurface.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }

    /**
     * remove sprite.Sprite object from the sprite.SpriteCollection.
     *
     * @param s is the sprite.Sprite object.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * make a copy of spriteCollection.
     *
     * @return copy of spriteCollection.
     */
    public SpriteCollection clone() {
        SpriteCollection copy = new SpriteCollection();
        List<Sprite> spriteListCopy = new ArrayList<>(this.spriteList);
        for (Sprite s : spriteListCopy) {
            copy.addSprite(s);
        }
        return copy;

    }
}
