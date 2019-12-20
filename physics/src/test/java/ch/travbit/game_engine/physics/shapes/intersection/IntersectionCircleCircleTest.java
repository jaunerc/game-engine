package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import org.joml.Vector2f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for {@link IntersectionCircleCircle}
 */
class IntersectionCircleCircleTest {
    Circle A = new Circle();
    Circle B = new Circle();

    void createIntersectingCircles() {
        A.setCenter(new Vector2f(5f, 5f));
        A.setRadius(2f);
        B.setCenter(new Vector2f(3f, 3f));
        B.setRadius(2f);
    }

    void createNotIntersectingCircles() {
        A.setCenter(new Vector2f(5f, 5f));
        A.setRadius(2f);
        B.setCenter(new Vector2f(1f, 1f));
        B.setRadius(2f);
    }

    @Test
    void testForIntersectingCircles() {
        createIntersectingCircles();
        Intersection<Circle, Circle> intersection = new IntersectionCircleCircle(A, B);
        assertTrue(intersection.test());
    }

    @Test
    void testForNotIntersectingCircles() {
        createNotIntersectingCircles();
        Intersection<Circle, Circle> intersection = new IntersectionCircleCircle(A, B);
        assertFalse(intersection.test());
    }
}