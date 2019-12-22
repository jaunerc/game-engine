package ch.travbit.game_engine.shapeapp.util;

import org.joml.Vector2f;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class VectorToFloatBufferWrapperTest {

    @Test
    void testAdd() {
        FloatBufferWrapper<Vector2f> wrapper = new VectorToFloatBufferWrapper();
        wrapper.add(new Vector2f(2, 3));
        Assertions.assertEquals(2, wrapper.toArray().length);
        Assertions.assertEquals(2, wrapper.toArray()[0]);
    }

    @Test
    void testAddAll() {
        FloatBufferWrapper<Vector2f> wrapper = new VectorToFloatBufferWrapper();
        List<Vector2f> vectors = new ArrayList<>();
        vectors.add(new Vector2f(1, 1));
        vectors.add(new Vector2f(0, 0));
        wrapper.addAll(vectors);
        Assertions.assertEquals(4, wrapper.toArray().length);
        Assertions.assertEquals(0, wrapper.toArray()[2]);
    }
}
