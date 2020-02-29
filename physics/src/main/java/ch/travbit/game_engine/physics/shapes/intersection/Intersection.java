package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Shape;
import org.joml.Intersectionf;
import org.joml.Vector2f;

import java.util.List;

/**
 * This class provides methods to test if a shape intersects with another shape.
 *
 * @param <A> the first shape type
 * @param <B> the second shape type
 */
abstract class Intersection<A extends Shape, B extends Shape> {

    public Intersection() {
    }

    /**
     * Indicates whether the two shape are intersecting.
     *
     * @param shapeA the first shape
     * @param shapeB the second shape
     * @return true if the two shapes are intersecting; false otherwise
     */
    public abstract boolean test(A shapeA, B shapeB);

    /**
     * Indicates whether a ray intersects an odd number of line segments.
     * <p>
     * The idea is to shoot a ray from the origin in a direction and count the intersections with the line segments
     * of vertices list. This can be used to detect if a shape is inside another shape. The method create lines
     * between two neighbors from the list of vertices. So vertices[0] is connected with vertices[1] and so on. The
     * last vertex is connected with the first vertex.
     *
     * @param rayOrigin the origin of the ray
     * @param vertices  a list of vertices
     * @return true if the number of intersecting lines is odd; false otherwise
     */
    public boolean isRayIntersectingOddNumberOfLines(Vector2f rayOrigin, List<Vector2f> vertices) {
        final Vector2f rayDirection = new Vector2f(1f, 0f);
        Vector2f vertexA;
        Vector2f vertexB;
        int intersectionCounter = 0;

        /*
         * the method Intersectionf.intersectRayLineSegment returns -1.0 if the ray does not intersect the line
         * segment
         */
        final float RAY_NOT_INTERSECTING = -1.0f;

        for (int i = 0, j = 1; i < vertices.size(); i++, j = (j + 1) % vertices.size()) {
            vertexA = vertices.get(i);
            vertexB = vertices.get(j);
            float t = Intersectionf.intersectRayLineSegment(rayOrigin, rayDirection, vertexA, vertexB);

            if (t != RAY_NOT_INTERSECTING) {
                intersectionCounter++;
            }
        }

        return intersectionCounter % 2 != 0;
    }
}
