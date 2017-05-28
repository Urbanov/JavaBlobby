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

    public void showEnd(Player winner) {
        stop();
        GameOverScene scene = new GameOverScene(winner, this);
        stage.setScene(scene.getScene());
        stage.sizeToScene();
    }

    @Override
    public void start() {
        manager.listen();
        world.start();
        super.start();
    }

    @Override
    public void handle(long now) {
        manager.update();
        world.tick();
        renderer.render();

        if (!world.isRunning()) {
            showEnd(world.getWinner());
        }
    }

    public static int translate(int position) {
        return (position + 5) / 10;
    }
}
