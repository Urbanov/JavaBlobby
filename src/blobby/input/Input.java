package blobby.input;

import blobby.game.Player;
import javafx.scene.input.KeyCode;

public abstract class Input {
    protected InputBuffer input;

    Input(Player player) {
        input = player.getInputBuffer();
    }

    public abstract void update();
    public abstract void pressed(KeyCode key);
    public abstract void released(KeyCode key);
}
