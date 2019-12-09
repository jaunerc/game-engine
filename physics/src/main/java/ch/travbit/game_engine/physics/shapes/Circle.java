package ch.travbit.game_engine.physics.shapes;

import ch.travbit.game_engine.physics.shapes.intersection.handler.IntersectionHandler;
import org.joml.Vector2f;

public class Circle extends Shape {

    private Vector2f center;
    private float radius;

    private IntersectionHandler<Circle> intersectionHandler;

    public Circle() {
        this(null);
    }

    public Circle(IntersectionHandler<Circle> intersectionHandler) {
        this.center = new Vector2f(0, 0);
        this.radius = 1f;
        this.intersectionHandler = intersectionHandler;
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

    @Override
    public boolean intersectsWith(Shape otherShape) {
        return intersectionHandler.intersectWithShape(otherShape);
    }
}
