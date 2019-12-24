package ch.travbit.game_engine.shapeapp.util;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a wrapper for indices.
 * <p>
 * The data of indices is stored in a list. The data structure in the result array looks like {i1, i2, i3, ...}
 */
public class IndicesToIntBufferWrapper implements IntBufferWrapper<Integer> {

    private List<Integer> integers;

    public IndicesToIntBufferWrapper() {
        integers = new ArrayList<>();
    }

    @Override
    public void add(Integer data) {
        integers.add(data);
    }

    @Override
    public Integer[] toArray() {
        return integers.toArray(new Integer[0]);
    }

    @Override
    public int[] toPrimitiveArray() {
        return integers.stream().mapToInt(value -> value).toArray();
    }
}
