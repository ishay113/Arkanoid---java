package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprite.BackGround;

import java.util.ArrayList;
import java.util.List;

/**
 * Menu Animation - run animation by choosing options.
 *
 * @param <T> is a generic type.
 */
public class MenuAnimation<T> implements Menu<T> {
    private String gameName;
    private List<String> keyList;
    private List<String> messageList;
    private List<T> returnValList;
    private KeyboardSensor keyboardSensor;
    private boolean stop;
    private boolean isAlreadyPressed;
    private BackGround backGround;

    /**
     * constructor with string game name, key sensor, and background.
     *
     * @param gameName       is the name of the game.
     * @param keyboardSensor is the key sensor.
     * @param backGround     is the background.
     */
    public MenuAnimation(String gameName, KeyboardSensor keyboardSensor, BackGround backGround) {
        this.gameName = gameName;
        keyList = new ArrayList<>();
        messageList = new ArrayList<>();
        returnValList = new ArrayList<T>();
        this.keyboardSensor = keyboardSensor;
        this.stop = false;
        this.isAlreadyPressed = true;
        this.backGround = backGround;
    }

    @Override
    public void addSelection(String key, String message, T returnVal) {
        this.keyList.add(key);
        this.messageList.add(message);
        this.returnValList.add(returnVal);

    }

    @Override
    public T getStatus() {
        for (String k : keyList) {
            if (keyboardSensor.isPressed(k)) {
                int index = keyList.indexOf(k);
                return returnValList.get(index);
            }
        }
        return null;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        backGround.drawOn(d);
        for (int i = 0; i < keyList.size(); i++) {
            d.drawText(d.getWidth() / 20, d.getHeight() / 3 + 100 * i, messageList.get(i), 50);
        }
        for (int i = 0; i < keyList.size(); i++) {
            if (keyboardSensor.isPressed(keyList.get(i))) {
                this.stop = true;
                isAlreadyPressed = false;
            }
        }


    }

    @Override
    public boolean shouldStop() {
        if (this.stop && !isAlreadyPressed) {
            isAlreadyPressed = true;
            return true;
        }
        return false;

    }
}
