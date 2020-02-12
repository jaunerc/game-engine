package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CircleTest {

    @Test
    public void Center_Initial_CenterIsOrigin() {
        Circle circle = new Circle();
        Assertions.assertEquals(new Vector2f(0, 0), circle.getCenter());
        Assertions.assertEquals(new Vector2f(0, 0), circle.calcCentroid());
    }

    @Test
    public void Center_AfterTranslation_CenterIsCorrect() {
        Circle circle = new Circle();
        circle.translate(new Vector2f(10, -10));
        Assertions.assertEquals(new Vector2f(10, -10), circle.getCenter());
        Assertions.assertEquals(new Vector2f(10, -10), circle.calcCentroid());
    }
}
