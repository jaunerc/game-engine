package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.LineSegment;
import org.joml.Vector2f;

/**
 * This class represents an intersection tester for two line segments.
 */
class IntersectionLineLine extends Intersection<LineSegment, LineSegment> {

    public IntersectionLineLine() {

    }

    public IntersectionLineLine(LineSegment shapeA, LineSegment shapeB) {
        super(shapeA, shapeB);
    }

    /**
     * Indicates whether the two LineSegment intersect.
     * <p>
     * This is an implementation based on: http://www.cs.swan.ac.uk/~cssimon/line_intersection.html
     *
     * @return true if the LineSegments intersect; false otherwise
     */
    @Override
    public boolean test() {
        Vector2f p1 = getShapeA().getStart();
        Vector2f p2 = getShapeA().getEnd();
        Vector2f p3 = getShapeB().getStart();
        Vector2f p4 = getShapeB().getEnd();

        float divisor = (p4.x - p3.x) * (p1.y - p2.y) - (p1.x - p2.x) * (p4.y - p3.y);

        if (divisor != 0) {
            float tA = ((p3.y - p4.y) * (p1.x - p3.x) + (p4.x - p3.x) * (p1.y - p3.y)) /
                    divisor;

            float tB = ((p1.y - p2.y) * (p1.x - p3.x) + (p2.x - p1.x) * (p1.y - p3.y)) /
                    divisor;

            return tA >= 0 && tA <= 1 && tB >= 0 && tB <= 1;
        } else {
            /*
             * The two line segments are collinear. Either they are parallel and intersect at no point or intersect at
             * infinitely many points. The second case should return true.
             */
            return !isPointOnLineSegment(p3, p1, p2);
        }
    }

    /**
     * Indicates whether one point is on the line segment.
     * <p>
     * If two line segments are collinear either they are parallel and intersect at no point or intersect at
     * infinitely many points. The method checks for one point of a line segment A if this point can be on a line
     * segment B or not. If not the line segments have to be parallel with no intersection since their slope is equal.
     *
     * @param pointOfLineA the point to check if it is on the line segment B
     * @param startLineB   the start point of the line segment
     * @param endLineB     the end point of the line segment
     * @return true if the point is on the line segment defined by startLineB and endLineB; false otherwise
     */
    private boolean isPointOnLineSegment(Vector2f pointOfLineA, Vector2f startLineB, Vector2f endLineB) {
        boolean pointOnLineSegmentB =
                pointOfLineA.x > startLineB.x && pointOfLineA.x > endLineB.x ||
                        pointOfLineA.x < startLineB.x && pointOfLineA.x < endLineB.x ||
                        pointOfLineA.y > startLineB.y && pointOfLineA.y > endLineB.y ||
                        pointOfLineA.y < startLineB.y && pointOfLineA.y < endLineB.y;
        return pointOnLineSegmentB;
    }
}
