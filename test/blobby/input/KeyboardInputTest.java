package blobby.input;

import blobby.game.World;
import javafx.scene.input.KeyCode;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class KeyboardInputTest {
    World world;
    KeyboardInput input;

    @Before
    public void setUp() {
        world = new World();
        input = new KeyboardInput(world.getRightPlayer(), KeyCode.UP, KeyCode.LEFT, KeyCode.RIGHT);
    }

    @Test
    public void pressingKeyShouldModifyBuffer() {
        assertFalse(world.getRightPlayer().getInputBuffer().up());
        input.pressed(KeyCode.UP);
        assertTrue(world.getRightPlayer().getInputBuffer().up());
    }

    @Test
    public void releasingKeyShouldModifyBuffer() {
        input.pressed(KeyCode.UP);
        input.released(KeyCode.UP);
        assertFalse(world.getRightPlayer().getInputBuffer().up());
    }
}
