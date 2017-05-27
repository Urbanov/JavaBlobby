package blobby.graphics;

import blobby.objects.GameObject;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Sprite {
    private GameObject object;
    private Image image;

    public Sprite(GameObject object/*, Image image*/) {
        this.object = object;
        //this.image = image;
    }

    public void render(GraphicsContext gc) {
        int HEIGHT = 600;
        //gc.drawImage(image, object.minX(), HEIGHT - object.maxY());
        gc.fillRect(object.minX()/10, HEIGHT - object.maxY()/10, object.getWidth()/10, object.getHeight()/10);
    }

    public void render2(GraphicsContext gc) {
        int HEIGHT = 600;
        Image image = new Image("file:C:/Users/Admin/Desktop/ball.png");
        gc.drawImage(image, object.minX()/10, HEIGHT - object.maxY()/10);
        //gc.fillRect(object.minX()/10, HEIGHT - object.maxY()/10, object.getWidth()/10, object.getHeight()/10);
    }

    public void render3(GraphicsContext gc) {
        int HEIGHT = 600;
        Image image = new Image("file:C:/Users/Admin/Desktop/blob.png");
        gc.drawImage(image, object.minX()/10, HEIGHT - object.maxY()/10);
        //gc.fillRect(object.minX()/10, HEIGHT - object.maxY()/10, object.getWidth()/10, object.getHeight()/10);
    }
}
