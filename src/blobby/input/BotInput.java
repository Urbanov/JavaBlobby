package blobby.input;

import blobby.objects.Ball;
import blobby.game.Player;
import javafx.scene.input.KeyCode;

/**
 * AI input
 */
public class BotInput extends Input {
    private Ball ball;

    /**
     * Creates new instance of input
     *
     * @param player who will use the input
     * @param ball reference to ball
     */
    public BotInput(Player player, Ball ball) {
        super(player);
        this.ball = ball;
    }

    /**
     * Calculates bot input based on position of the ball
     */
    @Override
    public void update() {
        //FIXME
        input.resetUp();
        input.resetLeft();
        input.resetRight();
        //XD
        input.setUp();
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
