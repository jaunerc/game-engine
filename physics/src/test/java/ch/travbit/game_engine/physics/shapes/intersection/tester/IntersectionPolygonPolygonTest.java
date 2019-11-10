package ch.travbit.game_engine.physics.shapes.intersection.tester;

import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Vector2f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

class IntersectionPolygonPolygonTest {
    Polygon A = new Polygon();
    Polygon B = new Polygon();

    void createIntersectingPolygons() {
        A.set(new Vector2f(0,0), new Vector2f(1, 0));
        B.set(new Vector2f(0.5f,0.5f), new Vector2f(0.5f, -0.5f));
    }

    @Test
    void testIntersection() {
        Intersection<Polygon, Polygon> intersection = new IntersectionPolygonPolygon(A, B);
        assertTrue(intersection.test());
    }
}