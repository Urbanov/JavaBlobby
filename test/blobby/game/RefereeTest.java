package blobby.game;

import blobby.objects.Ball;
import blobby.objects.Blob;
import blobby.objects.Court;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RefereeTest {
    Referee referee;
    World world;

    @Before
    public void setUp() {
        world = new World();
        referee = new Referee(world);
        world.start();
    }

    @Test
    public void scoreShouldBeUpdatedOnce() {
        world.getBall().reset(Court.Side.RIGHT);
        assertEquals(0, world.getLeftPlayer().getScore());
        referee.work();
        assertEquals(1, world.getLeftPlayer().getScore());
        referee.work();
        assertEquals(1, world.getLeftPlayer().getScore());
    }

    @Test
    public void newRoundShouldNotBeStarted() {
        world.getBall().reset(Court.Side.RIGHT);
        referee.work();
        assertEquals(Court.Side.RIGHT, world.getBall().getSide());
        referee.work();
        assertEquals(Court.Side.RIGHT, world.getBall().getSide());
        world.getBall().setPosition(Ball.RIGHT_SPAWN,0);
        world.getLeftPlayer().getBlob().setPosition(Blob.LEFT_SPAWN, 10);
        referee.work();
        assertEquals(Court.Side.RIGHT, world.getBall().getSide());
    }

    @Test
    public void newRoundShouldBeStarted() {
        world.getBall().setPosition(Ball.RIGHT_SPAWN,0);
        referee.work();
        assertEquals(Court.Side.LEFT, world.getBall().getSide());
        world.getBall().setPosition(Ball.LEFT_SPAWN,0);
        referee.work();
        assertEquals(Court.Side.RIGHT, world.getBall().getSide());
    }

    @Test
    public void gameShouldEndWhenPlayerReachesMaxPoints() {
        assertTrue(world.isRunning());
        for(int i = 0; i < 14; ++i) {
            world.getLeftPlayer().score();
        }
        world.getBall().setPosition(Ball.RIGHT_SPAWN,0);
        referee.work();
        assertFalse(world.isRunning());
    }

    @Test
    public void gameShouldNotEndWhenDifferenceIsTooSmall() {
        assertTrue(world.isRunning());
        for(int i = 0; i < 14; ++i) {
            world.getLeftPlayer().score();
            world.getRightPlayer().score();
        }
        world.getBall().setPosition(Ball.LEFT_SPAWN,0);
        referee.work();
        assertTrue(world.isRunning());
        world.getBall().setPosition(Ball.LEFT_SPAWN,0);
        referee.work();
        assertFalse(world.isRunning());
    }
}
