package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.LineSegment;
import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntersectionLinePolygonTest {
    Polygon polygon = new Polygon();
    LineSegment lineSegment = new LineSegment();

    void createIntersectingShapes() {
        polygon.set(0f, 0f, 1f, 1f, 1f, 0f);
        lineSegment.setStart(new Vector2f(0f, 0f));
        lineSegment.setEnd(new Vector2f(5f, 5f));
    }

    void createNotIntersectingShapes() {
        polygon.set(0f, 0f, 1f, 1f, 1f, 0f);
        lineSegment.setStart(new Vector2f(2f, 2f));
        lineSegment.setEnd(new Vector2f(5f, 5f));
    }

    void createLineInsidePolygon() {
        polygon.set(0f, 0f, 0f, 4f, 3f, 3f, 3f, 0f);
        lineSegment.setStart(new Vector2f(1f, 1f));
        lineSegment.setEnd(new Vector2f(2f, 2f));
    }

    @Test
    void testForIntersectingPolyLine() {
        createIntersectingShapes();
        Intersection<LineSegment, Polygon> intersection = new IntersectionLinePolygon();
        Assertions.assertTrue(intersection.test(lineSegment, polygon));
    }

    @Test
    void testForNotIntersectingPolyLine() {
        createNotIntersectingShapes();
        Intersection<LineSegment, Polygon> intersection = new IntersectionLinePolygon();
        Assertions.assertFalse(intersection.test(lineSegment, polygon));
    }

    @Test
    void testForLineInsidePoly() {
        createLineInsidePolygon();
        Intersection<LineSegment, Polygon> intersection = new IntersectionLinePolygon();
        Assertions.assertTrue(intersection.test(lineSegment, polygon));
    }
}
