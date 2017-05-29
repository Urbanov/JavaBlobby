package blobby.game;

import blobby.objects.Ball;
import blobby.objects.Court;
import blobby.objects.Net;

import java.util.Arrays;
import java.util.List;

/**
 * Class aggregating logical and physical game objects, basically a model
 */
public class World {
    private List<Player> players;
    private Ball ball;
    private Net net;
    private Court court;
    private Referee referee;
    private boolean running;

    /**
     * Creates new model
     */
    public World() {
        players = Arrays.asList(new Player(Court.Side.LEFT), new Player(Court.Side.RIGHT));
        ball = new Ball();
        net = new Net();
        court = new Court();
        referee = new Referee(this);
        running = false;
    }

    /**
     * Checks whether game is running (nobody has won yet)
     *
     * @return logical value
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Starts the game
     */
    public void start() {
        running = true;
    }

    /**
     * Stops the game
     */
    public void stop() {
        running = false;
    }

    /**
     * Gets a reference to {@link Player} on the left
     *
     * @return left player
     */
    public Player getLeftPlayer() {
        return players.get(0);
    }

    /**
     * Gets a reference to {@link Player} on the left
     *
     * @return right player
     */
    public Player getRightPlayer() {
        return players.get(1);
    }

    /**
     * Returns a winner, called only when the game has ended (else it returns a {@link Player} with more points)
     *
     * @return game winner
     */
    public Player getWinner() {
        if (getLeftPlayer().getScore() > getRightPlayer().getScore()) {
            return getLeftPlayer();
        }
        return getRightPlayer();
    }

    /**
     * Get {@link Ball} object
     *
     * @return ball
     */
    public Ball getBall() {
        return ball;
    }

    /**
     * Get {@link Net} object
     *
     * @return net
     */
    public Net getNet() {
        return net;
    }

    /**
     * Get {@link Court} object
     *
     * @return court
     */
    public Court getCourt() {
        return court;
    }

    /**
     * Get {@link Referee} object
     *
     * @return referee
     */
    public Referee getReferee() {
        return referee;
    }

    /**
     * Updates the state of the model, called from controller every frame
     */
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
