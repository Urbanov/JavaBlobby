package blobby.input;

import javafx.scene.Scene;
import java.util.ArrayList;
import java.util.List;

public class InputManager {
    private List<Input> inputs;
    private Scene scene;

    public InputManager(Scene scene) {
        inputs = new ArrayList<>(2);
        this.scene = scene;
    }

    public void register(Input input) {
        inputs.add(input);
    }

    public void listen() {
        scene.setOnKeyPressed(event -> {
            inputs.forEach(input -> input.pressed(event.getCode()));
        });

        scene.setOnKeyReleased(event -> {
            inputs.forEach(input -> input.released(event.getCode()));
        });
    }

    public void update() {
        inputs.forEach(input -> input.update());
    }
}
