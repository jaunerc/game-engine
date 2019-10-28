package ch.travbit.game_engine.physics;

import org.joml.Intersectionf;
import org.joml.Vector2f;

/**
 * This class represents an intersecting test for polygons. This implementation uses the joml library.
 */
public class IntersectingJomlImpl implements Intersecting {

    @Override
    public boolean isPolyPolyIntersecting(Polygon polyA, Polygon polyB) {
        Vector2f[] verticesA = polyA.getVertices().toArray(new Vector2f[0]);
        Vector2f[] verticesB = polyB.getVertices().toArray(new Vector2f[0]);
        return Intersectionf.testPolygonPolygon(verticesA, verticesB);
    }
}
