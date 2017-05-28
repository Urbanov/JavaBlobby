package blobby.graphics;

import blobby.game.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;


import java.util.ArrayList;
import java.util.List;

public class Renderer {
    private List<Sprite> sprites;
    private BallMarker marker;
    private GraphicsContext gc;

    public Renderer(GraphicsContext gc) {
        sprites = new ArrayList<>();
        this.gc = gc;
    }

    public void register(World world) {
        sprites.add(new Sprite(world.getBall(), new Image("ball.png")));
        sprites.add(new Sprite(world.getRightPlayer().getBlob(), new Image("red_blob.png")));
        sprites.add(new Sprite(world.getLeftPlayer().getBlob(), new Image("blue_blob.png")));

        marker = new BallMarker(world.getBall());
    }

    public void draw() {
        gc.clearRect(0, 0, GameScene.WIDTH,GameScene.HEIGHT);
        marker.render(gc);
        sprites.forEach(sprite -> sprite.render(gc));
    }
}
