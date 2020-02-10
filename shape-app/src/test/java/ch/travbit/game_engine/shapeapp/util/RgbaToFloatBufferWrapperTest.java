package ch.travbit.game_engine.shapeapp.util;

import ch.travbit.game_engine.rendering.ui.RgbaColor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class RgbaToFloatBufferWrapperTest {

    @Test
    void testAdd() {
        FloatBufferWrapper<RgbaColor> wrapper = new RgbaColorToFloatBufferWrapper();
        wrapper.add(new RgbaColor(1f, 1f, 1f, 1f));
        Assertions.assertEquals(4, wrapper.toArray().length);
        Assertions.assertEquals(1, wrapper.toArray()[0]);
    }

    @Test
    void testAddAll() {
        FloatBufferWrapper<RgbaColor> wrapper = new RgbaColorToFloatBufferWrapper();
        List<RgbaColor> vectors = new ArrayList<>();
        vectors.add(new RgbaColor(1f, 1f, 0f, 1f));
        vectors.add(new RgbaColor(1f, 1f, 1f, 1f));
        wrapper.addAll(vectors);
        Assertions.assertEquals(8, wrapper.toArray().length);
        Assertions.assertEquals(0, wrapper.toArray()[2]);
    }
}
