package main;

public class Point {
    private float x;
    private float y;
    private float r;
    private boolean success;

    Point(float x, float y, float r, boolean success) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getR() {
        return r;
    }

    public boolean isSuccess() {
        return success;
    }
}