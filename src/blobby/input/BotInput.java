package blobby.input;

import blobby.game.Player;
import blobby.objects.Ball;
import blobby.objects.Blob;
import blobby.objects.Court;
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
     * Updates bot input by calling appropriate methods
     */
    @Override
    public void update() {
        // reset everything
        input.resetUp();
        input.resetLeft();
        input.resetRight();

        // call appropriate methods
        if (ball.isWaiting()) {
            if (ball.getSide() == Court.Side.RIGHT) {
                onOpponentServe();
            }
            else {
                onServe();
            }
        }
        else if (ball.isValid()) {
            onGame();
        }

        else {
            onRoundEnd();
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

    /**
     * Called when opponent serves
     */
    private void onOpponentServe() {
        moveTo(1000);
    }

    /**
     * Called when bot serves
     */
    private void onServe() {
        moveTo(1300);
        if (distance() < 500) {
            jump();
        }
    }

    /**
     * Called during normal game
     */
    private void onGame() {
        if (ball.minX() < Net.MAX + 400) {

            // high ball
            if (ball.minY() > 2500) {
                if (blob.minX() > ball.minX() - 200) {
                    input.setLeft();
                }

                else if (blob.minX() < ball.minX() - 400) {
                    input.setRight();
                }

                if (ball.minY() < 3800 && distance() < 1000) {
                    jump();
                }
            }
            // low ball
            else {
                moveTo(estimate());
                if (Math.abs(ball.getVelocity().x) < 50) {
                    jump();
                }
            }
        }
        else {
            moveTo(1500);
        }
    }

    /**
     * Called after one of players scores
     */
    private void onRoundEnd() {
        moveTo(1400);
    }

    /**
     * Sets bot input values accoring to desired position
     *
     * @param pos desired horizontal position
     */
    private void moveTo(int pos) {
        if (blob.minX() > pos + 50) {
            input.setLeft();
        }
        else if (blob.minX() < pos - 50){
            input.setRight();
        }
    }

    /**
     * Sets bot input jump value
     */
    private void jump() {
        input.setUp();
    }

    /**
     * Calculates position where the ball is going to land
     *
     * @return horizontal value
     */
    private int estimate() {
        int time = (int) (Math.sqrt(2 * Ball.GRAVITY * (ball.minY() - blob.getHeight()) +
            ball.getVelocity().y * ball.getVelocity().y) + ball.getVelocity().y) / Ball.GRAVITY;
        int target = ball.minX() + ball.getVelocity().x * time;
        if (target < 0) {
            target =- target;
        }
        return target;
    }

    /**
     * Calculates horizontal distance between ball and blob
     * @return distance between ball and blob
     */
    private int distance() {
        return Math.abs(blob.minX() - ball.minX());
    }
}
