package arithmetic;

public class PlusOrMinusOne {
    private int value;
    public PlusOrMinusOne(int x){
        if ( !(x == -1 || x == 1)) throw new IllegalArgumentException();
        value = x;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    static public PlusOrMinusOne[] values(){
        PlusOrMinusOne[] set = new PlusOrMinusOne[2];
        PlusOrMinusOne one = new PlusOrMinusOne(1);
        PlusOrMinusOne neg_one = new PlusOrMinusOne(-1);
        set[0]=one;
        set[1]=neg_one;
        return set;
    }

    @Override
    public String toString() {
        return Integer.toString(value);
    }
}