package arithmetic;

import core.Group;

public class ZPlus implements Group<Integer> {

    @Override
    public Integer binaryOperation(Integer x, Integer y) {
        return x + y;
    }

    @Override
    public Integer identity() {
        return 0;
    }

    @Override
    public Integer inverseOf(Integer x) {
        return -x;
    }

    @Override
    public Integer exponent(Integer x, int k) {
        return x * k;
    }
}
