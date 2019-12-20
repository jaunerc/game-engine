package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;

/**
 * This class represents the geometric figure Circle.
 * <p>
 * A circle is represented by a vector that points to the center and a radius.
 */
public class Circle implements Shape {

    private Vector2f center;
    private float radius;

    public Circle() {
        this.center = new Vector2f(0, 0);
        this.radius = 1f;
    }

    public Vector2f getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    public void setCenter(Vector2f center) {
        this.center = center;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    @Override
    public void translate(Vector2f translation) {
        center.add(translation);
    }

    /**
     * Calculates the centroid of this circle. This is just the center of the circle.
     *
     * @return the centroid
     */
    @Override
    public Vector2f calcCentroid() {
        return new Vector2f(center.x, center.y);
    }
}
