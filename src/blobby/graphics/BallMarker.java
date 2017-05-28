package blobby.graphics;

import blobby.controller.Controller;
import blobby.objects.Ball;
import javafx.scene.canvas.GraphicsContext;

public class BallMarker implements Drawable {
    public final static int SIZE = 6;

    private Ball ball;

    public BallMarker(Ball ball) {
        this.ball = ball;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.fillRect(Controller.translate(ball.getCenter().x) - SIZE / 2, SIZE, SIZE, SIZE);
    }
}
