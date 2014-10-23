package ghostbusters;

import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Garret
 */
public class MakeAPair {

    LinkedList<Point> points;
    LinkedList<Point> upperPoints = new LinkedList<Point>();
    LinkedList<Point> lowerPoints = new LinkedList<Point>();
    Point p0;
    int ng;   //Number of ghost
    int ngb;  //Number of ghostbusters
    int difference = 0;  // Used to compute the difference betteen ghostbusters and ghost  (ngb-ng)
    GhostBusterGhostPair pair;
    
    /*
     * No Argument Constructor
     */
    public MakeAPair() {
    }

    /*
     * Finds a Pair of ghostbusters and ghost and then deletes them from the
     * origional list
     */
    public MakeAPair(LinkedList<Point> p) {
        int count;
        int end;
        points = p;
        Collections.sort(points);
        ng = 0;     //Number of ghost
        ngb = 0;   //Number of ghostbusters
        p0 = points.getFirst();
        if (p0.isGhostbuster) {
            Collections.sort(points, p0.POLAR_ORDER);

            for (int i = points.indexOf(p0) + 1; i < points.size(); i++) {
                if (points.get(i).isGhostbuster() == false) {
                    ng++;
                }
                if (points.get(i).isGhostbuster() == true) {
                    ngb++;
                }
                difference = ngb - ng;
                if (difference == -1) {
                    Point p1 = points.get(i);
                    pair = new GhostBusterGhostPair(p0, p1);
                    end = points.indexOf(p1);
                    count = 0;
                    while (count < end) {
                        lowerPoints.add(points.get(count));
                        count++;
                    }
                    while (end < points.size()) {
                        upperPoints.add(points.get(end));
                        end++;
                    }
                    lowerPoints.remove(p0);
                    lowerPoints.remove(p1);
                    upperPoints.remove(p0);
                    upperPoints.remove(p1);
                    break;
                }
            }
        } else {
            Collections.sort(points, p0.POLAR_ORDER);

            for (int i = points.indexOf(p0) + 1; i < points.size(); i++) {
                if (points.get(i).isGhostbuster() == false) {
                    ng++;
                }
                if (points.get(i).isGhostbuster() == true) {
                    ngb++;
                }
                difference = ng - ngb;
                if (difference == -1) {
                    Point p1 = points.get(i);
                    pair = new GhostBusterGhostPair(p0, p1);
                    end = points.indexOf(p1);
                    count = 0;
                    while (count < end) {
                        lowerPoints.add(points.get(count));
                        count++;
                    }
                    while (end < points.size()) {
                        upperPoints.add(points.get(end));
                        end++;
                    }
                    lowerPoints.remove(p0);
                    lowerPoints.remove(p1);
                    upperPoints.remove(p0);
                    upperPoints.remove(p1);
                    break;
                }
            }
        }
    }

    /*
     * Returns the remaining points after the pair has been made
     */
    public LinkedList<Point> getRemainingPoints() {
        return points;
    }

    /*
     * return the GhostBusterGhostPair for this list
     */
    public GhostBusterGhostPair getPair() {
        return pair;
    }

    /*
     * Returns the points below the line made with the pair
     */
    public LinkedList<Point> getLower() {
        return lowerPoints;
    }

    /*
     * Returns the points above the line made wiht the pair
     */
    public LinkedList<Point> getUpper() {
        return upperPoints;
    }
}
