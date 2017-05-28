package blobby;

import blobby.controller.Controller;
import javafx.application.Application;
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

