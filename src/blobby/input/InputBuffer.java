package blobby.input;

/**
 * Buffer updated by AI or key listeners, which is used to move players inside the model
 */
public class InputBuffer {
    private boolean up;
    private boolean left;
    private boolean right;

    /**
     * Creates new buffer
     */
    public InputBuffer() {
        up = false;
        left = false;
        right = false;
    }

    /**
     * Checks whether up key is in the buffer
     *
     * @return logical value of checking
     */
    public boolean up() {
        return up;
    }

    /**
     * Checks whether left key is in the buffer
     *
     * @return logical value of checking
     */
    public boolean left() {
        return left;
    }

    /**
     * Checks whether right key is in the buffer
     *
     * @return logical value of checking
     */
    public boolean right() {
        return right;
    }

    /**
     * Puts up key inside the buffer
     */
    public void setUp() {
        up = true;
    }

    /**
     * Puts left key inside the buffer
     */
    public void setLeft() {
        left = true;
    }

    /**
     * Puts right key inside the buffer
     */
    public void setRight() {
        right = true;
    }


    /**
     * Removes up key from the buffer
     */
    public void resetUp() {
        up = false;
    }

    /**
     * Removes left key from the buffer
     */
    public void resetLeft() {
        left = false;
    }

    /**
     * Removes right key from the buffer
     */
    public void resetRight() {
        right = false;
    }
}
