package geometry;

public class Square extends Shape {

    Point center;
    Point downRight;
    Point downLeft;
    Point upLeft;
    Point upRight;
    @Override
    public Point center() {
        return null; // TODO: part of the assignment
    }

    @Override
    public Square rotateBy(int degrees) {
        return null; // TODO: part of the assignment
    }

    @Override
    public Shape translateBy(double x, double y) {
        return null; // TODO: part of the assignment
    }

    @Override
    public String toString() {
        return null; // TODO: part of the assignment
    }

    public Square(Point a, Point b, Point c, Point d) {
        upRight=a;
        upLeft=b;
        downLeft=c;
        downRight=d;
        if (!(Distance(a,b)==Distance(b,c) && Distance(b,c) ==Distance(c,d) && Distance(c,d) == Distance(d,a) && Distance(d,a)==Distance(a,d))){
            throw new IllegalArgumentException ("Not every edges are equal!");
        }

        double xOfCenter = (downLeft.x+upRight.x)/2;
        double yofCenter = (downLeft.y+upRight.y)/2;
        center = new Point ("center",xOfCenter,yofCenter);
    }
    private static double Distance (Point a, Point b){
        return Math.sqrt( (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }
    private static double TiltAngle (Point a, Point b) {
        double xDiff = b.x-a.x;
        double yDiff = b.y-a.y;
        double tanVal = yDiff/xDiff;
        double degree = Math.toDegrees(Math.atan(tanVal));
        if (xDiff>=0){
            if (yDiff>=0){
                return degree;
            }else{
                return 360-degree;
            }
        }else{
            if (yDiff>=0){
                return 180 - degree;
            }else{
                return 360 + degree;
            }
        }

    }


}
