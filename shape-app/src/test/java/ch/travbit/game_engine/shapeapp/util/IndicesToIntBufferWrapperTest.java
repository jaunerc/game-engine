package ch.travbit.game_engine.shapeapp.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class IndicesToIntBufferWrapperTest {

    @Test
    void testAdd() {
        IntBufferWrapper<Integer> wrapper = new IndicesToIntBufferWrapper();
        wrapper.add(2);
        wrapper.add(3);
        Assertions.assertEquals(2, wrapper.toArray().length);
        Assertions.assertEquals(2, wrapper.toArray()[0]);
    }

    @Test
    void testAddAll() {
        IntBufferWrapper<Integer> wrapper = new IndicesToIntBufferWrapper();
        List<Integer> integers = new ArrayList<>();
        integers.add(2);
        integers.add(3);
        wrapper.addAll(integers);
        Assertions.assertEquals(2, wrapper.toPrimitiveArray()[0]);
    }
}
