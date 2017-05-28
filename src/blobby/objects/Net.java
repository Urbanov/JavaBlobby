package blobby.objects;

import blobby.utils.Vector;

/**
 * Class representing a net
 */
public class Net extends GameObject {
    /**
     * Radius of the top
     */
    public final static int RADIUS = 70;

    /**
     * Height of the net
     */
    public final static int HEIGHT = 2750;

    /**
     * Horitontal position
     */
    public final static int POSITION = Court.MAX/2 - RADIUS;

    /**
     * Left bound of net
     */
    public final static int MIN = POSITION;

    /**
     * Right bound of net
     */
    public final static int MAX = Court.MAX/2 + RADIUS;

    /**
     * Creates new net
     */
    public Net() {
        super(POSITION, 0, RADIUS*2, HEIGHT);
    }

    /**
     * Gets center of top of the net
     *
     * @return position of center
     */
    public Vector getTopCenter() {
        return new Vector(POSITION + RADIUS, HEIGHT - RADIUS);
    }
}
