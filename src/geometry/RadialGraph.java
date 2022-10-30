package geometry;

import java.util.*;

/**
 * A radial graph, one point as the
 * central node. All other nodes are connected only
 * to this center by a directed edge. All edges have
 * the same length, and direct away from the center.
 */
public class RadialGraph extends Shape {
    /**
     * Represents all the neighbor points
     **/
    public List<Point> neighbors;
    /**
     * Center of the Radial Graph
     **/
    private final Point center;

    /**
     * Construct a Radial Graph using a center point and neighbor points, the
     * neighbor points will be sort using the same rule in toString (all other
     * points of this shape are included in increasing order of their angle with
     * respect to the positive x axis (0 to 360 (not included) degrees)).
     *
     * @param center    center of the radial graph
     * @param neighbors neighbor points of the radial graph
     */
    public RadialGraph(Point center, List<Point> neighbors) {
        this.center = center;
        double length = Distance(center, neighbors.get(0));
        for (Point neighbor : neighbors) {
            if (Distance(neighbor, center) != length) {
                throw new IllegalArgumentException("Not all edges have the same length! ");
            }
        }
        this.neighbors = GraphSort(neighbors);
    }

    /**
     * construct a Radial Graph using only a center point
     *
     * @param center the center of the radial graph
     */
    public RadialGraph(Point center) {
        this.center = center;
    }

    @Override
    public RadialGraph rotateBy(int degrees) {
        double newDegrees = Math.toRadians(degrees);
        if (neighbors == null) {
            return this;
        }

        List<Point> newNeighbors = new ArrayList<>();
        for (Point p : neighbors) {
            double newX, newY;
            newX = ((p.x - center.x) * Math.cos(newDegrees) - (p.y - center.y) * Math.sin(newDegrees)) + center.x;
            newY = ((p.x - center.x) * Math.sin(newDegrees) + (p.y - center.y) * Math.cos(newDegrees)) + center.y;
            newX = (double) Math.round((newX * 1000)) / 1000;
            newY = (double) Math.round((newY * 1000)) / 1000;
            newNeighbors.add(new Point(p.name, newX, newY));
        }
        return new RadialGraph(center, newNeighbors);
    }

    @Override
    public RadialGraph translateBy(double x, double y) {
        if (neighbors == null) {
            return this;
        }
        List<Point> newNeighbors = new ArrayList<>();
        for (Point p : neighbors) {
            double newX, newY;
            newX = p.x + x;
            newY = p.y + y;
            newNeighbors.add(new Point(p.name, newX, newY));
        }
        return new RadialGraph(center, newNeighbors);
    }

    @Override
    public String toString() {
        String output = "[";
        output += center.toString();
        if (neighbors != null) {
            List<Point> sortedNeighbors = GraphSort(neighbors);
            for (Point sortedNeighbor : sortedNeighbors) {
                output += "; ";
                output += sortedNeighbor.toString();
            }
        }
        output += "]";
        return output;
    }

    @Override
    public Point center() {
        return center;
    }

    // ADDITIONAL METHODS BELOW:

    /**
     * Sort the given List in specific order( all other points of this shape are
     * included in increasing order of their angle with respect to the positive x
     * axis (0 to 360 (not included) degrees). )
     *
     * @param neighbors the list being sorted
     * @return a sorted point list
     */
    private List<Point> GraphSort(List<Point> neighbors) {
        if (neighbors == null)
            return null;
        List<Point> sortedNeighbors = new ArrayList<>();
        Map<Double, Point> neighborsWithDegree = new TreeMap<>();
        for (Point neighbor : neighbors) {
            double degree = TiltAngle(center, neighbor);
            neighborsWithDegree.put(degree, neighbor);
        }
        for (Map.Entry<Double, Point> entry : neighborsWithDegree.entrySet()) {
            sortedNeighbors.add(entry.getValue());
        }
        return sortedNeighbors;
    }

    /**
     * Get the distance between two points a and b
     *
     * @param a
     * @param b
     * @return the distance between a and b in double.
     */
    private static double Distance(Point a, Point b) {
        return Math.round(Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))*100)/100;
    }

    /**
     * Get the tile angle between two points (respect to the positive x-axis)
     *
     * @param a
     * @param b
     * @return the tile angle between a and b
     */
    private static double TiltAngle(Point a, Point b) {
        double xDiff = b.x - a.x;
        double yDiff = b.y - a.y;
        double tanVal = yDiff / xDiff;
        double degree = Math.toDegrees(Math.atan(tanVal));
        if (xDiff >= 0) {
            if (yDiff >= 0) {
                return degree;
            } else {
                return 360 + degree;
            }
        } else {
            return 180 + degree;
        }
    }

    /*
     * Driver method given to you as an outline for testing your code. You can
     * modify this as you want, but please keep
     * in mind that the lines already provided here as expected to work exactly as
     * they are (some lines have additional
     * explanation of what is expected)
     */
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

        // This line must throw IllegalArgumentException, since the edges will not be of
        // the same length
        // RadialGraph nope = new RadialGraph(center, Arrays.asList(north, toofarsouth,
        // east, west));

        Shape g = new RadialGraph(center, Arrays.asList(north, south, east, west));

        // Must follow the documentation in the Shape abstract class, and print the
        // following string:
        // [(center, 0.0, 0.0); (east, 1.0, 0.0); (north, 0.0, 1.0); (west, -1.0, 0.0);
        // (south, 0.0, -1.0)]
        System.out.println(g);

        // After this counterclockwise rotation by 90 degrees, "north" must be at (-1,
        // 0), and similarly for all the
        // other radial points. The center, however, must remain exactly where it was.
        g = g.rotateBy(90);
        System.out.println(g);
        // you should similarly add tests for the translateBy(x, y) method
    }
}
