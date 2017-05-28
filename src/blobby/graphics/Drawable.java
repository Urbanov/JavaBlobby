package blobby.graphics;

import javafx.scene.canvas.GraphicsContext;

/**
 * Interface representing an object, which can be drawn on screen
 */
public interface Drawable {
    void render(GraphicsContext gc);
}
