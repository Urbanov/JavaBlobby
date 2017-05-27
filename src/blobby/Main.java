package blobby;

import blobby.game.World;
import blobby.graphics.Sprite;
import blobby.input.*;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Main extends Application {

    @Override
    public void start(Stage theStage) throws Exception {
        theStage.setTitle("<3");

        Group root = new Group();
        Scene theScene = new Scene(root);
        theStage.setScene(theScene);

        Canvas canvas = new Canvas(800, 600);
        root.getChildren().add(canvas);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.setFill(Color.BLUE);

        /***/


        World world = new World();

        InputManager manager = new InputManager(theScene);
        manager.register(new KeyboardInput(world.getRightPlayer(), KeyCode.UP, KeyCode.LEFT, KeyCode.RIGHT));
        manager.register(new BotInput(world.getLeftPlayer(), world.getBall()));
        //manager.register(new KeyboardInput(world.getLeftPlayer(), KeyCode.W, KeyCode.A, KeyCode.D));
        manager.listen();

        Sprite sprite = new Sprite(world.getLeftPlayer().getBlob());
        Sprite sprite2 = new Sprite(world.getRightPlayer().getBlob());
        Sprite sprite3 = new Sprite(world.getBall());
        Sprite sprite4 = new Sprite(world.getNet());
        world.start();

        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                manager.update();
                world.tick();

                gc.clearRect(0, 0, 1000,600);
                sprite.render3(gc);
                sprite2.render3(gc);
                sprite3.render2(gc);
                sprite4.render(gc);

                if (!world.isRunning()) {
                    System.out.println("stop");
                    stop();
                }

            }
        }.start();

        theStage.show();
    }
}
