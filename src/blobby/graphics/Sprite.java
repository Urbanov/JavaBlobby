package blobby.graphics;

import blobby.controller.Controller;
import blobby.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite implements Drawable {
    private GameObject object;
    private Image image;
    private int offset;

    public Sprite(GameObject object, Image image) {
        this(object, image, GameScene.GROUND_LEVEL);
    }

    public Sprite(GameObject object, Image image, int offset) {
        this.object = object;
        this.image = image;
        this.offset = offset;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.drawImage(image, Controller.translate(object.minX()),
            GameScene.HEIGHT - offset - Controller.translate(object.maxY()));
    }
}
