package animations;

/**
 * Menu interface - every menu has to have this methods: add selection to the menu,
 * and get the status (afer the selection).
 *
 * @param <T> is a generic type - the results of the select.
 */
public interface Menu<T> extends Animation {
    /**
     * add selection to the menue.
     *
     * @param key       is the key of the press.
     * @param message   is the messege of the selection.
     * @param returnVal is the return value of the selection.
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * get the status of the selection.
     *
     * @return <T> the status of the selection.
     */
    T getStatus();
}
