package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.Polygon;

public class IntersectionPolygonCircle implements Intersection<Polygon, Circle> {
    @Override
    public boolean test(Polygon shapeA, Circle shapeB) {
        return false;
    }
}
