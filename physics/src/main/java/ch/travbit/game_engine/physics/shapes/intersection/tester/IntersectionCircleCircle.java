package ch.travbit.game_engine.physics.shapes.intersection.tester;

import ch.travbit.game_engine.physics.shapes.Circle;
import org.joml.Intersectionf;

/**
 * This class represents an intersection tester for two circles.
 */
public class IntersectionCircleCircle extends Intersection<Circle, Circle> {

    public IntersectionCircleCircle(Circle shapeA, Circle shapeB) {
        super(shapeA, shapeB);
    }

    public IntersectionCircleCircle() {
    }

    @Override
    public boolean test() {
        return Intersectionf.testCircleCircle(
                getShapeA().getCenter(), getShapeA().getRadius(),
                getShapeB().getCenter(), getShapeB().getRadius());
    }
}
