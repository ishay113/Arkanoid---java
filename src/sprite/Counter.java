package sprite;

/**
 * the class count objects or any type of variables.
 */
public class Counter {
    // the value of the count
    private int value;

    /**
     * counter with a number.
     *
     * @param number is the initialized number of the count.
     */
    public Counter(int number) {
        this.value = number;
    }


    /**
     * add number to current count.
     *
     * @param number is the number we add to the count
     */
    public void increase(int number) {
        this.value += number;
    }


    /**
     * subtract number from current count.
     *
     * @param number is the number we decrease from the count.
     */
    public void decrease(int number) {
        this.value -= number;
    }


    /**
     * get current count.
     *
     * @return the value of the count.
     */
    public int getValue() {
        return this.value;
    }
}
