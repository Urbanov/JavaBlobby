package blobby.utils;

/**
 * Class implementing a 2D vector
 */
public class Vector {
    public int x;
    public int y;

    /**
     * Create a new vector
     *
     * @param x horizontal value
     * @param y vertical value
     */
    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new vector, but casts double parameters to ints
     *
     * @param x horizontal value
     * @param y vertical value
     */
    public Vector(double x, double y) {
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
    }

    /**
     * Calculates length of the vector
     *
     * @return length of the vector
     */
    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    /**
     * Calculates distance to other point
     *
     * @param vec other point
     * @return distance between two points
     */
    public double distance(Vector vec) {
        return Math.sqrt(Math.pow(this.x - vec.x, 2) + Math.pow(this.y - vec.y, 2));
    }

    /**
     * Calculates closest point on segment to a given point
     *
     * @param p1 segment begin
     * @param p2 segment end
     * @return closest point on segment
     */
    public Vector closestPointOnSegment(Vector p1, Vector p2) {
        double a1 = p2.y - p1.y;
        double b1 = p1.x - p2.x;
        double c1 = (p2.y - p1.y) * p1.x + (p1.x - p2.x) * p1.y;
        double c2 = -b1 * this.x + a1 * this.y;
        double det = a1 * a1 + b1 * b1;
        double c_x, c_y;
        if (det != 0) {
            c_x = ((a1 * c1 - b1 * c2) / det);
            c_y = ((a1 * c2 + b1 * c1) / det);
        }
        else {
            c_x = this.x;
            c_y = this.y;
        }
        return new Vector(c_x, c_y);
    }

    /**
     * Calculates point of collision between circle with given velocity (this) and a static one
     *
     * @param circle1 center of first circle
     * @param radius1 radius of first circle
     * @param circle2 center of second circle
     * @param radius2 radius of second circle
     * @return center of first circle after the collision
     */
    public Vector collisionPoint(Vector circle1, int radius1, Vector circle2, int radius2) {
        double c_x, c_y;

        // movable circle has no speed
        if (this.x == 0 && this.y == 0) {
            double distance = circle1.distance(circle2);
            double midpoint_x = (circle1.x + circle2.x) / 2;
            double midpoint_y = (circle1.y + circle2.y) / 2;
            c_x = midpoint_x + ((radius1 + radius2) * (circle1.x - circle2.x)) / distance;
            c_y = midpoint_y + ((radius1 + radius2) * (circle1.y - circle2.y)) / distance;
        }

        // moving circle
        else {
            Vector point = circle2.closestPointOnSegment(circle1,
                new Vector(circle1.x + this.x, circle1.y + this.y));
            double closest_dist_sq = Math.pow(circle2.x - point.x, 2) + Math.pow(circle2.y - point.y, 2);
            double back_dist = Math.sqrt(Math.pow(radius1 + radius2, 2) - closest_dist_sq);
            double velocity_length = length();
            c_x = point.x - back_dist * (this.x / velocity_length);
            c_y = point.y - back_dist * (this.y / velocity_length);
        }

        return new Vector(c_x, c_y);
    }

    /**
     * Returns a perpendicular vector to a given one with desired length
     *
     * @param scale desired length
     * @return perpendicular vector
     */
    public Vector perpendicularFixedLength(double scale) {
        double length = length();
        double temp_x = (-this.x / length);
        double temp_y = (-this.y / length);
        temp_x *= scale;
        temp_y *= scale;
        return new Vector(temp_x, temp_y);
    }

    /**
     * Scale both vector values by another value
     *
     * @param scale scale value
     */
    public void scale(double scale) {
        this.x *= scale;
        this.y *= scale;
    }

    /**
     * Recalculate velocity after inelastic collision between two touching circles
     * @param circle1 center of first circle
     * @param circle2 center of second circle
     */
    public void inelasticCollision(Vector circle1, Vector circle2) {
        double distance = circle1.distance(circle2);
        double norm_x = (circle2.x - circle1.x) / distance;
        double norm_y = (circle2.y - circle1.y) / distance;
        double temp = this.x * norm_x + this.y * norm_y;
        this.x -= temp * norm_x * 2;
        this.y -= temp * norm_y * 2;
    }
}
