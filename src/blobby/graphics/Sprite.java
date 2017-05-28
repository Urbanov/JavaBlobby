package blobby.graphics;

import blobby.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private GameObject object;
    private Image image;
    private int offset;

    public Sprite(GameObject object, Image image) {
        this.object = object;
        this.image = image;
        this.offset = GameScene.GROUND_LEVEL;
    }

    public void render(GraphicsContext gc) {
        gc.drawImage(image, (object.minX() + 5) / 10, GameScene.HEIGHT - offset - (object.maxY() + 5) / 10);
    }
}
