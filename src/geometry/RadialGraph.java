package geometry;

import java.util.*;

public class RadialGraph extends Shape {
    private Point center;
    public List<Point> neighbors;

    public RadialGraph(Point center, List<Point> neighbors) {
        this.center = center;
        double length = Distance(center,neighbors.get(0));
        for (int i = 0; i < neighbors.size();i++){
            if (Distance(neighbors.get(i),center)!=length){
                throw new IllegalArgumentException("Not all edges have the same length! ");
            }
        }
        this.neighbors = GraphSort(neighbors);
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
                return 360 + degree;
            }
        }else{
            return 180 + degree;
        }


    }
    public RadialGraph(Point center) {
        this.center = center;
    }

    @Override
    public RadialGraph rotateBy(int degrees) {
        double newDegrees = Math.toRadians(degrees);
        if (neighbors==null){
            return this;
        }

        List<Point> newNeighbors = new ArrayList<Point>();
        for (Point p: neighbors){
            double newX, newY;
            newX =((p.x-center.x)*Math.cos(newDegrees)-(p.y-center.y)*Math.sin(newDegrees))+center.x;
            newY =((p.x-center.x)*Math.sin(newDegrees)+(p.y-center.y)*Math.cos(newDegrees))+center.y;
            newX = (double)Math.round((newX*1000))/1000;
            newY = (double)Math.round((newY*1000))/1000;
            newNeighbors.add(new Point(p.name,newX,newY));
        }
        Point newCenter = center;
        return new RadialGraph(newCenter,newNeighbors);
    }

    @Override
    public RadialGraph translateBy(double x, double y) {
        if (neighbors==null){
            return this;
        }
        List<Point> newNeighbors = new ArrayList<Point>();
        for (Point p: neighbors){
            double newX, newY;
            newX = p.x + x;
            newY = p.y + y;
            newNeighbors.add(new Point(p.name,newX,newY));
        }
        Point newCenter = center;
        return new RadialGraph(newCenter,newNeighbors);
    }

    @Override
    public String toString() {
        String output = "[";
        output+=center.toString();
        if(neighbors!=null) {
            List<Point> sortedNeighbors = GraphSort(neighbors);
            for (int i = 0; i < sortedNeighbors.size(); i++) {
                output += "; ";
                output += sortedNeighbors.get(i).toString();
            }
        }
        output+="]";
        return output;
    }

    private List<Point> GraphSort(List<Point> neighbors) {
        if (neighbors == null) return null;
        List<Point> sortedNeighbors = new ArrayList<>();
        Map <Double , Point> neighborsWithDegree = new TreeMap<Double , Point>();
        for ( int i = 0; i<neighbors.size(); i++){
            double degree = TiltAngle(center,neighbors.get(i));
            neighborsWithDegree.put(degree,neighbors.get(i));

        }
        for ( Map.Entry <Double , Point> entry : neighborsWithDegree.entrySet()){
            sortedNeighbors.add(entry.getValue());
        }
        return sortedNeighbors;
    }

    @Override
    public Point center() {
        return center;
    }

    /* Driver method given to you as an outline for testing your code. You can modify this as you want, but please keep
     * in mind that the lines already provided here as expected to work exactly as they are (some lines have additional
     * explanation of what is expected) */
    public static void main(String... args) {
        Point center = new Point("center", 0, 0);
        Point east = new Point("east", 1, 0);
        Point west = new Point("west", -1, 0);
        Point north = new Point("north", 0, 1);
        Point south = new Point("south", 0, -1);
        Point toofarsouth = new Point("south", 0, -2);

        // A single node is a valid radial graph.
        RadialGraph lonely = new RadialGraph(center);

        // Must print: [(center, 0.0, 0.0)]
        System.out.println(lonely);


        // This line must throw IllegalArgumentException, since the edges will not be of the same length
        //RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));

        Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));

        // Must follow the documentation in the Shape abstract class, and print the following string:
        // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]
        System.out.println(g);

        // After this counterclockwise rotation by 90 degrees, "north" must be at (-1, 0), and similarly for all the
        // other radial points. The center, however, must remain exactly where it was.
        g = g.rotateBy(90);
        System.out.println(g);
        // you should similarly add tests for the translateBy(x, y) method
    }
}
