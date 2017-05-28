package blobby.input;

import blobby.game.Player;
import javafx.scene.input.KeyCode;

/**
 * Human input
 */
public class KeyboardInput extends Input {
    private KeyCode up;
    private KeyCode left;
    private KeyCode right;

    /**
     * Creates new instance of input
     *
     * @param player who will use the input
     * @param up key used for moving up
     * @param left key used for moving left
     * @param right key used for moving right
     */
    public KeyboardInput(Player player, KeyCode up, KeyCode left, KeyCode right) {
        super(player);
        this.up = up;
        this.left = left;
        this.right = right;
    }

    /**
     * Not used here
     */
    @Override
    public void update() {

    }

    /**
     * Sets inner {@link InputBuffer} according to pressed keys
     * Called on key press
     *
     * @param key code received from {@link InputManager}
     */
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

    /**
     * Sets inner {@link InputBuffer} according to pressed keys
     * Called on key release
     *
     * @param key code received from {@link InputManager}
     */
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
