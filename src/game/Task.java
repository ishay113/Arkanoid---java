package game;

/**
 * interface Task<T> - must implements the method run.
 *
 * @param <T> is a generic type.
 */
public interface Task<T> {
    /**
     * run the task.
     *
     * @return T a generic type.
     */
    T run();
}
