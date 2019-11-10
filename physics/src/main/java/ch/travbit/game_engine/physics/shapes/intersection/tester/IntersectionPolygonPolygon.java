package ch.travbit.game_engine.physics.shapes.intersection.tester;

import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Intersectionf;
import org.joml.Vector2f;

/**
 * This class represents an intersection tester for two polygons.
 */
public class IntersectionPolygonPolygon extends Intersection<Polygon, Polygon> {


    public IntersectionPolygonPolygon(Polygon shapeA, Polygon shapeB) {
        super(shapeA, shapeB);
    }

    public IntersectionPolygonPolygon() {
    }

    @Override
    public boolean test() {
        Vector2f[] verticesA = getShapeA().getVertices().toArray(new Vector2f[0]);
        Vector2f[] verticesB = getShapeB().getVertices().toArray(new Vector2f[0]);
        return Intersectionf.testPolygonPolygon(verticesA, verticesB);
    }
}
