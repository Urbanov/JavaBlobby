package blobby.objects;

/**
 * Interface for objects, which can be controlled by keyboard input
 */
public interface Controllable {
    /**
     * Steer up
     */
    void moveUp();

    /**
     * Steer left
     */
    void moveLeft();

    /**
     * Steer right
     */
    void moveRight();

    /**
     * Check bounds
     */
    void checkBounds();
}
