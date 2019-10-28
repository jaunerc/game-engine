package ch.travbit.game_engine.physics;

import org.joml.Vector2f;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for {@link IntersectingImpl}
 */
class IntersectingImplTest {

    Polygon A = new Polygon();
    Polygon B = new Polygon();

    void createIntersectingPolygons() {
        A.set(new Vector2f(0,0), new Vector2f(1, 0));
        B.set(new Vector2f(0.5f,0.5f), new Vector2f(0.5f, -0.5f));
    }

    @Test
    void isPolyPolyIntersecting() {
        createIntersectingPolygons();
        Intersecting intersecting = new IntersectingImpl();
        assertTrue(intersecting.isPolyPolyIntersecting(A, B));
    }
}