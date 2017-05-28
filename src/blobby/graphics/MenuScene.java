package blobby.graphics;

import blobby.controller.Controller;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class MenuScene {
    private Scene scene;
    private Controller controller;

    public MenuScene(Controller controller) {
        this.controller = controller;
        Group root = new Group();
        scene = new Scene(root);
        ImageView background = new ImageView("menu_bg.png");
        Button button1 = new Button("bot game");
        Button button2 = new Button("human game");

        button1.setOnAction(event -> controller.startHumanGame());

        root.getChildren().addAll(background, button1);
    }

    public Scene getScene() {
        return scene;
    }
}
