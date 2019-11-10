package ch.travbit.game_engine.physics.shapes.intersection.tester;

import ch.travbit.game_engine.physics.shapes.Circle;

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
        return false;
    }
}
