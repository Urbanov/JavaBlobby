package blobby.game;

import blobby.objects.Blob;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class WorldTest {
    World world;

    @Before
    public void setUp() {
        world = new World();
    }

    @Test
    public void membersShouldBeSetCorrectly() {
        assertNotNull(world.getLeftPlayer());
        assertNotNull(world.getRightPlayer());
        assertNotNull(world.getBall());
        assertNotNull(world.getNet());
        assertNotNull(world.getCourt());
        assertNotNull(world.getReferee());
        assertFalse(world.isRunning());
    }

    @Test
    public void worldShouldStart() {
        world.start();
        assertTrue(world.isRunning());
    }

    @Test
    public void worldShouldStop() {
        world.start();
        world.stop();
        assertFalse(world.isRunning());
    }

    @Test
    public void winnerShouldBeReturned() {
        for(int i = 0; i < 15; ++i) {
            world.getLeftPlayer().score();
        }
        assertSame(world.getLeftPlayer(), world.getWinner());
    }

    @Test
    public void blobShouldBeUpdatedDuringTick() {
        world.start();
        world.getLeftPlayer().getBlob().setPosition(100,100);
        world.tick();
        world.tick();
        assertEquals(100 - Blob.GRAVITY, world.getLeftPlayer().getBlob().minY());
    }
}
