import geometry.*;
import core.*;
import java.util.*;
class Main {
  public static void main(String[] args) {
             Square sq1 = new Square(new Point("upper-right", 1, 1), new Point("upper-left", 0, 1),
                                new Point("lower-left", 0, 0), new Point("lower-right", 1, 0));
        Square sq2 = sq1.rotateBy(30);
        Square sq3 = sq1.rotateBy(180);

        SquareSymmetries squareSymmetries = new SquareSymmetries();
        squareSymmetries.areSymmetric(sq1, sq2); // must return false
        squareSymmetries.areSymmetric(sq1, sq3); // must return true

        // obtain all the 8 symmetries (including the identity) of sq1, and print them one by one (remember that printing
        // will give the string representation of each square, which must follow the specification of Shape's toString()
        // method)
        List<Square> symmetries = squareSymmetries.symmetriesOf(sq1);
        for (Square s : symmetries) System.out.println(s);
  }
}