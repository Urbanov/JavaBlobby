package blobby.input;

import blobby.objects.Ball;
import blobby.game.Player;
import blobby.objects.Blob;
import blobby.objects.Net;
import javafx.scene.input.KeyCode;

/**
 * AI input
 */
public class BotInput extends Input {
    private Ball ball;
    private Blob blob;

    /**
     * Creates new instance of input
     *
     * @param player who will use the input
     * @param ball reference to ball
     */
    public BotInput(Player player, Ball ball) {
        super(player);
        this.ball = ball;
        this.blob = player.getBlob();
    }

    /**
     * Calculates bot input based on position of the ball
     */
    @Override
    public void update() {
        // reset everything
        input.resetUp();
        input.resetLeft();
        input.resetRight();

        // ball is near our side
        if (ball.minX() < Net.MAX + 400) {

            // serve
            if (ball.isWaiting()) {
                input.setUp();
            }

            // follow the ball
            else if (ball.isValid()) {
                if (blob.minX() > ball.minX() + 200) {
                    input.setLeft();
                }
                if (blob.minX() < ball.minX() + 400) {
                    input.setRight();
                }
                if (ball.minY() < 4000) {
                    input.setUp();
                }
            }
        }

        // position a bit to the left
        else if (ball.isValid()) {
            if (blob.minX() > 1500) {
                input.setLeft();
            }
        }

        // reposition after round
        else {
            if (blob.minX() > 1700) {
                input.setLeft();
            }
            else if (blob.minX() < 1500) {
                input.setRight();
            }
        }
    }

    /**
     * Not used in AI
     *
     * @param key code received from {@link InputManager}
     */
    @Override
    public void pressed(KeyCode key) {

    }

    /**
     * Not used in AI
     *
     * @param key code received from {@link InputManager}
     */
    @Override
    public void released(KeyCode key) {

    }
}
