package counter;

/**
 * The counter class.
 */
public class Counter {
    private int count;
    /**
     * The counter.counter constructor.
     * @param value the current value of the counter
     */
    public Counter(int value) {
        this.count = value;
    }
    /**
     * Add number to current count.
     * @param number the number we want to add
     */
    public void increase(int number) {
        count = count + number;
    }
    /**
     * Subtract number from current count.
     * @param number the number we want to subtract
     */
    public void decrease(int number) {
        count = count - number;
    }
    /**
     * Get current count..
     * @return  the current count
     */
    public int getValue() {
        return this.count;
    }
}
