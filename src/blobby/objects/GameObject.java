package blobby.objects;

import blobby.utils.Vector;

public abstract class GameObject {
    protected Vector position;
    protected int width;
    protected int height;

    public GameObject(int posX, int posY, int width, int height) {
        position = new Vector(posX, posY);
        this.width = width;
        this.height = height;
    }

    public void setPosition(int posX, int posY) {
        position.x = posX;
        position.y = posY;
    }

    public int minX() {
        return position.x;
    }

    public int maxX() {
        return position.x + width;
    }

    public int minY() {
        return position.y;
    }

    public int maxY() {
        return position.y + height;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}