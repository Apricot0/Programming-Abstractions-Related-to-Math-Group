package geometry;

import java.util.*;
class SquareSymmetries implements Symmetries<Square> {
  public boolean areSymmetric(Square s1, Square s2){
    return true;
  }
  public List<Square> symmetriesOf(Square s){
    List<Square> allSym = new ArrayList<Square>();

    //rotation 0, 90, 280, 270
    for(int degrees = 0; degrees <= 270; degrees +=90){
      allSym.add(s.rotateBy(degrees));
    }
    //qreflection
    Square verticalRe  = new Square(s.downRight,s.downLeft, s.upLeft, s.upRight);
    allSym.add(verticalRe);
    Square horiReflec = new Square(s.upLeft,s.upRight,s.downRight,s.downLeft);
    allSym.add(horiReflec);
    Square diagReflec1 = new Square(s.upRight, s.downRight, s.downLeft, s.upLeft);
    allSym.add(diagReflec1);
    Square diagReflec2 = new Square(s.downLeft, s.upLeft, s.upRight, s.downRight);
    allSym.add(diagReflec2);
    
   return allSym;
  }
}