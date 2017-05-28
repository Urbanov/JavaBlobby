package blobby.objects;

import blobby.utils.Vector;

public class Ball extends Movable implements Collidable {
    public final static int RADIUS = 315;

    //FIXME
    public final static int VERTICAL_SPAWN = 2000;
    public final static int LEFT_SPAWN = 2000;
    public final static int RIGHT_SPAWN = 7000;
    public final static int DEFAULT_SPAWN = RIGHT_SPAWN;

    public final static int MAX_HITS = 3;
    public final static int GRAVITY = 3;
    public final static double DAMP = 0.45;
    public final static double BUFF = 1.1;
    public final static double BLOB_VELOCITY_FACTOR = 0.15;
    public final static int BOUNCE = 125;
    public final static int MINIMAL_VELOCITY = 40;

    public final int SQUEEZE = 10;

    private boolean valid;
    private boolean waiting;
    private Blob last;
    private int hits;
    private int squeeze;

    public Ball() {
        super(DEFAULT_SPAWN, VERTICAL_SPAWN, RADIUS*2, RADIUS*2);
        waiting = true;
        valid = true;
        squeeze = 0;
    }

    public boolean isValid() {
        return valid;
    }

    public boolean isWaiting() {
        return waiting;
    }

    public void reset(Court.Side side) {
        valid = true;
        waiting = true;
        velocity.x = 0;
        velocity.y = 0;
        setPosition(side == Court.Side.LEFT ? LEFT_SPAWN : RIGHT_SPAWN, VERTICAL_SPAWN);
    }

    private boolean hit(Blob blob) {
        if (squeeze != 0) {
            return false;
        }
        squeeze = SQUEEZE;

        if (blob != last || waiting) {
            last = blob;
            hits = 0;
        }
        waiting = false;

        if (++hits > MAX_HITS) {
            hit();
            return true;
        }
        return false;
    }

    private void hit() {
        valid = false;
    }

    @Override
    public void checkCollision(Blob blob) {
        // ball is invalid, no collision
        if (!valid) {
            return;
        }

        boolean collision = false;

        // top part collision
        if (getCenter().distance(blob.getTopCenter()) <= RADIUS + Blob.TOP_RADIUS) {
            collision = true;

            // calculate new position
            Vector new_pos = velocity.collisionPoint(getCenter(), RADIUS, blob.getTopCenter(), Blob.TOP_RADIUS);

            // hacks for when ball is moving too fast
            if (new_pos.y < blob.getTopCenter().y) {
                int diff = blob.getTopCenter().y - new_pos.y;
                new_pos.y += 2*diff;
            }
            if (new_pos.x > blob.getTopCenter().x && getCenter().x < blob.getTopCenter().x ||
                new_pos.x < blob.getTopCenter().x && getCenter().x > blob.getTopCenter().x) {
                int diff = blob.getTopCenter().x - new_pos.x;
                new_pos.x += 2*diff;
            }

            // calculate new velocity
            double extra_factor = blob.getVelocity().y > 0 ? BLOB_VELOCITY_FACTOR * blob.getVelocity().y : 0;
            velocity = new Vector(blob.getTopCenter().x - new_pos.x, blob.getTopCenter().y - new_pos.y)
                .perpendicularFixedLength(BOUNCE + extra_factor);

            // additional horizontal speed after bouncing
            if (velocity.y < MINIMAL_VELOCITY) {
                velocity.y = MINIMAL_VELOCITY;
            }

            setPosition(new_pos.x - RADIUS, new_pos.y - RADIUS);
        }

        // bottom part collision
        if (getCenter().distance(blob.getBottomCenter()) <= RADIUS + Blob.BOTTOM_RADIUS) {
            collision = true;

            // calculate new position
            Vector new_pos = velocity.collisionPoint(getCenter(), RADIUS, blob.getBottomCenter(), Blob.BOTTOM_RADIUS);

            // hack for when ball is moving too fast
            if (new_pos.x > blob.getTopCenter().x && getCenter().x < blob.getTopCenter().x ||
                new_pos.x < blob.getTopCenter().x && getCenter().x > blob.getTopCenter().x) {
                int diff = blob.getTopCenter().x - new_pos.x;
                new_pos.x += 2*diff;
            }

            // calculate new velocity
            velocity = new Vector(blob.getBottomCenter().x - new_pos.x, blob.getBottomCenter().y - new_pos.y)
                .perpendicularFixedLength(BOUNCE);

            setPosition(new_pos.x - RADIUS, new_pos.y - RADIUS);
        }

        // additional things to check after collision
        if (collision) {
            // damp the ball if player exceeded hit limit
            if (hit(blob)) {
                velocity.scale(DAMP);
            }

            // buff horizontal velocity for better gameplay
            velocity.x *= BUFF;

            // hacks for when ball is outside of field after collision
            if (position.x < Court.MIN || position.x > Court.MAX - width) {
                position.y = blob.maxY();
                update();
            }
            else if (position.x >= Net.MIN - width && position.x <= Net.MAX) {
                position.y = blob.maxY();
                update();
            }
        }
    }

    @Override
    public void checkCollision(Net net) {
        // bottom rectangular part
        if (getCenter().y <= net.getTopCenter().y && position.x >= Net.MIN - width && position.x <= Net.MAX) {
            if (velocity.x < 0) {
                // going left
                position.x = Net.MAX;
            }
            else {
                // going right
                position.x = Net.MIN - width;
            }
            velocity.x = -velocity.x;
        }

        // top circular part
        else {
            double distance = getCenter().distance(net.getTopCenter());
            if (distance <= RADIUS + Net.RADIUS) {

                // calculate new position
                Vector new_pos = velocity.collisionPoint(getCenter(), RADIUS, net.getTopCenter(), Net.RADIUS);

                // hack for when ball is moving too fast
                if (new_pos.y < net.getTopCenter().y) {
                    int diff = net.getTopCenter().y - new_pos.y;
                    new_pos.y += 2*diff;
                }

                // calculate new velocity
                velocity.inelasticCollision(new_pos, net.getTopCenter());
                velocity.y -= GRAVITY;

                setPosition(new_pos.x - RADIUS, new_pos.y - RADIUS);
            }
        }
    }

    @Override
    public void checkCollision(Court court) {
        // vertical check
        if (position.y <= 0) {
            position.y = 0;
            velocity.x *= DAMP;
            velocity.y = (int) Math.round(-velocity.y * DAMP - GRAVITY);
            velocity.y = velocity.y > 0 ? velocity.y : 0;
            hit();
        }

        // horizontal chceck
        if (position.x <= Court.MIN || position.x >= Court.MAX - width) {
            if (velocity.x < 0) {
                // going left
                position.x = Court.MIN;
            }
            else {
                // going right
                position.x = Court.MAX - width;
            }
            velocity.x = -velocity.x;
        }
    }

    public void update() {
        if (squeeze > 0) {
            --squeeze;
        }

        super.update(waiting ? 0 : GRAVITY);

        if (last != null && getSide() != last.getOwner().getSide()) {
            hits = 0;
        }
    }

    public Vector getCenter() {
        return new Vector(position.x + RADIUS, position.y + RADIUS);
    }

    public Court.Side getSide() {
        return getCenter().x >= Court.MAX / 2 ? Court.Side.RIGHT : Court.Side.LEFT;
    }
}
