package blobby.game;

import blobby.input.InputBuffer;
import blobby.objects.Blob;
import blobby.objects.Court;
import blobby.objects.Court.Side;

/**
 * Class representing a player
 */
public class Player {
    private InputBuffer input;
    private Blob blob;
    private int score;
    private Court.Side side;

    /**
     * Creates new player
     *
     * @param side player's side of court
     */
    public Player(Side side) {
        this.side = side;
        input = new InputBuffer();
        blob = new Blob(this);
        score = 0;
    }

    /**
     * Gets {@link InputBuffer} associated with player
     *
     * @return players's input buffer
     */
    public InputBuffer getInputBuffer() {
        return input;
    }

    /**
     * Gets {@link Blob} associated with player
     *
     * @return player's blob
     */
    public Blob getBlob() {
        return blob;
    }

    /**
     * Returns player's score
     *
     * @return player's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds a point to player's score
     */
    public void score() {
        ++score;
    }

    /**
     * Gets {@link Court.Side} associated with player
     *
     * @return player's side
     */
    public Court.Side getSide() {
        return side;
    }

    /**
     * Moves {@link Blob} associated with player, called every frame inside game loop
     */
    public void move() {
        if (input.up()) {
            blob.moveUp();
        }
        if (input.left()) {
            blob.moveLeft();
        }
        if (input.right()) {
            blob.moveRight();
        }
    }
}
