package blobby.graphics;

import blobby.controller.Controller;
import blobby.game.Player;
import blobby.objects.Court;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

public class GameOverScene extends GameScene {
    public GameOverScene(Player winner, Controller controller) {
        // grid
        Group root = new Group();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        ColumnConstraints colConstraints = new ColumnConstraints();
        colConstraints.setHalignment(HPos.CENTER);
        grid.getColumnConstraints().add(colConstraints);
        grid.setPrefSize(GameScene.WIDTH, GameScene.HEIGHT);

        // background
        Rectangle cover = new Rectangle(GameScene.WIDTH, GameScene.HEIGHT);
        cover.setFill(Color.rgb(0, 0, 0, 0.4));
        ImageView background = new ImageView("bg.png");

        // winner
        HBox hbox = new HBox(40);
        hbox.setAlignment(Pos.CENTER);
        ImageView cup = new ImageView("cup.png");
        Text text = new Text((winner.getSide().toString() + " wins!"));
        text.setFont(Font.font("Roboto", 80));
        text.setFill(Color.WHITE);
        hbox.getChildren().addAll(cup, text);

        // restart button
        Button restart_btn = new Button("Return to menu");
        restart_btn.setStyle("-fx-font: 30 Roboto; -fx-base: #12a6ee;");
        GridPane.setMargin(restart_btn, new Insets(80, 0, 0, 0));
        restart_btn.setOnAction(event -> controller.showMenu());

        // set eveything up
        scene = new Scene(root);
        grid.add(hbox, 0, 0);
        grid.add(restart_btn, 0, 1);
        root.getChildren().addAll(background, cover, grid);
        root.requestFocus();
    }
}
