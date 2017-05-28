package blobby.objects;

import blobby.game.Player;
import blobby.utils.Vector;

/**
 * Class representing a blob
 */
public class Blob extends Movable implements Controllable {
    /**
     * Radius of bottom circle
     */
    public final static int BOTTOM_RADIUS = 330;

    /**
     * Radius of top circle
     */
    public final static int TOP_RADIUS = 250;

    /**
     * Difference between centers of circles
     */
    public final static int CENTER_DIFF = 310;

    /**
     * Where to spawn the blob vertically
     */
    public final static int VERTICAL_SPAWN = 0;

    /**
     * Where to spawn the ball horizontally on the left court
     */
    public final static int LEFT_SPAWN = Court.WIDTH / 4 - BOTTOM_RADIUS;

    /**
     * Where to spawn the ball horizontally on the right court
     */
    public final static int RIGHT_SPAWN = Court.WIDTH * 3 / 4 - BOTTOM_RADIUS;

    /**
     * How much the blob moves in a single frame
     */
    public final static int HORIZONTAL_MOVE_DELTA = 45;

    /**
     * Value of vertical velocity after jumping
     */
    public final static int JUMP_VELOCITY = 200;

    /**
     * Gravity value
     */
    public final static int GRAVITY = 8;

    private Player owner;

    /**
     * Creates new blob
     *
     * @param owner {@link Player} who owns the ball
     */
    public Blob(Player owner) {
        super(owner.getSide() == Court.Side.LEFT ? LEFT_SPAWN : RIGHT_SPAWN, VERTICAL_SPAWN,
            BOTTOM_RADIUS*2, BOTTOM_RADIUS + TOP_RADIUS + CENTER_DIFF);
        this.owner = owner;
    }

    /**
     * Checks whether blob is inside court and updates position accordingly
     */
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

    /**
     * Makes the blob jumo
     */
    @Override
    public void moveUp() {
        if (onGround()) {
            velocity.y = JUMP_VELOCITY;
        }
    }

    /**
     * Moves blob to the left
     */
    @Override
    public void moveLeft() {
        position.x -= HORIZONTAL_MOVE_DELTA;
    }

    /**
     * Moves blob to the right
     */
    @Override
    public void moveRight() {
        position.x += HORIZONTAL_MOVE_DELTA;
    }

    /**
     * Updates blob position every frame
     */
    public void update() {
        super.update(GRAVITY);
    }

    /**
     * Gets center of the bottom circle
     *
     * @return position of center
     */
    public Vector getBottomCenter() {
        return new Vector(position.x + BOTTOM_RADIUS, position.y + BOTTOM_RADIUS);
    }

    /**
     * Gets center of the top circle
     *
     * @return position of center
     */
    public Vector getTopCenter() {
        return new Vector(position.x + BOTTOM_RADIUS, position.y + BOTTOM_RADIUS + CENTER_DIFF);
    }

    public Player getOwner() {
        return owner;
    }
}
