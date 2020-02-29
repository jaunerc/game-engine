package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import org.joml.Intersectionf;

/**
 * This class represents an intersection tester for two circles.
 */
class IntersectionCircleCircle extends Intersection<Circle, Circle> {

    public IntersectionCircleCircle() {
    }

    @Override
    public boolean test(Circle circleA, Circle circleB) {
        return Intersectionf.testCircleCircle(
                circleA.getCenter(), circleA.getRadius(),
                circleB.getCenter(), circleB.getRadius());
    }
}
