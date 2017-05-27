package blobby.game;

import blobby.input.InputBuffer;
import blobby.objects.Blob;
import blobby.objects.Court;
import blobby.objects.Court.Side;

public class Player {
    private InputBuffer input;
    private Blob blob;
    private int score;
    private Court.Side side;

    public Player(Side side) {
        this.side = side;
        input = new InputBuffer();
        blob = new Blob(this);
        score = 0;
    }

    public InputBuffer getInputBuffer() {
        return input;
    }

    public Blob getBlob() {
        return blob;
    }

    public int getScore() {
        return score;
    }

    public void score() {
        ++score;
    }

    public Court.Side getSide() {
        return side;
    }

    public void move() {
        blob.resetHorizontalMovement();
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

