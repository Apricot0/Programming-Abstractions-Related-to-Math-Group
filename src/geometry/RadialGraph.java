package geometry;

import java.util.*;

public class RadialGraph extends Shape {

    public RadialGraph(Point center, List<Point> neighbors) {
        // TODO: part of assignment
    }

    public RadialGraph(Point center) {
        // TODO: part of assignment
    }

    @Override
    public RadialGraph rotateBy(int degrees) {
        return null; // TODO: part of assignment
    }

    @Override
    public RadialGraph translateBy(double x, double y) {
        return null; // TODO: part of assignment
    }

    @Override
    public String toString() {
        return null; // TODO: part of assignment
    }

    @Override
    public Point center() {
        return null; // TODO: part of assignment
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
        RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth, east, west));

        Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));

        // Must follow the documentation in the Shape abstract class, and print the following string:
        // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0); (south, 0.0, -1.0)]
        System.out.println(g);

        // After this counterclockwise rotation by 90 degrees, "north" must be at (-1, 0), and similarly for all the
        // other radial points. The center, however, must remain exactly where it was.
        g = g.rotateBy(90);

        // you should similarly add tests for the translateBy(x, y) method
    }
}
