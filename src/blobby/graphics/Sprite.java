package blobby.graphics;

import blobby.controller.Controller;
import blobby.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Draws any physical game object on screen
 */
public class Sprite implements Drawable {
    private GameObject object;
    private Image image;
    private int offset;

    /**
     * Creates new sprite
     *
     * @param object reference to any {@link GameObject}
     * @param image image associated with object
     */
    public Sprite(GameObject object, Image image) {
        this(object, image, GameScene.GROUND_LEVEL);
    }

    /**
     * Creates new sprite, which will be drawn on screen moved by a vertical offset
     *
     * @param object reference to any {@link GameObject}
     * @param image image associated with object
     * @param offset how far move the sprite
     */
    public Sprite(GameObject object, Image image, int offset) {
        this.object = object;
        this.image = image;
        this.offset = offset;
    }

    /**
     * Draws marker on canvas
     *
     * @param gc where to draw
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, Controller.translate(object.minX()),
            GameScene.HEIGHT - offset - Controller.translate(object.maxY()));
    }
}
