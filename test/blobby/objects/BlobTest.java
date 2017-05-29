package blobby.objects;

import blobby.game.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class BlobTest {
    Blob blob;

    @Before
    public void setUp() {
        Player player = new Player(Court.Side.LEFT);
        blob = player.getBlob();
    }

    @Test
    public void blobShouldJump() {
        blob.setPosition(0, 0);
        blob.setVelocity(0, 0);
        blob.moveUp();
        assertEquals(Blob.JUMP_VELOCITY, blob.getVelocity().y);
    }

    @Test
    public void blobShouldNotJumpWhenNotOnGround() {
        blob.setPosition(0, 10);
        blob.setVelocity(0, 0);
        blob.moveUp();
        assertEquals(0, blob.getVelocity().y);
    }

    @Test
    public void blobShouldMoveHorizontally() {
        blob.setPosition(100, 100);
        blob.moveRight();
        assertEquals(100 + Blob.HORIZONTAL_MOVE_DELTA, blob.minX());
    }

    @Test
    public void blobShouldNotMoveWhenCollidingWithWall() {
        blob.setPosition(0, 100);
        blob.moveLeft();
        blob.checkBounds();
        assertEquals(0, blob.minX());
    }

    @Test
    public void blobShouldStopMovingAfterFallindDown() {
        blob.setPosition(0, -10);
        blob.setVelocity(0, -20);
        blob.checkBounds();
        assertTrue(blob.onGround());
        assertEquals(0, blob.getVelocity().y);
    }
}
