package blobby.graphics;

import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 * Scene used for rendering graphics during game
 */
public class GameScene extends SceneObject {
    /**
     * Width of the canvas
     */
    public final static int WIDTH = 800;
    /**
     * Height of the canvas
     */
    public final static int HEIGHT = 600;
    /**
     * Ground level for rendering
     */
    public final static int GROUND_LEVEL = 50;

    private GraphicsContext gc;

    /**
     * Creates new scene
     */
    public GameScene() {
        Group root = new Group();
        scene = new Scene(root);

        // set up canvas for rendering
        Canvas canvas = new Canvas(WIDTH, HEIGHT);
        gc = canvas.getGraphicsContext2D();
        gc.setFont(Font.font("Roboto", 60));
        gc.setTextBaseline(VPos.TOP);
        gc.setFill(Color.WHITE);
        ImageView background = new ImageView("bg.png");

        root.getChildren().addAll(background, canvas);
    }

    /**
     * Returns {@link GraphicsContext} associated with the scene
     *
     * @return GraphicsContext object
     */
    public GraphicsContext getGraphicsContext() {
        return gc;
    }
}
