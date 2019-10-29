package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Intersectionf;
import org.joml.Vector2f;

/**
 * This class represents an intersection tester for two polygons.
 */
public class IntersectionPolygonPolygon implements Intersection<Polygon, Polygon> {

    public IntersectionPolygonPolygon() {
    }

    @Override
    public boolean test(Polygon shapeA, Polygon shapeB) {
        Vector2f[] verticesA = shapeA.getVertices().toArray(new Vector2f[0]);
        Vector2f[] verticesB = shapeB.getVertices().toArray(new Vector2f[0]);
        return Intersectionf.testPolygonPolygon(verticesA, verticesB);
    }
}
