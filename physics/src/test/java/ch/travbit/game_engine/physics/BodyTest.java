package ch.travbit.game_engine.physics;

import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BodyTest {

    @Test
    void testBodyTranslation() {
        Body body = new Body();
        body.translate(10f, 10f);
        Assertions.assertEquals(new Vector2f(10f, 10f), body.getPosition());
    }

    @Test
    void testSetPosition() {
        Polygon poly = new Polygon();
        poly.set(0f, 0f, 1f, 1f, 1f, 0f);

        Body body = new Body(0f, 0f, poly);
        body.setPosition(1f, 1f);

        Vector2f expected = new Vector2f(1f, 1f);
        Assertions.assertEquals(expected.x, body.getPosition().x, 0.001f);
        Assertions.assertEquals(expected.y, body.getPosition().y, 0.001f);

        body.getShape().ifPresentOrElse(shape -> {
            final Vector2f shapePosition = shape.calcCentroid();
            Assertions.assertEquals(expected.x, shapePosition.x, 0.001f);
            Assertions.assertEquals(expected.y, shapePosition.y, 0.001f);
        }, () -> Assertions.assertFalse(false));
    }
}
