package blobby.objects;

public class Court extends GameObject {
    public final static int WIDTH = 8000;
    public static int HEIGHT = 6000;

    public final static int MIN = 0;
    public final static int MAX = MIN + WIDTH;

    public enum Side {
        LEFT, RIGHT;

        @Override
        public String toString() {
            return super.toString().substring(0, 1) + super.toString().substring(1).toLowerCase();
        }
    }

    public Court() {
        super(0, 0, WIDTH, HEIGHT);
    }
}