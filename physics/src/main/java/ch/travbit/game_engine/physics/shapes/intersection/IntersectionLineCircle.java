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

    public IntersectionLineCircle(LineSegment shapeA, Circle shapeB) {
        super(shapeA, shapeB);
    }

    /**
     * Indicates whether the LineSegment and the Circle shape intersect.
     * <p>
     * First this implementation finds the closest point to the circle center that is still on the line segment. Then
     * the distance to the circle center is measured. The intersection test returns true if the distance between the
     * closest point and the circle center is lower than the radius of the circle.
     *
     * @return true if the LineSegment intersects the Circle; false otherwise
     */
    @Override
    public boolean test() {
        Vector2f lineStart = getShapeA().getStart();
        Vector2f lineEnd = getShapeA().getEnd();
        Vector2f circleCenter = getShapeB().getCenter();
        Vector3f closestPoint = new Vector3f();
        float circleRadius = getShapeB().getRadius();

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
