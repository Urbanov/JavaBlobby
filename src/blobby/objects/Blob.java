package blobby.objects;

import blobby.game.Player;
import blobby.utils.Vector;

public class Blob extends Movable implements Controllable {
    public final static int BOTTOM_RADIUS = 330;
    public final static int TOP_RADIUS = 250;
    public final static int CENTER_DIFF = 310;

    public final static int VERTICAL_SPAWN = 0;
    public final static int LEFT_SPAWN = 2000;
    public final static int RIGHT_SPAWN = 4500;

    public final static int TOP_COLLISION = 340;
    public final static int HORIZONTAL_MOVE_DELTA = 45;
    public final static int JUMP_VELOCITY = 200;
    public final static int GRAVITY = 8;

    private Player owner;

    public Blob(Player owner) {
        super(owner.getSide() == Court.Side.LEFT ? LEFT_SPAWN : RIGHT_SPAWN, VERTICAL_SPAWN,
            BOTTOM_RADIUS*2, BOTTOM_RADIUS + TOP_RADIUS + CENTER_DIFF);
        this.owner = owner;
    }

    @Override
    public void checkBounds() {
        // vertical check
        if (position.y <= 0) {
            position.y = 0;
            velocity.y = 0;
        }

        // horizontal check
        if (owner.getSide() == Court.Side.LEFT) {
            if (position.x < 0) {
                position.x = Court.MIN;
            }
            else if (position.x > Net.MIN - width) {
                position.x = Net.MIN - width;
            }
        }
        else {
            if (position.x > Court.MAX - width) {
                position.x = Court.MAX - width;
            }
            if (position.x < Net.MAX) {
                position.x = Net.MAX;
            }
        }
    }

    @Override
    public void moveUp() {
        if (onGround()) {
            velocity.y = JUMP_VELOCITY;
        }
    }

    @Override
    public void moveLeft() {
        //position.x -= HORIZONTAL_MOVE_DELTA;
        velocity.x = -HORIZONTAL_MOVE_DELTA;
    }

    @Override
    public void moveRight() {
        //position.x += HORIZONTAL_MOVE_DELTA;
        velocity.x = HORIZONTAL_MOVE_DELTA;
    }

    public void resetHorizontalMovement() {
        velocity.x = 0;
    }

    //@Override
    public void update() {
        /*position.x += velocity.x;
        position.y += velocity.y;
        if (position.y > 0) {
            velocity.y -= GRAVITY;
        }*/
        super.update(GRAVITY);
    }

    public Vector getBottomCenter() {
        return new Vector(position.x + BOTTOM_RADIUS, position.y + BOTTOM_RADIUS);
    }

    public Vector getTopCenter() {
        return new Vector(position.x + BOTTOM_RADIUS, position.y + BOTTOM_RADIUS + CENTER_DIFF);
    }

    public Player getOwner() {
        return owner;
    }
}
