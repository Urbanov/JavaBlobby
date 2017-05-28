package blobby.graphics;

import blobby.game.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

public class Scores implements Drawable {
    public static int MARGIN = 30;

    private Player left;
    private Player right;

    public Scores(Player left, Player right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public void render(GraphicsContext gc) {
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText(String.format("%02d", left.getScore()), MARGIN, 0);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(String.format("%02d", right.getScore()), GameScene.WIDTH - MARGIN, 0);
    }
}
