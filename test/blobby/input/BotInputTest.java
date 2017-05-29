package blobby.input;

import blobby.game.World;
import blobby.objects.Court;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class BotInputTest {
    World world;
    BotInput input;

    @Before
    public void setUp() {
        world = new World();
        input = new BotInput(world.getLeftPlayer(), world.getBall());
        world.getBall().reset(Court.Side.LEFT);
    }

    @Test
    public void updateShouldModifyBuffer() {
        assertFalse(world.getLeftPlayer().getInputBuffer().up());
        input.update();
        assertTrue(world.getLeftPlayer().getInputBuffer().up());
    }
}

