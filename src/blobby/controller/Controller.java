package blobby.controller;

import blobby.game.Player;
import blobby.game.World;
import blobby.graphics.GameOverScene;
import blobby.graphics.GameScene;
import blobby.graphics.MenuScene;
import blobby.graphics.Renderer;
import blobby.input.BotInput;
import blobby.input.InputManager;
import blobby.input.KeyboardInput;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

/**
 * Class implementing game loop, which also manages scenes
 */
public class Controller extends AnimationTimer {
    private Stage stage;
    private World world;
    private InputManager manager;
    private Renderer renderer;

    /**
     * Creates new controller
     *
     * @param stage main window
     */
    public Controller(Stage stage) {
        this.stage = stage;
    }

    /**
     * Gets stage
     *
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Shows menu scene
     */
    public void showMenu() {
        stage.setScene(new MenuScene(this).getScene());
        stage.sizeToScene();
    }

    /**
     * Shows game scene and initializes the model
     *
     * @param bot true means a single player game, false means two player game
     */
    public void initializeGame(boolean bot) {
        GameScene scene = new GameScene();
        stage.setScene(scene.getScene());
        stage.sizeToScene();
        world = new World();
        renderer = new Renderer(scene.getGraphicsContext());
        renderer.register(world);
        manager = new InputManager(scene.getScene());
        manager.register(new KeyboardInput(world.getRightPlayer(), KeyCode.UP, KeyCode.LEFT, KeyCode.RIGHT));
        if (bot) {
            manager.register(new BotInput(world.getLeftPlayer(), world.getBall()));
        }
        else {
            manager.register(new KeyboardInput(world.getLeftPlayer(), KeyCode.W, KeyCode.A, KeyCode.D));
        }
        start();
    }

    /**
     * Shows game ending screen and stops the game loop
     *
     * @param winner player who won the match
     */
    private void showEnd(Player winner) {
        stop();
        GameOverScene scene = new GameOverScene(winner, this);
        stage.setScene(scene.getScene());
        stage.sizeToScene();
    }

    /**
     * Starts gamme loop, starts listening for input and changes initial state of model
     */
    @Override
    public void start() {
        manager.listen();
        world.start();
        super.start();
    }

    /**
     * Game loop
     *
     * @param now timestamp of current frame in nanoseconds
     */
    @Override
    public void handle(long now) {
        manager.update();
        world.tick();
        renderer.render();

        if (!world.isRunning()) {
            showEnd(world.getWinner());
        }
    }

    /**
     * Translates game coordinates into pixel coordinates
     *
     * @param position in-game position
     * @return position used by renderer
     */
    public static int translate(int position) {
        return (position + 5) / 10;
    }
}
