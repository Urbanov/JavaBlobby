package blobby.objects;

public class Court extends GameObject {
    public final static int WIDTH = 8000;
    public static int HEIGHT = 6000;

    public final static int MIN = 0;
    public final static int MAX = MIN + WIDTH;

    public enum Side {
        LEFT, RIGHT
    }

    public Court() {
        super(0, 0, WIDTH, HEIGHT);
    }
}