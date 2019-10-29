package ch.travbit.game_engine.physics.intersection;

import ch.travbit.game_engine.physics.shapes.Polygon;
import ch.travbit.game_engine.physics.shapes.intersection.Intersection;
import ch.travbit.game_engine.physics.shapes.intersection.IntersectionPolygonPolygon;
import org.joml.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class IntersectionPolygonPolygonTest {
    Polygon A = new Polygon();
    Polygon B = new Polygon();

    void createIntersectingPolygons() {
        A.set(new Vector2f(0,0), new Vector2f(1, 0));
        B.set(new Vector2f(0.5f,0.5f), new Vector2f(0.5f, -0.5f));
    }
    @Test
    void testIntersection() {
        createIntersectingPolygons();
        Intersection<Polygon, Polygon> intersection = new IntersectionPolygonPolygon();
        Assertions.assertTrue(intersection.test(A, B));
    }
}