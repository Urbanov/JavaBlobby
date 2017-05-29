package blobby.objects;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GameObjectTest  {
    class GameObjectMock extends GameObject {
        public GameObjectMock(int posX, int posY, int width, int height) {
            super(posX, posY, width, height);
        }
    }

    GameObjectMock object;

    @Before
    public void setUp()  {
        object = new GameObjectMock(50, 20, 100, 80);
    }

    @Test
    public void membersShouldBeSetCorrectly() {
        assertEquals(50, object.minX());
        assertEquals(50 + 100, object.maxX());
        assertEquals(20, object.minY());
        assertEquals(20 + 80, object.maxY());
        assertEquals(100, object.getWidth());
        assertEquals(80, object.getHeight());
    }

    @Test
    public void positionShouldBeSet() {
        object.setPosition(300,400);
        assertEquals(300, object.minX());
        assertEquals(400, object.minY());
    }
}
