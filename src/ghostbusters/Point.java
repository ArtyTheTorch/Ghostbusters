package ghostbusters;

import java.util.Comparator;

/**
 *
 * @author Garret
 */
public class Point implements Comparable<Point> {

    public final Comparator<Point> POLAR_ORDER = new PolarOrder();
    protected int x,
            y;
    protected boolean isGhostbuster;

    /**
     * Initializes this Point object to (0, 0).
     */
    public Point() {
        x = 0;
        y = 0;
        isGhostbuster = false;
    } // default constructor

    /**
     * Initializes this Point object to (row, column).
     *
     * @param x the row this Point object has been initialized to.
     * @param y the column this Point object has been initialized to.
     */
    public Point(int x, int y, boolean isGhostbuster) {
        this.x = x;
        this.y = y;
        this.isGhostbuster = isGhostbuster;
    } // constructor

    /**
     * Determines the row of this Point object.
     *
     * @return the row of this Point object.
     */
    public int getX() {
        return x;
    } // method getX

    /**
     * Determines the column of this Point object.
     *
     * @return the column of this Point object.
     */
    public int getY() {
        return y;
    } // method getY

    /**
     * Determines the type of Point object
     *
     * @return the type of this Point object (True = Ghostbuster)
     */
    public boolean isGhostbuster() {
        return isGhostbuster;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    // is a->b->c a counter-clockwise turn?
    // -1 if clockwise, +1 if counter-clockwise, 0 if collinear
    
    
    public static int ccw(Point a, Point b, Point c) {
        double area2 = (b.x - a.x) * (c.y - a.y) - (b.y - a.y) * (c.x - a.x);
        if (area2 < 0) {
            return -1;
        } else if (area2 > 0) {
            return +1;
        } else {
            return 0;
        }
    }

    @Override
    // compare by y-coordinate, breaking ties by x-coordinate
    public int compareTo(Point p) {
        if (this.y < p.y) {
            return -1;
        }
        if (this.y > p.y) {
            return +1;
        }
        if (this.x < p.x) {
            return -1;
        }
        if (this.x > p.x) {
            return +1;
        }
        return 0;
    }

    // compare other points relative to polar angle (between 0 and 2pi) they make with this Point
    private class PolarOrder implements Comparator<Point> {

        @Override
        public int compare(Point q1, Point q2) {
            double dx1 = q1.x - x;
            double dy1 = q1.y - y;
            double dx2 = q2.x - x;
            double dy2 = q2.y - y;

            if (dy1 >= 0 && dy2 < 0) {
                return -1;    // q1 above; q2 below
            } else if (dy2 >= 0 && dy1 < 0) {
                return +1;    // q1 below; q2 above
            } else if (dy1 == 0 && dy2 == 0) {            // 3-collinear and horizontal
                if (dx1 >= 0 && dx2 < 0) {
                    return -1;
                } else if (dx2 >= 0 && dx1 < 0) {
                    return +1;
                } else {
                    return 0;
                }
            } else {
                return -ccw(Point.this, q1, q2);     // both above or below
            }
            // Note: ccw() recomputes dx1, dy1, dx2, and dy2
        }
    }
} // class Point