package blobby.input;

import blobby.objects.Ball;
import blobby.game.Player;
import javafx.scene.input.KeyCode;

public class BotInput extends Input {
    private Ball ball;

    public BotInput(Player player, Ball ball) {
        super(player);
        this.ball = ball;
    }

    @Override
    public void update() {
        //FIXME
        input.resetUp();
        input.resetLeft();
        input.resetRight();
        //XD
        input.setUp();
    }

    @Override
    public void pressed(KeyCode key) {

    }

    @Override
    public void released(KeyCode key) {

    }
}