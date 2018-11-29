package main;

import static java.lang.StrictMath.pow;
import static java.lang.StrictMath.sqrt;

public class Point {
    private float x;
    private float y;
    private float r;
    private boolean success;

    Point(float x, float y, float r, boolean success) {
        this.x = x;
        this.y = y;
        this.r = r;
        this.success = success;
    }

    Point(float x, float y, float r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    Point() {

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

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setR(float r) {
        this.r = r;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    private boolean fits1() {
        return
                (this.x <= this.r / 2) &&
                        (this.x >= 0) &&
                        (this.y <= this.r) &&
                        (this.y >= 0);

    }

    private boolean fits2() {
        if (this.y < 0 || this.x > 0 || this.x < -this.r / 2 || this.y > this.r / 2) return false;
        return this.y <= sqrt(pow(this.r / 2, 2) - pow(this.x, 2));
    }

    private boolean fits3() {
        if (this.x > 0 || this.y > 0) return false;
        return this.y >= -this.x - this.r;
    }

    void checkArea() {
        this.success = (this.fits2() || this.fits3() || this.fits1());
    }

    public String toString() {
        return "{\"x\":\"" + this.x + "\"," +
                "\"y\":\"" + this.y + "\"," +
                "\"r\":\"" + this.r + "\"," +
                "\"success\":" + this.success + "}";
    }
}