package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.Polygon;

public class IntersectionPolygonCircle extends Intersection<Polygon, Circle> {


    public IntersectionPolygonCircle(Polygon shapeA, Circle shapeB) {
        super(shapeA, shapeB);
    }

    @Override
    public boolean test() {
        return false;
    }
}
