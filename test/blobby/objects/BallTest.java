package blobby.objects;

import blobby.game.Player;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BallTest {
    Ball ball;
    Court court;
    Net net;
    Blob blob;

    @Before
    public void setUp() {
        ball = new Ball();
        court = new Court();
        net = new Net();
        Player player = new Player(Court.Side.LEFT);
        blob = player.getBlob();
    }

    @Test
    public void membersShouldBeSetCorrectly() {
        assertTrue(ball.isValid());
        assertTrue(ball.isWaiting());
    }

    @Test
    public void ballShouldReportCorrectSide() {
        ball.setPosition(0,0);
        assertEquals(Court.Side.LEFT, ball.getSide());
        ball.setPosition(Net.MAX,0);
        assertEquals(Court.Side.RIGHT, ball.getSide());
    }

    @Test
    public void resetShouldResetVelocityAndPosition() {
        ball.setPosition(100,100);
        ball.setVelocity(100, 100);
        ball.reset(Court.Side.LEFT);
        assertEquals(Court.Side.LEFT, ball.getSide());
        assertEquals(Ball.VERTICAL_SPAWN, ball.minY());
        assertEquals(0, ball.getVelocity().x);
        assertEquals(0, ball.getVelocity().y);
    }

    @Test
    public void ballShouldBeWaitingAfterReset() {
        assertTrue(ball.isWaiting());
        blob.setPosition(500, 500);
        ball.setPosition(600, 600);
        ball.checkCollision(blob);
        assertFalse(ball.isWaiting());
        ball.reset(Court.Side.LEFT);
        assertTrue(ball.isWaiting());
    }

    @Test
    public void ballShouldCollideWithCourt() {
        ball.setVelocity(-10, 10);
        ball.setPosition(-10, 20);
        ball.checkCollision(court);
        assertEquals(Court.MIN, ball.minX());
        assertEquals(10, ball.getVelocity().x);
    }

    @Test
    public void ballShouldCollideWithNet() {
        ball.setVelocity(10, 10);
        ball.setPosition(Net.MIN - Ball.RADIUS + 10, 20);
        ball.checkCollision(net);
        assertEquals(Net.MIN, ball.maxX());
        assertEquals(-10, ball.getVelocity().x);
    }

    @Test
    public void ballShouldCollideWithBlob() {
        blob.setPosition(500, 500);
        ball.setPosition(600, 600);
        ball.checkCollision(blob);
        assertTrue(ball.minX() != 600 || ball.minY() != 600);
    }

    @Test
    public void ballShouldBecomeInvalidAfterFourHits() {
        assertTrue(ball.isValid());
        for (int i = 0; i < 4; ++i) {
            blob.setPosition(500, 500);
            ball.setPosition(600, 600);
            ball.checkCollision(blob);
            for (int j = 0; j < 10; ++j) {
                ball.update();
            }
        }
        assertFalse(ball.isValid());
    }

    @Test
    public void ballShouldBecomeInvalidAfterHittingGround() {
        assertTrue(ball.isValid());
        ball.setPosition(0, 0);
        ball.checkCollision(court);
        assertFalse(ball.isValid());
    }
}
