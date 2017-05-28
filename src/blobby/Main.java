package blobby;

import blobby.controller.Controller;
import blobby.game.World;
import blobby.graphics.Sprite;
import blobby.input.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("JavaBlobby");
        primaryStage.setResizable(false);
        Controller controller = new Controller(primaryStage);
        controller.showMenu();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

