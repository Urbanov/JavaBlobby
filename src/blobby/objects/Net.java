package blobby.objects;

import blobby.utils.Vector;

public class Net extends GameObject {
    public final static int RADIUS = 70;
    public final static int HEIGHT = 2750;
    public final static int POSITION = Court.MAX/2 - RADIUS;
    public final static int MIN = POSITION;
    public final static int MAX = Court.MAX/2 + RADIUS;

    public Net() {
        super(POSITION, 0, RADIUS*2, HEIGHT);
    }

    public Vector getTopCenter() {
        return new Vector(POSITION + RADIUS, HEIGHT - RADIUS);
    }
}
