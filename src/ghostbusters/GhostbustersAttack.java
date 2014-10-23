package ghostbusters;

import java.util.LinkedList;

/**
 *
 * @author Garret
 */
public class GhostbustersAttack {

    LinkedList<GhostBusterGhostPair> gbPairs = new LinkedList<GhostBusterGhostPair>();
    LinkedList<Point> points = new LinkedList<Point>();

    //No Argument constructor
    public GhostbustersAttack() {
        gbPairs = new LinkedList<GhostBusterGhostPair>();
    }

    /*
     * Takes in a list of ghostbuster and ghost points and creates the attack
     * strategy
     */
    public GhostbustersAttack(LinkedList<Point> p) {
        points = p;
    }

    /*
     * returns a list of GhostBusterGhostPairs which is the solution
     */
    public LinkedList<GhostBusterGhostPair> getPairs() {
        return gbPairs;
    }

    /*
     * To String method
     */
    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < gbPairs.size(); i++) {
            str = gbPairs.get(i) + "\n";
        }
        return str;
    }

    public void recursiveAttack(LinkedList<Point> p) {

        MakeAPair pair = new MakeAPair(p);
        gbPairs.add(pair.getPair());
        if (pair.getLower().size() > 0) {
            recursiveAttack(pair.getLower());
        }
        if (pair.getUpper().size() > 0) {
            recursiveAttack(pair.getUpper());
        }
    }
}
