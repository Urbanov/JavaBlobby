package blobby.input;

import blobby.game.Player;
import javafx.scene.input.KeyCode;

/**
 * Base class for input
 */
public abstract class Input {
    protected InputBuffer input;

    /**
     * Create new input
     *
     * @param player who will use the input
     */
    Input(Player player) {
        input = player.getInputBuffer();
    }

    /**
     * Called every frame from game loop
     */
    public abstract void update();

    /**
     * Called on key press
     *
     * @param key key code
     */
    public abstract void pressed(KeyCode key);

    /**
     * Called on key
     *
     * @param key key code
     */
    public abstract void released(KeyCode key);
}
