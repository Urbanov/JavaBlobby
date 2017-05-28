package blobby.controller;

import blobby.game.World;
import blobby.graphics.GameScene;
import blobby.graphics.MenuScene;
import blobby.graphics.Renderer;
import blobby.input.BotInput;
import blobby.input.InputManager;
import blobby.input.KeyboardInput;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

public class Controller extends AnimationTimer {
    private Stage stage;
    private World world;
    private InputManager manager;
    private Renderer renderer;

    public Controller(Stage stage) {
        this.stage = stage;
    }

    public void showMenu() {
        stage.setScene(new MenuScene(this).getScene());
        stage.sizeToScene();
    }

    public void startHumanGame() {
        initializeGame();
        manager.register(new KeyboardInput(world.getLeftPlayer(), KeyCode.W, KeyCode.A, KeyCode.D));
        this.start();
    }

    public void startBotGame() {
        initializeGame();
        manager.register(new BotInput(world.getLeftPlayer(), world.getBall()));
        this.start();
    }

    public void initializeGame() {
        GameScene scene = new GameScene();
        stage.setScene(scene.getScene());
        stage.sizeToScene();
        renderer = new Renderer(scene.getGraphicsContext());
        world = new World();
        renderer.register(world);
        manager = new InputManager(scene.getScene());
        manager.register(new KeyboardInput(world.getRightPlayer(), KeyCode.UP, KeyCode.LEFT, KeyCode.RIGHT));
    }

    @Override
    public void start() {
        manager.listen();
        world.start();
        super.start();
    }

    @Override
    public void stop() {
        super.stop();
    }

    @Override
    public void handle(long now) {
        manager.update();
        world.tick();
        renderer.draw();

        if (!world.isRunning()) {
            stop();
        }
    }
}
