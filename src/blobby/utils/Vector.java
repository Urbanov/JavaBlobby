package blobby.utils;

public class Vector {
    public int x;
    public int y;

    public Vector(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Vector(double x, double y) {
        this.x = (int) Math.round(x);
        this.y = (int) Math.round(y);
    }

    public double length() {
        return Math.sqrt(x * x + y * y);
    }

    public double distance(Vector vec) {
        return Math.sqrt(Math.pow(x - vec.x, 2) + Math.pow(y - vec.y, 2));
    }

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

    public Vector collisionPoint(Vector circle1, int radius1, Vector circle2, int radius2) {
        double c_x, c_y;

        // collision with static circle
        if (this.x == 0 && this.y == 0) {
            double distance = circle1.distance(circle2);
            double midpoint_x = (circle1.x + circle2.x) / 2;
            double midpoint_y = (circle1.y + circle2.y) / 2;
            c_x = midpoint_x + ((radius1 + radius2) * (circle1.x - circle2.x)) / distance;
            c_y = midpoint_y + ((radius1 + radius2) * (circle1.y - circle2.y)) / distance;
        }

        // collision with moving circle
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

    public Vector perpendicularFixedLength(double scale) {
        double length = length();
        double temp_x = (-this.x / length);
        double temp_y = (-this.y / length);
        temp_x *= scale;
        temp_y *= scale;
        return new Vector(temp_x, temp_y);
    }

    public void scale(double scale) {
        this.x *= scale;
        this.y *= scale;
    }

    public void inelasticCollision(Vector circle1, Vector circle2) {
        double distance = circle1.distance(circle2);
        double norm_x = (circle2.x - circle1.x) / distance;
        double norm_y = (circle2.y - circle1.y) / distance;
        double temp = this.x * norm_x + this.y * norm_y;
        this.x -= temp * norm_x * 2;
        this.y -= temp * norm_y * 2;
    }
}
