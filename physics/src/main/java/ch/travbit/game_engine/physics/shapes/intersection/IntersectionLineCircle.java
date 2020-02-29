package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.LineSegment;
import org.joml.Intersectionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

/**
 * This class represents an intersection tester for line segments and circles.
 */
class IntersectionLineCircle extends Intersection<LineSegment, Circle> {

    public IntersectionLineCircle() {
    }

    /**
     * Indicates whether the LineSegment and the Circle shape intersect.
     * <p>
     * First this implementation finds the closest point to the circle center that is still on the line segment. Then
     * the distance to the circle center is measured. The intersection test returns true if the distance between the
     * closest point and the circle center is lower than the radius of the circle.
     *
     * @param lineSegment the LineSegment shape
     * @param circle      the Circle shape
     * @return true if the LineSegment intersects the Circle; false otherwise
     */
    @Override
    public boolean test(LineSegment lineSegment, Circle circle) {
        Vector2f lineStart = lineSegment.getStart();
        Vector2f lineEnd = lineSegment.getEnd();
        Vector2f circleCenter = circle.getCenter();
        Vector3f closestPoint = new Vector3f();
        float circleRadius = circle.getRadius();

        /*
        the result is saved in the 3-dimensional vector closestPoint which is passed as a parameter.
         */
        Intersectionf.findClosestPointOnLineSegment(lineStart.x, lineStart.y, 0,
                lineEnd.x, lineEnd.y, 0,
                circleCenter.x, circleCenter.y, 0, closestPoint);

        float distanceClosestToCircleCenter = circleCenter.distance(closestPoint.x, closestPoint.y);

        return (distanceClosestToCircleCenter < circleRadius);
    }
}
