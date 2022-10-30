package arithmetic;

public class PlusOrMinusOne {
    private int value;

    /**
     * Use x to construct PlusOrMinusOne, x can only be -1 or 1
     *
     * @param x
     */
    public PlusOrMinusOne(int x) {
        if (!(x == -1 || x == 1)) throw new IllegalArgumentException();
        value = x;
    }

    /**
     * Get the value
     *
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * Set the value
     *
     * @param value the value that going to set
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return all the values in an array which are -1 and 1
     */
    static public PlusOrMinusOne[] values() {
        PlusOrMinusOne[] set = new PlusOrMinusOne[2];
        PlusOrMinusOne one = new PlusOrMinusOne(1);
        PlusOrMinusOne neg_one = new PlusOrMinusOne(-1);
        set[0] = one;
        set[1] = neg_one;
        return set;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}