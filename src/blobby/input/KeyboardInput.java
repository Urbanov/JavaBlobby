package blobby.input;

import blobby.game.Player;
import javafx.scene.input.KeyCode;

public class KeyboardInput extends Input {
    private KeyCode up;
    private KeyCode left;
    private KeyCode right;

    public KeyboardInput(Player player, KeyCode up, KeyCode left, KeyCode right) {
        super(player);
        this.up = up;
        this.left = left;
        this.right = right;
    }

    @Override
    public void update() {

    }

    @Override
    public void pressed(KeyCode key) {
        if (key == up) {
            input.setUp();
        }
        else if (key == left) {
            input.setLeft();
        }
        else if (key == right) {
            input.setRight();
        }
    }

    @Override
    public void released(KeyCode key) {
        if (key == up) {
            input.resetUp();
        }
        else if (key == left) {
            input.resetLeft();
        }
        else if (key == right) {
            input.resetRight();
        }
    }
}
