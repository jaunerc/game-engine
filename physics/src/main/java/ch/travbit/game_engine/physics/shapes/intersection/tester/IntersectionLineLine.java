package ch.travbit.game_engine.physics.shapes.intersection.tester;

import ch.travbit.game_engine.physics.shapes.LineSegment;
import org.joml.Vector2f;

/**
 * This class represents an intersection tester for two line segments.
 */
public class IntersectionLineLine extends Intersection<LineSegment, LineSegment> {

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
             * infinitely many points. The second case should return true. Therefore the method checks for one point
             * of the line segment B if this point can be on the line segment A or not. If not the line segments have
             * to be parallel with no intersection since their slope is equal.
             */
            boolean checkParallelP3 = p3.x > p1.x && p3.x > p2.x || p3.x < p1.x && p3.x < p2.x ||
                    p3.y > p1.y && p3.y > p2.y || p3.y < p1.y && p3.y < p2.y;

            return !checkParallelP3;
        }
    }
}
