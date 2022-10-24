package geometry;

public abstract class Shape {

    /**
     * @return the center of this shape
     */
    public abstract Point center();

    /**
     * Rotates this shape counterclockwise by the specified degrees (not radians) with respect to the positive x axis
     * about the center of this shape.
     *
     * @param degrees the specified degrees of counterclockwise rotation
     * @return the rotated shape
     */
    public abstract Shape rotateBy(int degrees);

    /**
     * Moves every point in the x and y directions by the specified amounts.
     *
     * @param x the amount by which all points are moved along the x axis
     * @param y the amount by which all points are moved along the y axis
     * @return the translated shape
     */
    public abstract Shape translateBy(double x, double y);

    /**
     * @return The string representation of this shape. If the center of this shape is a part of this shape, then it is
     * the first element in the string. All other points of this shape are included in increasing order of their angle
     * with respect to the positive x axis (0 to 360 (not included) degrees). The elements are separated by "; " (i.e.,
     * a single semicolon and a single space). The string in its entirety must be enclosed by "[" and "]".
     *
     * If the center of this shape is not at (0,0), then the increasing order specified above must be determined after
     * translating the shape so that the center is at (0,0).
     */
    public abstract String toString();

}
