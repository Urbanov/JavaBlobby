package blobby.graphics;

import javafx.scene.Scene;

/**
 * Abstract class for any scene used in the game
 */
abstract public class SceneObject {
    protected Scene scene;

    /**
     * Gets {@link Scene} associated with wrapper
     *
     * @return scene
     */
    public Scene getScene() {
        return scene;
    }
}
