package ch.travbit.game_engine.physics;

import org.joml.Vector2f;

import java.util.Iterator;

/**
 * This class represents an intersecting test for polygons. This implementation checks for each line of a polygon A if
 * it intersects with any line of the polygon B.
 */
public class IntersectingImpl implements Intersecting {


    /**
     * Checks whether two polygons are intersecting or not. As soon as one line intersection test is true the method
     * returns true.
     * @param polyA The first polygon
     * @param polyB The second polygon
     * @return true if the polygons are intersecting false otherwise
     */
    @Override
    public boolean isPolyPolyIntersecting(Polygon polyA, Polygon polyB) {
        Iterator<Vector2f> it = polyA.getVertices().iterator();
        while (it.hasNext()) {
            Vector2f vertexA = it.next();
            Vector2f vertexB = polyA.getVertices().get(0);
            if (it.hasNext()) {
                vertexB = it.next();
            }
            if (isPolyLineIntersecting(polyB, vertexA, vertexB)) {
                return true;
            }
        }

        return false;
    }

    private boolean isPolyLineIntersecting(Polygon poly, Vector2f lineVertexA, Vector2f lineVertexB) {

        Iterator<Vector2f> it = poly.getVertices().iterator();
        while (it.hasNext()) {
            Vector2f vertexA = it.next();
            Vector2f vertexB = poly.getVertices().get(0);
            if (it.hasNext()) {
                vertexB = it.next();
            }

            if (isLineLineIntersecting(lineVertexA.x, lineVertexA.y, lineVertexB.x, lineVertexB.y,
                    vertexA.x, vertexA.y, vertexB.x, vertexB.y)) {
                return true;
            }
        }
        return false;
    }

    private boolean isLineLineIntersecting(float x1, float y1, float x2, float y2, float x3, float y3, float x4,
                                           float y4) {

        float divider = ((y4-y3)*(x2-x1) - (x4-x3)*(y2-y1));
        float uA = ((x4-x3)*(y1-y3) - (y4-y3)*(x1-x3)) / divider;
        float uB = ((x2-x1)*(y1-y3) - (y2-y1)*(x1-x3)) / divider;
        return uA >= 0 && uA <= 1 && uB >= 0 && uB <= 1;
    }
}
