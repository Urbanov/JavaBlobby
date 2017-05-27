package blobby.objects;

public interface Collidable {
    void checkCollision(Blob blob);
    void checkCollision(Net net);
    void checkCollision(Court court);
}
