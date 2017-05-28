package blobby.game;

import blobby.objects.Ball;
import blobby.objects.Court;
import blobby.objects.Net;

import java.util.Arrays;
import java.util.List;

public class World {
    private List<Player> players;
    private Ball ball;
    private Net net;
    private Court court;
    private Referee referee;
    private boolean running;

    public World() {
        players = Arrays.asList(new Player(Court.Side.LEFT), new Player(Court.Side.RIGHT));
        ball = new Ball();
        net = new Net();
        court = new Court();
        referee = new Referee(this);
        running = false;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        running = true;
    }

    public void stop() {
        running = false;
    }

    public Player getLeftPlayer() {
        return players.get(0);
    }

    public Player getRightPlayer() {
        return players.get(1);
    }

    public Player getWinner() {
        if (getLeftPlayer().getScore() > getRightPlayer().getScore()) {
            return getLeftPlayer();
        }
        return getRightPlayer();
    }

    public Ball getBall() {
        return ball;
    }

    public Net getNet() {
        return net;
    }

    public Court getCourt() {
        return court;
    }

    public Referee getReferee() {
        return referee;
    }

    public void tick() {
        ball.update();

        players.forEach(player -> {
            player.move();
            player.getBlob().update();
            player.getBlob().checkBounds();
            ball.checkCollision(player.getBlob());
        });

        ball.checkCollision(net);
        ball.checkCollision(court);

        if (!ball.isValid()) {
            referee.work();
        }
    }
}