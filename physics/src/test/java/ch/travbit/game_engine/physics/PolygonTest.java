package ch.travbit.game_engine.physics;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test for {@link Polygon}
 */
class PolygonTest {

    @Test
    void set() {
        assertThrows(IllegalArgumentException.class, () ->
                new Polygon().set(1f, 2f, 3f));
    }

    @Test
    void setWithCorrectCoordinates() {
        assertDoesNotThrow(()  ->
                new Polygon().set(2f, 2f, 3f, 4f, 4f, 2f));
    }
}