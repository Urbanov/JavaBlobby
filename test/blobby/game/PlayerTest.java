package blobby.game;

import blobby.objects.Blob;
import blobby.objects.Court;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerTest {
    Player player;

    @Before
    public void setUp() {
        player = new Player(Court.Side.LEFT);
    }

    @Test
    public void sideShouldBeSetCorrectly() {
        assertEquals(Court.Side.LEFT, player.getSide());
    }

    @Test
    public void scoreShouldBeSetCorrectly() {
        assertEquals(0, player.getScore());
    }

    @Test
    public void scoringShouldChangeScore() {
        player.score();
        assertEquals(1, player.getScore());
        player.score();
        assertEquals(2, player.getScore());
    }

    @Test
    public void movingShouldChangeBlobPosition() {
        int old_x = player.getBlob().minX();
        player.getInputBuffer().setLeft();
        player.move();
        assertEquals(old_x - Blob.HORIZONTAL_MOVE_DELTA, player.getBlob().minX());
    }
}
