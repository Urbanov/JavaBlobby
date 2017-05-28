package blobby.objects;

import blobby.utils.Vector;

/**
 * Abstract class representing a game object with position, width and height
 */
public abstract class GameObject {
    protected Vector position;
    protected int width;
    protected int height;

    /**
     * Creates new game object
     *
     * @param posX horizontal position
     * @param posY vertical position
     * @param width width of object
     * @param height height of object
     */
    public GameObject(int posX, int posY, int width, int height) {
        position = new Vector(posX, posY);
        this.width = width;
        this.height = height;
    }

    /**
     * Sets object's position to desired coords
     *
     * @param posX horizontal position
     * @param posY vertical position
     */
    public void setPosition(int posX, int posY) {
        position.x = posX;
        position.y = posY;
    }

    /**
     * Returns left side of the object
     *
     * @return position
     */
    public int minX() {
        return position.x;
    }

    /**
     * Returns right side of the object
     *
     * @return position
     */
    public int maxX() {
        return position.x + width;
    }

    /**
     * Returns bottom side of the object
     *
     * @return position
     */
    public int minY() {
        return position.y;
    }

    /**
     * Returns top side of the object
     *
     * @return position
     */
    public int maxY() {
        return position.y + height;
    }

    /**
     * Returns width of the object
     *
     * @return width value
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns height of the object
     *
     * @return height value
     */
    public int getHeight() {
        return height;
    }
}
