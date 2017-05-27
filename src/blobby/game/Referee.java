package blobby.game;

import blobby.objects.Court;
import blobby.objects.Net;

public class Referee {
    public final static int WIN_POINTS = 3;
    public final static int DIFF_WIN = 2;

    private World world;
    private boolean ready;
    private Court.Side next_side;

    public Referee(World world) {
        this.world = world;
        ready = true;
    }

    public void ready() {
        ready = true;
    }

    public void work() {
        if (ready) {
            score();
        }
        if (world.getBall().onGround() && world.getLeftPlayer().getBlob().onGround() && world.getRightPlayer().getBlob().onGround()) {
            if (!checkEnd()) {
                nextRound();
            }
        }
    }

    public boolean checkEnd() {
        int right_score = world.getRightPlayer().getScore();
        int left_score = world.getLeftPlayer().getScore();
        int diff = Math.abs(right_score - left_score);

        if ((right_score >= WIN_POINTS || left_score >= WIN_POINTS) && diff >= DIFF_WIN) {
            world.stop();
            return true;
        }

        return false;
    }

    public void nextRound() {
        ready = true;
        world.getBall().reset(next_side);
    }

    public void score() {
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
