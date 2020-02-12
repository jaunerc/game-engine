package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LineSegmentTest {

    @Test
    public void Centroid_Initial_IsCorrect() {
        LineSegment lineSegment = new LineSegment();
        Assertions.assertEquals(new Vector2f(.5f, 0), lineSegment.calcCentroid());
    }

    @Test
    public void Centroid_AfterTranslation_IsCorrect() {
        LineSegment lineSegment = new LineSegment();
        lineSegment.translate(new Vector2f(10f, -10f));
        Assertions.assertEquals(new Vector2f(10.5f, -15f), lineSegment.calcCentroid());
    }
}
