package blobby.graphics;

import blobby.objects.Ball;
import javafx.scene.canvas.GraphicsContext;

public class BallMarker {
    public final static int SIZE = 6;

    private Ball ball;

    public BallMarker(Ball ball) {
        this.ball = ball;
    }

    public void render(GraphicsContext gc) {
        gc.fillRect((ball.getCenter().x + 5) / 10 - SIZE / 2, SIZE, SIZE, SIZE);
    }
}
