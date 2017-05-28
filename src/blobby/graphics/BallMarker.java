package blobby.graphics;

import blobby.controller.Controller;
import blobby.objects.Ball;
import javafx.scene.canvas.GraphicsContext;

/**
 * Ball marker following the ball at the top of the screen
 */
public class BallMarker implements Drawable {
    public final static int SIZE = 6;

    private Ball ball;

    /**
     * Creates new marker
     *
     * @param ball ball to follow
     */
    public BallMarker(Ball ball) {
        this.ball = ball;
    }

    /**
     * Draws marker on canvas
     *
     * @param gc where to draw
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.fillRect(Controller.translate(ball.getCenter().x) - SIZE / 2, SIZE, SIZE, SIZE);
    }
}
