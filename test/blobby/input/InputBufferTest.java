package blobby.input;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class InputBufferTest {
    InputBuffer buffer;

    @Before
    public void setUp() {
        buffer = new InputBuffer();
    }

    @Test
    public void membersShouldBeInitializedCorrectly() {
        assertFalse(buffer.up());
        assertFalse(buffer.left());
        assertFalse(buffer.right());
    }

    @Test
    public void settingKeyShouldModifyBuffer() {
        assertFalse(buffer.up());
        buffer.setUp();
        assertTrue(buffer.up());
        buffer.setLeft();
        assertTrue(buffer.left());
    }

    @Test
    public void resettingKeyShouldModifyBuffer() {
        buffer.setUp();
        buffer.setLeft();
        buffer.setRight();
        buffer.resetUp();
        assertFalse(buffer.up());
        assertTrue(buffer.left());
        buffer.resetLeft();
        assertFalse(buffer.left());
    }
}
