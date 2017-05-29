package blobby.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MovableTest {
    class MovableMock extends Movable {
        public MovableMock(int posX, int posY, int width, int height) {
            super(posX, posY, width, height);
        }
    }

    MovableMock object;

    @Before
    public void setUp() {
        object = new MovableMock(0,0,50,50);
    }

    @Test
    public void membersShouldBeSetCorrectly() {
        assertEquals(0, object.getVelocity().x);
        assertEquals(0, object.getVelocity().y);
    }

    @Test
    public void velocityShouldBeSet() {
        object.setVelocity(20,40);
        assertEquals(20, object.getVelocity().x);
        assertEquals(40, object.getVelocity().y);
    }

    @Test
    public void objectShouldBeOnTheGround() {
        object.setPosition(100, 100);
        object.setVelocity(100, 100);
        assertFalse(object.onGround());
        object.setPosition(0,0);
        assertFalse(object.onGround());
        object.setVelocity(0,0);
        assertTrue(object.onGround());
    }

    @Test
    public void objectShouldBeUpdated() {
        object.setPosition(100, 100);
        object.setVelocity(10, 20);
        object.update(15);
        assertEquals(20 - 15, object.getVelocity().y);
        assertEquals(100 + 10, object.minX());
        assertEquals(100 + 20, object.minY());
    }
}
