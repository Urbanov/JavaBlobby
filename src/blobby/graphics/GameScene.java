package blobby.graphics;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameScene extends SceneObject {
    public final static int WIDTH = 800;
    public final static int HEIGHT = 600;
    public final static int GROUND_LEVEL = 50;

    private GraphicsContext gc;

    public GameScene() {
        Group root = new Group();
        scene = new Scene(root);

        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFont(Font.font("Roboto", 60));
        gc.setTextBaseline(VPos.TOP);
        gc.setFill(Color.WHITE);
        ImageView background = new ImageView("bg.png");

        root.getChildren().addAll(background, canvas);
    }

    public GraphicsContext getGraphicsContext() {
        return gc;
    }
}
