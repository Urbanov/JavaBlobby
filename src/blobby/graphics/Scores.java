package blobby.graphics;

import blobby.game.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.text.TextAlignment;

/**
 * Used for drawing player's scores
 */
public class Scores implements Drawable {
    /**
     * Margin of the score from left and right sides
     */
    public final static int MARGIN = 30;

    private Player left;
    private Player right;

    /**
     * Creates new instance
     *
     * @param left reference to left {@link Player}
     * @param right reference to right {@link Player}
     */
    public Scores(Player left, Player right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Draws scores on canvas
     *
     * @param gc where to draw
     */
    @Override
    public void render(GraphicsContext gc) {
        gc.setTextAlign(TextAlignment.LEFT);
        gc.fillText(String.format("%02d", left.getScore()), MARGIN, 0);
        gc.setTextAlign(TextAlignment.RIGHT);
        gc.fillText(String.format("%02d", right.getScore()), GameScene.WIDTH - MARGIN, 0);
    }
}
