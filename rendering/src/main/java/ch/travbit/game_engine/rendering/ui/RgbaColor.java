package ch.travbit.game_engine.rendering.ui;

import org.joml.Vector4f;

import java.util.Random;

/**
 * This class represents colors in the rgba format.
 */
public class RgbaColor {
    public final static RgbaColor BLACK = new RgbaColor(0, 0, 0, 1);
    public final static RgbaColor WHITE = new RgbaColor(1, 1, 1, 1);

    /**
     * Creates a new rgba color with random values for rgb.
     * <p>
     * The returned color has no transparency so the alpha value is always 1.
     *
     * @return a random color
     */
    public static RgbaColor randomColorNoTransparency() {
        Random random = new Random();
        return new RgbaColor(random.nextFloat(), random.nextFloat(), random.nextFloat(), 1f);
    }

    private float r, g, b, a;

    public RgbaColor() {
        this(BLACK.r, BLACK.g, BLACK.b, BLACK.a);
    }

    public RgbaColor(float r, float g, float b, float a) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
    }

    /**
     * Represents this color as a 4d vector (r,g,b,a).
     * @return This color as 4d vector
     */
    public Vector4f asVector() {
        return new Vector4f(r, g, b, a);
    }

    public void set(Vector4f colorVector) {
        setR(colorVector.x);
        setG(colorVector.y);
        setB(colorVector.z);
        setA(colorVector.w);
    }

    public void set(float r, float g, float b, float a) {
        setR(r);
        setG(g);
        setB(b);
        setA(a);
    }

    public float getR() {
        return r;
    }

    public void setR(float r) {
        this.r = r;
    }

    public float getG() {
        return g;
    }

    public void setG(float g) {
        this.g = g;
    }

    public float getB() {
        return b;
    }

    public void setB(float b) {
        this.b = b;
    }

    public float getA() {
        return a;
    }

    public void setA(float a) {
        this.a = a;
    }
}
