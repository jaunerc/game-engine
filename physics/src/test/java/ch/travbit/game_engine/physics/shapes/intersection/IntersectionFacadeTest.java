package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.LineSegment;
import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class IntersectionFacadeTest {

    @Test
    void testIntersectingPolyPoly() {
        Polygon A = new Polygon();
        A.set(0f, 0f, 1f, 1f, 1f, 0f);
        Polygon B = new Polygon();
        B.set(.5f, .5f, 1f, 1f, .5f, 0f);
        Assertions.assertTrue(IntersectionFacade.testPolygonShape(A, B));
    }

    @Test
    void testIntersectingCircleCircle() {
        Circle A = new Circle();
        A.setCenter(new Vector2f(1f, 1f));
        A.setRadius(1f);
        Circle B = new Circle();
        A.setCenter(new Vector2f(.5f, .5f));
        A.setRadius(1f);
        Assertions.assertTrue(IntersectionFacade.testCircleShape(A, B));
    }

    @Test
    void testIntersectingLineLine() {
        LineSegment A = new LineSegment();
        A.setStart(new Vector2f(0f, 0f));
        A.setEnd(new Vector2f(1f, 1f));
        LineSegment B = new LineSegment();
        B.setStart(new Vector2f(0f, 1f));
        B.setEnd(new Vector2f(1f, 0f));
        Assertions.assertTrue(IntersectionFacade.testLineSegmentShape(A, B));
    }
}
