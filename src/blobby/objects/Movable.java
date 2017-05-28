package blobby.objects;

import blobby.utils.Vector;

public abstract class Movable extends GameObject {
    protected Vector velocity;

    public Movable(int posX, int posY, int width, int height) {
        super(posX, posY, width, height);
        velocity = new Vector(0,0);
    }

    public boolean onGround() {
        return position.y == 0 && velocity.y == 0;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void update(int gravity) {
        position.x += velocity.x;
        position.y += velocity.y;
        if (position.y > 0) {
            velocity.y -= gravity;
        }
    }
}
