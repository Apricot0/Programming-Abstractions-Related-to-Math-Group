package arithmetic;

import core.Group;

public class FiniteGroupOfOrderTwo implements Group<PlusOrMinusOne> {
    /**
     *
     * @param one   the object that is the first argument of the binary operation.
     * @param other the other object (the second argument of the binary operation)
     *              to be combined with this object as
     *              per the group's binary operation.
     * @return one * other
     */
    @Override
    public PlusOrMinusOne binaryOperation(PlusOrMinusOne one, PlusOrMinusOne other) {
        return new PlusOrMinusOne(one.getValue() * other.getValue());
    }

    /**
     * 1 times -1 or 1 is still -1 or 1, so 1 is the identity
     * 
     * @return 1
     */
    @Override
    public PlusOrMinusOne identity() {
        return new PlusOrMinusOne(1);
    }

    /**
     * -1*-1= 1; 1*1 = 1, so -1 or 1 's inverses are themselves
     * 
     * @param plusOrMinusOne
     * @return the inverse
     */
    @Override
    public PlusOrMinusOne inverseOf(PlusOrMinusOne plusOrMinusOne) {
        return plusOrMinusOne;
    }

    /**
     *
     * @param plusOrMinusOne the group element serving as the base.
     * @param k              the integer exponent, indicating the number of times
     *                       the binary operation is applied on <code>t</code>.
     *                       The exponent must be a non-negative integer value.
     * @return the result after do binary operation on -1 or 1 k times
     */
    @Override
    public PlusOrMinusOne exponent(PlusOrMinusOne plusOrMinusOne, int k) {
        return Group.super.exponent(plusOrMinusOne, k);
    }
}
