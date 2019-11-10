package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;

public class Circle extends Shape {

    private Vector2f center;
    private float radius;

    public Circle() {
        center = new Vector2f(0f, 0f);
        radius = 1f;
    }

    public Vector2f getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public void translate(Vector2f translation) {
        center.add(translation);
    }

    @Override
    public boolean intersectsWith(Shape otherShape) {
        return false;
    }
}
