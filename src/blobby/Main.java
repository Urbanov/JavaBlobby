package blobby;

import blobby.controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main application class
 */
public class Main extends Application {

    /**
     * Starts the application, creates controller and shows menu
     *
     * @param primaryStage main window
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaBlobby");
        primaryStage.setResizable(false);
        Controller controller = new Controller(primaryStage);
        controller.showMenu();
        primaryStage.show();
    }

    /**
     * Main method of application
     *
     * @param args command line args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
