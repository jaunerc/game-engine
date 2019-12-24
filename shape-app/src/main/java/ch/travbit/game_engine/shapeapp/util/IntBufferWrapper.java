package ch.travbit.game_engine.shapeapp.util;

import java.util.List;

/**
 * This interface provides methods to store elements and wrap them to an array of integers.
 *
 * @param <T> the type of data to store
 */
public interface IntBufferWrapper<T> {

    void add(T data);

    default void addAll(List<T> data) {
        data.forEach(this::add);
    }

    Integer[] toArray();

    int[] toPrimitiveArray();
}
