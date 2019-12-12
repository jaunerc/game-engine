package ch.travbit.game_engine.physics.shapes.intersection.tester;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.LineSegment;
import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Intersectionf;
import org.joml.Vector2f;

import java.util.List;

/**
 * This class represents an intersection tester for a polygon and a circle.
 */
public class IntersectionPolygonCircle extends Intersection<Polygon, Circle> {


    public IntersectionPolygonCircle(Polygon shapeA, Circle shapeB) {
        super(shapeA, shapeB);
    }

    public IntersectionPolygonCircle() {
    }

    /**
     * Indicates whether the circle intersects with the polygon.
     * <p>
     * This method checks for two cases. First if the polygon intersects with a line segment of the polygon or if the
     * polygon is inside the circle. Second if the circle is inside the polygon. This method returns true if one of
     * the cases is fulfilled. The idea is from a stackexchange post.
     *
     * @return true if the circle intersects with the polygon; false otherwise
     * @see <a href="https://gamedev.stackexchange.com/questions/7735/how-do-i-test-if-a-circle-and-concave-polygon-intersect"/>
     */
    @Override
    public boolean test() {
        return circleIntersectsWithALine() || isCircleInsideThePolygon();
    }

    /**
     * Indicates whether the circle intersects with one line of the polygon.
     * <p>
     * This method checks for an intersection between the circle and any line of the polygon. Each intersection test
     * can be reduced to a test between a line segment defined by two vertices of the polygon and the circle. The
     * method terminates as soon as one line intersects with the circle.
     *
     * @return true if the circle intersects with any line of the polygon; false otherwise
     */
    private boolean circleIntersectsWithALine() {
        Vector2f vertexA;
        Vector2f vertexB;
        IntersectionLineCircle intersectionLineCircle;
        List<Vector2f> polygonVertices = getShapeA().getVertices();
        LineSegment lineSegment = new LineSegment();
        for (int i = 0; i < polygonVertices.size(); i++) {
            vertexA = polygonVertices.get(i);

            if (i == polygonVertices.size() - 1) {
                vertexB = polygonVertices.get(0);
            } else {
                vertexB = polygonVertices.get(i + 1);
            }

            lineSegment.setStart(vertexA);
            lineSegment.setEnd(vertexB);
            intersectionLineCircle = new IntersectionLineCircle(lineSegment, getShapeB());
            if (intersectionLineCircle.test()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Indicates whether the circle is inside the polygon.
     * <p>
     * This method checks if the circle is whole in the polygon. The idea is to shoot a ray from the circle center in
     * a direction and count the intersections with the line segments of the polygon. If the count is odd the
     * circle must be inside the polygon. If it's even the circle must be outside the polygon.
     *
     * @return true if the circle is whole inside the polygon; false otherwise
     */
    private boolean isCircleInsideThePolygon() {
        Vector2f vertexA;
        Vector2f vertexB;
        Vector2f circleCenter = getShapeB().getCenter();
        Vector2f rayDirection = new Vector2f(1f, 0f); // ray direction to the right
        List<Vector2f> polygonVertices = getShapeA().getVertices();

        /*
         * the method Intersectionf.intersectRayLineSegment returns -1.0 if the ray does not intersect the line
         * segment
         */
        final float RAY_NOT_INTERSECTING = -1.0f;

        int intersectionCounter = 0;
        for (int i = 0; i < polygonVertices.size(); i++) {
            vertexA = polygonVertices.get(i);

            if (i == polygonVertices.size() - 1) {
                vertexB = polygonVertices.get(0);
            } else {
                vertexB = polygonVertices.get(i + 1);
            }

            float t = Intersectionf.intersectRayLineSegment(circleCenter, rayDirection, vertexA, vertexB);

            if (t == RAY_NOT_INTERSECTING) {
                intersectionCounter++;
            }
        }

        return intersectionCounter % 2 != 0;
    }
}
