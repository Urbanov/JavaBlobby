package blobby.input;

import javafx.scene.Scene;

import java.util.ArrayList;
import java.util.List;

/**
 * Class managing all inputs
 */
public class InputManager {
    private List<Input> inputs;
    private Scene scene;

    /**
     * Creates new manager
     *
     * @param scene where to add key listeners
     */
    public InputManager(Scene scene) {
        inputs = new ArrayList<>(2);
        this.scene = scene;
    }

    /**
     * Registers {@link Input} in the manager
     *
     * @param input input object (AI or keyboard) to be registered
     */
    public void register(Input input) {
        inputs.add(input);
    }

    /**
     * Sets up key listeners
     */
    public void listen() {
        scene.setOnKeyPressed(event -> {
            inputs.forEach(input -> input.pressed(event.getCode()));
        });

        scene.setOnKeyReleased(event -> {
            inputs.forEach(input -> input.released(event.getCode()));
        });
    }

    /**
     * Calls update method on every registered input
     */
    public void update() {
        inputs.forEach(input -> input.update());
    }
}
