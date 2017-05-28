package blobby.graphics;

import blobby.controller.Controller;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Scene used for displaying a menu
 */
public class MenuScene extends SceneObject {
    /**
     * Creates new scene
     *
     * @param controller main controller
     */
    public MenuScene(Controller controller) {
        Group root = new Group();

        // grid
        GridPane grid = new GridPane();
        RowConstraints row = new RowConstraints();
        row.setPercentHeight(25);
        grid.getRowConstraints().add(row);
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().add(colConstraints);
        grid.setAlignment(Pos.CENTER);
        grid.setPrefSize(GameScene.WIDTH, GameScene.HEIGHT);

        // game title
        Text text = new Text("JavaBlobby");
        text.setFont(Font.font("Roboto", 80));
        text.setFill(Color.WHITE);

        // buttons for starting game
        Button bot_btn = new Button("One player");
        GridPane.setMargin(bot_btn, new Insets(10, 0, 10, 0));
        bot_btn.setMaxWidth(300);
        bot_btn.setStyle("-fx-font: 30 Roboto; -fx-base: #12a6ee;");
        bot_btn.setOnAction(event -> controller.initializeGame(true));

        Button human_btn = new Button("Two players");
        human_btn.setMaxWidth(300);
        human_btn.setStyle("-fx-font: 30 Roboto; -fx-base: #12a6ee;");
        human_btn.setOnAction(event -> controller.initializeGame(false));

        // background
        Rectangle cover = new Rectangle(GameScene.WIDTH, GameScene.HEIGHT);
        cover.setFill(Color.rgb(0, 0, 0, 0.4));
        ImageView background = new ImageView("bg.png");

        // set eveything up
        scene = new Scene(root);
        grid.add(text,0,0);
        grid.add(bot_btn, 0, 1);
        grid.add(human_btn, 0, 2);
        root.getChildren().addAll(background, cover, grid);
        root.requestFocus();
    }
}
