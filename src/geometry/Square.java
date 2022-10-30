package geometry;

import java.util.*;

/**
 * Square, a plane figure with four equal straight sides and four right angles.
 */
public class Square extends Shape {
    public Point downRight;
    public Point downLeft;
    public Point upLeft;
    public Point upRight;
    public List<Point> squarePoints;
    private final Point center;

    /**
     * Construct a square using 4 points in specific order
     *
     * @param a
     * @param b
     * @param c
     * @param d
     */
    public Square(Point a, Point b, Point c, Point d) {
        upRight = a;
        upLeft = b;
        downLeft = c;
        downRight = d;
        squarePoints = Arrays.asList(upRight, upLeft, downLeft, downRight);
        // check if 4 edges are equal
        if (!(Distance(a, b) == Distance(b, c) && Distance(b, c) == Distance(c, d) && Distance(c, d) == Distance(d, a)
                && Distance(d, a) == Distance(a, d))) {
            throw new IllegalArgumentException("Not every edges are equal!");
        }
        // check if 4 points are different
        if (a.equals(b) || b.equals(c) || c.equals(d) || d.equals(a)) {
            throw new IllegalArgumentException("Can not have two same points!");
        }

        // check if one of the angle is right(if it is, and 4 edges are equal, then it
        // must be a square
        if (!verify90(b, a, d)) {
            throw new IllegalArgumentException("Please make sure every angle is 90 degree");
        }
        double xOfCenter = (downLeft.x + upRight.x) / 2;
        double yofCenter = (downLeft.y + upRight.y) / 2;
        center = new Point("center", xOfCenter, yofCenter);
    }

    @Override
    public Point center() {
        return center;
    }

    @Override
    public Square rotateBy(int degrees) {
        double newDegrees = Math.toRadians(degrees);
        if (squarePoints == null) {
            throw new RuntimeException("Invalid Square");
        }

        List<Point> newPoints = new ArrayList<>();
        for (Point p : squarePoints) {
            double newX, newY;
            newX = ((p.x - center.x) * Math.cos(newDegrees) - (p.y - center.y) * Math.sin(newDegrees)) + center.x;
            newY = ((p.x - center.x) * Math.sin(newDegrees) + (p.y - center.y) * Math.cos(newDegrees)) + center.y;
            newX = (double) Math.round((newX * 1000)) / 1000;
            newY = (double) Math.round((newY * 1000)) / 1000;
            newPoints.add(new Point(p.name, newX, newY));
        }
        List<Point> sorted = GraphSort(newPoints);
        return new Square(sorted.get(0), sorted.get(1), sorted.get(2), sorted.get(3));
    }

    @Override
    public Shape translateBy(double x, double y) {
        if (squarePoints == null) {
            throw new RuntimeException("Invalid Square");
        }

        List<Point> newPoints = new ArrayList<>();
        for (Point p : squarePoints) {
            double newX, newY;
            newX = p.x + x;
            newY = p.y + y;
            newPoints.add(new Point(p.name, newX, newY));
        }
        return new Square(newPoints.get(0), newPoints.get(1), newPoints.get(2), newPoints.get(3));
    }

    @Override
    public String toString() {
        String output = "[";
        if (squarePoints != null) {
            for (int i = 0; i < squarePoints.size(); i++) {
                if (i != 0)
                    output += "; ";
                output += squarePoints.get(i).toString();
            }
        }
        output += "]";
        return output;
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
        return (double)Math.round(Math.sqrt((a.x - b.x) * (a.x - b.x) + (a.y - b.y) * (a.y - b.y))*100)/100;
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

    /**
     * Check if edge ab and edge bc consist a right angle
     *
     * @param a point a
     * @param b point b
     * @param c point c
     * @return true if it is a right angle else false
     */
    private static boolean verify90(Point a, Point b, Point c) {
        // move sub graph a b c to b = (0,0) so:
        Point newA = new Point(a.name, a.x - b.x, a.y - b.y);
        Point newC = new Point(c.name, c.x - b.x, c.y - b.y);
        return (newA.y == -newC.x && newA.x == newC.y);
    }

}
