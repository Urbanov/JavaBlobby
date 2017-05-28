package blobby.objects;

import blobby.utils.Vector;

/**
 * Abstract class representing objects, which can be moved
 */
public abstract class Movable extends GameObject {
    protected Vector velocity;

    /**
     * Creates new movable object
     *
     * @param posX horizontal position
     * @param posY vertical position
     * @param width width of object
     * @param height height of object
     */
    public Movable(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        velocity = new Vector(0,0);
    }

    /**
     * Checks whether object is on the ground with no vertical speed
     *
     * @return logcal value
     */
    public boolean onGround() {
        return position.y == 0 && velocity.y == 0;
    }

    /**
     * Gets velocity {@link Vector} of the object
     *
     * @return velocity vector
     */
    public Vector getVelocity() {
        return velocity;
    }

    /**
     * Updates position
     *
     * @param gravity value of gravity
     */
    public void update(int gravity) {
        position.x += velocity.x;
        position.y += velocity.y;
        if (position.y > 0) {
            velocity.y -= gravity;
        }
    }
}
