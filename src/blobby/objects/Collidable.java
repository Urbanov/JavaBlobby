package blobby.objects;

/**
 * Interface for moving objects, which collide with environment
 */
public interface Collidable {
    /**
     * Collision with blob
     *
     * @param blob blob object
     */
    void checkCollision(Blob blob);

    /**
     * Collision with net
     *
     * @param net net object
     */
    void checkCollision(Net net);

    /**
     * Collision with court
     *
     * @param court court object
     */
    void checkCollision(Court court);
}
