package geometry;

import java.util.Arrays;
import java.util.List;

/**
 * This class is given to you as an outline for testing your code. You can modify this as you want, but please keep in
 * mind that the lines already provided here as expected to work exactly as they are.
 *
 * @author Ritwik Banerjee
 */
public class GeometryTest {

    public static void main(String... args) {
        testRadialGraphSymmetries();
        testSquareSymmetries();
    }

    private static void testRadialGraphSymmetries() {

        Point center = new Point("center", 0, 0);
        Point east = new Point("east", 3, 4);
        Point west = new Point("west", -3, 4);
        Point north = new Point("north", 3, -4);
        Point south = new Point("south", -3, -4);

        RadialGraph g1 = new RadialGraph(center, Arrays.asList(north, south, east, west));
        System.out.println(g1);
        RadialGraph g2 = g1.rotateBy(45);
        System.out.println(g2);
        RadialGraph g3 = g1.rotateBy(360);
        System.out.println(g3);
        RadialGraph g4 = g1.rotateBy(180);
        System.out.println(g4);

        RadialGraphSymmetries graphSymmetries = new RadialGraphSymmetries();
        System.out.println(graphSymmetries.areSymmetric(g1, g2)); // must return false
        System.out.println(graphSymmetries.areSymmetric(g1, g3)); // must return true
        System.out.println(graphSymmetries.areSymmetric(g3, g4)); // must return true

        // obtain all the symmetries (including the identity) of g1, and print them one by one (remember that printing
        // will give the string representation of each radial graph, which must follow the specification of Shape's
        // toString() method)
        List<RadialGraph> symmetries = graphSymmetries.symmetriesOf(g1);
        for (RadialGraph g : symmetries) System.out.println(g);
    }

    private static void testSquareSymmetries() {
        Square sq1 = new Square(new Point("upper-right", 1, 1), new Point("upper-left", 0, 1),
                new Point("lower-left", 0, 0), new Point("lower-right", 1, 0));
        System.out.println(sq1);
        Square sq2 = sq1.rotateBy(30);
        System.out.println(sq2.toString());
        Square sq3 = sq1.rotateBy(180);
        System.out.println(sq3.toString());


        SquareSymmetries squareSymmetries = new SquareSymmetries();
        System.out.println(squareSymmetries.areSymmetric(sq1, sq2)); // must return false
        System.out.println(squareSymmetries.areSymmetric(sq1, sq3)); // must return true

        // obtain all the 8 symmetries (including the identity) of sq1, and print them one by one (remember that printing
        // will give the string representation of each square, which must follow the specification of Shape's toString()
        // method)
        List<Square> symmetries = squareSymmetries.symmetriesOf(sq1);
        for (Square s : symmetries) System.out.println(s);


    }

}
