package arithmetic;

/**
 * This class is given to you as an outline for testing your code. You can modify this as you want, but please keep in
 * mind that the lines already provided here as expected to work exactly as they are.
 *
 * @author Ritwik Banerjee
 */
public class ArithmeticTest {

    public static void main(String... args) {
        FiniteGroupOfOrderTwo g  = new FiniteGroupOfOrderTwo();
        PlusOrMinusOne[]      vs = PlusOrMinusOne.values();
        System.out.printf("g.identity() = %s%n", g.identity());
        for (PlusOrMinusOne v : vs) {
            for (PlusOrMinusOne u : vs) {
                PlusOrMinusOne e = g.binaryOperation(u, v);
                System.out.printf("%s * %s = %s%n", u.toString(), v.toString(), e.toString());
                System.out.printf("inverseOf(%s) = %s%n", e.toString(), g.inverseOf(e).toString());
            }
        }
    }
}