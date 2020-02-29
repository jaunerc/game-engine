package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Vector2f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for {@link IntersectionCircleCircle}
 */
class IntersectionPolygonCircleTest {
    Polygon poly = new Polygon();
    Circle circle = new Circle();

    void createIntersectingShapes() {
        poly.set(1f, 2f, 3f, 4f, 5f, 2f);
        circle.setCenter(new Vector2f(1f, 1f));
        circle.setRadius(2f);
    }

    void createNotIntersectingShapes() {
        poly.set(2f, 2f, 3f, 3f);
        circle.setCenter(new Vector2f(0f, 0f));
        circle.setRadius(1f);
    }

    void createCircleInsidePolygonShapes() {
        poly.set(0f, 0f, 0f, 4f, 4f, 4f, 4f, 0f);
        circle.setCenter(new Vector2f(2f, 2f));
        circle.setRadius(1f);
    }

    void createCircleNotInsidePolygonShapes() {
        poly.set(0f, 0f, 0f, 4f, 4f, 4f, 4f, 0f);
        poly.translate(new Vector2f(-4f, 0f));
        circle.setCenter(new Vector2f(2f, 2f));
        circle.setRadius(1f);
    }

    @Test
    void testForIntersectingCircles() {
        createIntersectingShapes();
        Intersection<Polygon, Circle> intersection = new IntersectionPolygonCircle();
        assertTrue(intersection.test(poly, circle));
    }

    @Test
    void testForNotIntersectingCircles() {
        createNotIntersectingShapes();
        Intersection<Polygon, Circle> intersection = new IntersectionPolygonCircle();
        assertFalse(intersection.test(poly, circle));
    }

    @Test
    void testForCircleInsidePolygon() {
        createCircleInsidePolygonShapes();
        Intersection<Polygon, Circle> intersection = new IntersectionPolygonCircle();
        assertTrue(intersection.test(poly, circle));
    }

    @Test
    void testForCircleNotInsidePolygon() {
        createCircleNotInsidePolygonShapes();
        Intersection<Polygon, Circle> intersection = new IntersectionPolygonCircle();
        assertFalse(intersection.test(poly, circle));
    }
}