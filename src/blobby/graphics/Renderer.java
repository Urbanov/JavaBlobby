package blobby.graphics;

import blobby.game.World;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.ArrayList;
import java.util.List;

/**
 * Class used to draw sprites on the screen
 */
public class Renderer {
    private List<Drawable> drawables;
    private GraphicsContext gc;

    /**
     * Creates new renderer
     *
     * @param gc GraphicsContext associated with canvas
     */
    public Renderer(GraphicsContext gc) {
        drawables = new ArrayList<>();
        this.gc = gc;
    }

    /**
     * Registers game objects and parameters, which should be drawn on the screen
     *
     * @param world model
     */
    public void register(World world) {
        drawables.add(new BallMarker(world.getBall()));
        drawables.add(new Sprite(world.getBall(), new Image("ball.png")));
        drawables.add(new Sprite(world.getRightPlayer().getBlob(), new Image("red_blob.png")));
        drawables.add(new Sprite(world.getLeftPlayer().getBlob(), new Image("blue_blob.png")));
        drawables.add(new Scores(world.getLeftPlayer(), world.getRightPlayer()));
    }

    /**
     * Draw all registered objects on the screen
     */
    public void render() {
        gc.clearRect(0, 0, GameScene.WIDTH,GameScene.HEIGHT);
        drawables.forEach(drawable -> drawable.render(gc));
    }
}
