package blobby.objects;

/**
 * Class representing a court
 */
public class Court extends GameObject {
    /**
     * Width of the court
     */
    public final static int WIDTH = 8000;

    /**
     * Height of the court
     */
    public static int HEIGHT = 6000;

    /**
     * Left bound of court
     */
    public final static int MIN = 0;

    /**
     * Right bound of court
     */
    public final static int MAX = MIN + WIDTH;

    /**
     * Allows distinction between two sides of court
     */
    public enum Side {
        LEFT, RIGHT;

        @Override
        public String toString() {
            return super.toString().substring(0, 1) + super.toString().substring(1).toLowerCase();
        }
    }

    /**
     * Creates new court
     */
    public Court() {
        super(0, 0, WIDTH, HEIGHT);
    }
}
