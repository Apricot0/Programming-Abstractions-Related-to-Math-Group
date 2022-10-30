package geometry;

import java.util.*;
class SquareSymmetries implements Symmetries<Square> {
  public boolean areSymmetric(Square s1, Square s2){
    for(int i= 0; i< s1.squarePoints.size();i++){
      if((s1.squarePoints.get(i).x!=s2.squarePoints.get(i).x)||(s1.squarePoints.get(i).y!=s2.squarePoints.get(i).y)) return false;
    }
    return true;
  }
  public List<Square> symmetriesOf(Square s){
    List<Square> allSym = new ArrayList<Square>();

    //rotation 0, 90, 280, 270
    for(int degrees = 0; degrees <= 270; degrees +=90){
      allSym.add(s.rotateBy(degrees));
    }
    //reflection
    Point a = new Point(s.downRight.name,s.upRight.x, s.upRight.y);
    Point b = new Point(s.downLeft.name,s.upLeft.x, s.upLeft.y);
    Point c = new Point(s.upLeft.name,s.downLeft.x, s.downLeft.y);
    Point d = new Point(s.upRight.name,s.downRight.x, s.downRight.y);
    Square verticalRe  = new Square(a,b,c,d);
    allSym.add(verticalRe);

    a = new Point(s.upLeft.name,s.upRight.x, s.upRight.y);
    b = new Point(s.upRight.name,s.upLeft.x, s.upLeft.y);
    c = new Point(s.downRight.name,s.downLeft.x, s.downLeft.y);
    d = new Point(s.downLeft.name,s.downRight.x, s.downRight.y);
    Square horiReflec = new Square(a,b,c,d);
    allSym.add(horiReflec);

    a = new Point(s.upRight.name,s.upRight.x, s.upRight.y);
    b = new Point(s.downRight.name,s.upLeft.x, s.upLeft.y);
    c = new Point(s.downLeft.name,s.downLeft.x, s.downLeft.y);
    d = new Point(s.upLeft.name,s.downRight.x, s.downRight.y);
    Square diagReflec1 = new Square(a,b,c,d);
    allSym.add(diagReflec1);

    a = new Point(s.downLeft.name,s.upRight.x, s.upRight.y);
    b = new Point(s.upLeft.name,s.upLeft.x, s.upLeft.y);
    c = new Point(s.upRight.name,s.downLeft.x, s.downLeft.y);
    d = new Point(s.downRight.name,s.downRight.x, s.downRight.y);
    Square diagReflec2 = new Square(a,b,c,d);
    allSym.add(diagReflec2);
    
   return allSym;
  }
}