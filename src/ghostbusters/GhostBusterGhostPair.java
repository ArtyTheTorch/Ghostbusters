package ghostbusters;

/**
 *
 * @author BoltonG
 */
public class GhostBusterGhostPair {
    Point g, gb;
    
    /*
     * No argument constructor
     */
    public GhostBusterGhostPair()
    {
    g = new Point();
    gb = new Point();
    }
    
    /*
     * Constructor that takes in a ghost and ghostbuster point object
     */
    public GhostBusterGhostPair(Point gb, Point g)
    {
        this.g = g;
        this.gb = gb;
    }
    
    /*
     * Returns the ghost point
     */
    public Point getGhost()
    {
    return g;
    }
    
    /*
     * Returns the Ghostbuster Point
     */
    public Point getGhostbuster()
    {
    return gb;
    }

    @Override
    public String toString()
    {
    return gb + " and " + g;
    }
}
