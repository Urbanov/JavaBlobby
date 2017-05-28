package blobby.input;

public class InputBuffer {
    private boolean up;
    private boolean left;
    private boolean right;

    public InputBuffer() {
        up = false;
        left = false;
        right = false;
    }

    public boolean up() {
        return up;
    }

    public boolean left() {
        return left;
    }

    public boolean right() {
        return right;
    }

    public void setUp() {
        up = true;
    }

    public void setLeft() {
        left = true;
    }

    public void setRight() {
        right = true;
    }

    public void resetUp() {
        up = false;
    }

    public void resetLeft() {
        left = false;
    }

    public void resetRight() {
        right = false;
    }
}
