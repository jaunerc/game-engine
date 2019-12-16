package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PolygonTest {

    @Test
    public void testCorrectCentroidWithSquare() {
        Polygon square = new Polygon();
        square.set(0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f);
        Vector2f expectedCentroid = new Vector2f(0.5f, 0.5f);
        Assertions.assertEquals(expectedCentroid, square.calcCentroid());
    }

    @Test
    public void testCorrectCentroidWithTriangle() {
        Polygon triangle = new Polygon();
        triangle.set(0f, 0f, 1f, 1f, 1f, 0f);
        Vector2f expectedCentroid = new Vector2f(2f / 3, 1f / 3);
        Assertions.assertEquals(expectedCentroid, triangle.calcCentroid());
    }

    @Test
    public void testCentroidWithZeroArea() {
        Polygon polygon = new Polygon();
        Assertions.assertThrows(ArithmeticException.class, polygon::calcCentroid);
    }

    @Test
    public void testSetPosition() {
        Polygon polygon = new Polygon();
        polygon.set(0f, 0f, 0f, 1f, 1f, 1f, 1f, 0f);
        Vector2f nextPosition = new Vector2f(1f, 1f);
        polygon.setShapeAtPosition(nextPosition);
        Assertions.assertEquals(nextPosition, polygon.calcCentroid());
    }
}
