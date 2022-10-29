package geometry;

import java.util.StringJoiner;

/**
 * An unmodifiable point in the standard two-dimensional Euclidean space. The coordinates of such a point is given by
 * exactly two doubles specifying its <code>x</code> and <code>y</code> values. Each point also has a unique name, which
 * is a <code>String</code> value.
 */
public class Point {

    public final double x, y;
    public final String name;

    public Point(String name, double x, double y) {
        this.name = name;
        this.x    = x;
        this.y    = y;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", "(", ")").add(name).add(Double.toString(x)).add(Double.toString(y)).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Point)) return false;

        Point point = (Point) o;

        if (Double.compare(point.x, x) != 0) return false;
        if (Double.compare(point.y, y) != 0) return false;
        return name.equals(point.name);
    }
}
