package ch.travbit.game_engine.physics.shapes.intersection.tester;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.Polygon;

/**
 * This class represents an intersection tester for a polygon and a circle.
 */
public class IntersectionPolygonCircle extends Intersection<Polygon, Circle> {


    public IntersectionPolygonCircle(Polygon shapeA, Circle shapeB) {
        super(shapeA, shapeB);
    }

    public IntersectionPolygonCircle() {
    }

    @Override
    public boolean test() {
        return false;
    }
}
