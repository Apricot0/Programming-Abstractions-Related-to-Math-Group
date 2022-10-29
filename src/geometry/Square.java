package geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Square extends Shape {

    Point center;
    Point downRight;
    Point downLeft;
    Point upLeft;
    Point upRight;

    List<Point> squarePoints;
    @Override
    public Point center() {
        return center;
    }

    @Override
    public Square rotateBy(int degrees) {
        double newDegrees = Math.toRadians(degrees);
        if (squarePoints==null){
            throw new RuntimeException("Invalid Square");
        }

        List<Point> newPoints = new ArrayList<Point>();
        for (Point p: squarePoints){
            double newX, newY;
            newX =((p.x-center.x)*Math.cos(newDegrees)-(p.y-center.y)*Math.sin(newDegrees))+center.x;
            newY =((p.x-center.x)*Math.sin(newDegrees)+(p.y-center.y)*Math.cos(newDegrees))+center.y;
            newX = (double)Math.round((newX*10)/10);
            newY = (double)Math.round((newY*10)/10);
            newPoints.add(new Point(p.name,newX,newY));
        }
        return new Square(newPoints.get(0),newPoints.get(1),newPoints.get(2),newPoints.get(3));
    }

    @Override
    public Shape translateBy(double x, double y) {
        if (squarePoints==null){
            throw new RuntimeException("Invalid Square");
        }

        List<Point> newPoints = new ArrayList<Point>();
        for (Point p: squarePoints){
            double newX, newY;
            newX = p.x + x;
            newY = p.y + y;
            newPoints.add(new Point(p.name,newX,newY));
        }
        return new Square(newPoints.get(0),newPoints.get(1),newPoints.get(2),newPoints.get(3));
    }

    @Override
    public String toString() {
        String output = "[";
        if(squarePoints!=null) {
            for (int i = 0; i < squarePoints.size(); i++) {
                output += "; ";
                output += squarePoints.get(i).toString();
            }
        }
        output+="]";
        return output;
    }

    public Square(Point a, Point b, Point c, Point d) {
        upRight=a;
        upLeft=b;
        downLeft=c;
        downRight=d;
        squarePoints = Arrays.asList(upRight,upLeft,downLeft,downRight);
        if (!(Distance(a,b)==Distance(b,c) && Distance(b,c) ==Distance(c,d) && Distance(c,d) == Distance(d,a) && Distance(d,a)==Distance(a,d))){
            throw new IllegalArgumentException ("Not every edges are equal!");
        }
        if ( a.equals(b)||b.equals(c)||c.equals(d)||d.equals(a)){
            throw new IllegalArgumentException("Can not have two same points!");
        }
        if (!verify90(a,b,d)){
            throw new IllegalArgumentException("Please make sure every angle is 90 degree");
        }
        double xOfCenter = (downLeft.x+upRight.x)/2;
        double yofCenter = (downLeft.y+upRight.y)/2;
        center = new Point ("center",xOfCenter,yofCenter);
    }
    private static double Distance (Point a, Point b){
        return Math.sqrt( (a.x-b.x)*(a.x-b.x) + (a.y-b.y)*(a.y-b.y));
    }

    private static boolean verify90(Point a, Point b, Point c){
        //move sub graph a b c to b = (0,0) so:
        Point newA = new Point (a.name, a.x-b.x, a.y-b.y);
        Point newC = new Point (c.name, c.x-b.x, c.y-b.y);
        return (newA.y==-newC.x && newA.x == newC.y);
    }


}
