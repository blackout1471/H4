package Model;

public class Vec {

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getZ() {
        return z;
    }

    public void setZ(float z) {
        this.z = z;
    }

    public float getW() {
        return w;
    }

    public void setW(float w) {
        this.w = w;
    }

    private float x;
    private float y;
    private float z;
    private float w;

    public Vec(float _x, float _y, float _z, float _w) {
        x = _x;
        y = _y;
        z = _z;
        w = _w;
    }

    public Vec(float _x, float _y, float _z) {
        x = _x;
        y = _y;
        z = _z;
        w = 0.f;
    }

    public Vec(Vec v) {
        x = v.getX();
        y = v.getY();
        z = v.getZ();
        w = v.getW();
    }

    /**
     * Add a float value to the vector.
     */
    public void add(float v) {
        x += v;
        y += v;
        z += v;
        w += v;
    }

    /**
     * Add another vector to the current vector.
     */
    public void add(Vec vec) {
        x += vec.x;
        y += vec.y;
        z += vec.z;
        w += vec.w;
    }

    /**
     * Multiply the current vector with a value
     */
    public void mult(float m) {
        x *= m;
        y *= m;
        y *= m;
        w *= m;
    }
}
