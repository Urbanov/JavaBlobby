package blobby.game;

import blobby.objects.Court;

/**
 * Class which manages scores and rounds
 */
public class Referee {
    /**
     * Points needed to win
     */
    public final static int WIN_POINTS = 15;
    /**
     * Minimal difference between player's and opponent's scores to win
     */
    public final static int WIN_DIFF = 2;

    private World world;
    private boolean ready;
    private Court.Side next_side;

    /**
     * Creates new referee
     *
     * @param world reference to {@link World} model
     */
    public Referee(World world) {
        this.world = world;
        ready = true;
    }

    /**
     *  Checks whetver a player should score and whether it is possible to start a new round
     */
    public void work() {
        if (ready) {
            score();
        }
        if (world.getBall().onGround() && world.getLeftPlayer().getBlob().onGround() &&
            world.getRightPlayer().getBlob().onGround()) {
            if (!checkEnd()) {
                nextRound();
            }
        }
    }

    /**
     * Checks whether any of players already won the game
     *
     * @return logical value
     */
    private boolean checkEnd() {
        int right_score = world.getRightPlayer().getScore();
        int left_score = world.getLeftPlayer().getScore();
        int diff = Math.abs(right_score - left_score);

        if ((right_score >= WIN_POINTS || left_score >= WIN_POINTS) && diff >= WIN_DIFF) {
            world.stop();
            return true;
        }

        return false;
    }

    /**
     * Starts new round
     */
    private void nextRound() {
        ready = true;
        world.getBall().reset(next_side);
    }

    /**
     * Updates players' scores
     */
    private void score() {
        ready = false;
        if (world.getBall().getSide() == Court.Side.RIGHT) {
            world.getLeftPlayer().score();
            next_side = world.getLeftPlayer().getSide();
        }
        else {
            world.getRightPlayer().score();
            next_side = world.getRightPlayer().getSide();
        }
    }
}
