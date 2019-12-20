package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.LineSegment;
import org.joml.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Test for {@link IntersectionLineLine}
 */
public class IntersectionLineLineTest {

    LineSegment lineA = new LineSegment();
    LineSegment lineB = new LineSegment();

    void createIntersectingLineSegments() {
        lineA.setStart(new Vector2f(0f, 0f));
        lineA.setEnd(new Vector2f(3f, 3f));
        lineB.setStart(new Vector2f(0f, 3f));
        lineB.setEnd(new Vector2f(3f, 0f));
    }

    void createNotIntersectingLineSegments() {
        lineA.setStart(new Vector2f(0f, 0f));
        lineA.setEnd(new Vector2f(3f, 3f));
        lineB.setStart(new Vector2f(0f, 4f));
        lineB.setEnd(new Vector2f(3f, 7f));
    }

    void createCollinearIntersectingLineSegments() {
        lineA.setStart(new Vector2f(0f, 0f));
        lineA.setEnd(new Vector2f(3f, 3f));
        lineB.setStart(new Vector2f(2f, 2f));
        lineB.setEnd(new Vector2f(5f, 5f));
    }

    void createCollinearNotIntersectingLineSegments() {
        lineA.setStart(new Vector2f(0f, 0f));
        lineA.setEnd(new Vector2f(3f, 3f));
        lineB.setStart(new Vector2f(4f, 4f));
        lineB.setEnd(new Vector2f(5f, 5f));
    }

    @Test
    void testForIntersectingLines() {
        createIntersectingLineSegments();
        Intersection<LineSegment, LineSegment> intersection = new IntersectionLineLine(lineA, lineB);
        Assertions.assertTrue(intersection.test());
    }

    @Test
    void testForNotIntersectingLines() {
        createNotIntersectingLineSegments();
        Intersection<LineSegment, LineSegment> intersection = new IntersectionLineLine(lineA, lineB);
        Assertions.assertFalse(intersection.test());
    }

    @Test
    void testForCollinearIntersectingLines() {
        createCollinearIntersectingLineSegments();
        Intersection<LineSegment, LineSegment> intersection = new IntersectionLineLine(lineA, lineB);
        Assertions.assertTrue(intersection.test());
    }

    @Test
    void testForCollinearNotIntersectingLines() {
        createCollinearNotIntersectingLineSegments();
        Intersection<LineSegment, LineSegment> intersection = new IntersectionLineLine(lineA, lineB);
        Assertions.assertFalse(intersection.test());
    }
}
