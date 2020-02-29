package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.LineSegment;
import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Vector2f;

import java.util.List;

/**
 * This class represents an intersection tester for a polygon and a circle.
 */
class IntersectionPolygonCircle extends Intersection<Polygon, Circle> {

    public IntersectionPolygonCircle() {
    }

    /**
     * Indicates whether the circle intersects with the polygon.
     * <p>
     * This method checks for two cases. First if the polygon intersects with a line segment of the polygon or if the
     * polygon is inside the circle. Second if the circle is inside the polygon. This method returns true if one of
     * the cases is fulfilled. The idea is from a stackexchange post.
     *
     * @param polygon the Polygon shape
     * @param circle  the Circle shape
     * @return true if the circle intersects with the polygon; false otherwise
     * @see <a href="https://gamedev.stackexchange.com/questions/7735/how-do-i-test-if-a-circle-and-concave-polygon-intersect"/>
     */
    @Override
    public boolean test(Polygon polygon, Circle circle) {
        return circleIntersectsWithALine(polygon, circle) || isCircleInsideThePolygon(polygon, circle);
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
    private boolean circleIntersectsWithALine(Polygon polygon, Circle circle) {
        Vector2f vertexA;
        Vector2f vertexB;
        IntersectionLineCircle intersectionLineCircle;
        List<Vector2f> polygonVertices = polygon.getVertices();
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
            intersectionLineCircle = new IntersectionLineCircle();
            if (intersectionLineCircle.test(lineSegment, circle)) {
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
    private boolean isCircleInsideThePolygon(Polygon polygon, Circle circle) {
        Vector2f circleCenter = circle.getCenter();
        List<Vector2f> polygonVertices = polygon.getVertices();

        return isRayIntersectingOddNumberOfLines(circleCenter, polygonVertices);
    }
}
