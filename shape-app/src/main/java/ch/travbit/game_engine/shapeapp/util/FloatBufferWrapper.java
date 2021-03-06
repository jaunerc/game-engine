package ch.travbit.game_engine.shapeapp.util;

import java.util.List;

/**
 * This interface provides methods to store elements and wrap them to an array of floats.
 *
 * @param <T> the type of data to store
 */
public interface FloatBufferWrapper<T> {

    /**
     * Wrap the given list of Float to an array of float
     *
     * @param floats a list of Float
     * @return the array with unboxed float values
     */
    static float[] wrapToPrimitiveArray(List<Float> floats) {
        float[] primitiveArray = new float[floats.size()];

        for (int i = 0; i < floats.size(); i++) {
            primitiveArray[i] = floats.get(i);
        }

        return primitiveArray;
    }

    /**
     * Adds the data object to the underlying data structure.
     *
     * @param data the data object
     */
    void add(T data);

    /**
     * Adds all objects from the list of data to the underlying data structure. The default implementation calls the
     * add method of this class for each data object.
     *
     * @param data a list of data objects
     */
    default void addAll(List<T> data) {
        data.forEach(this::add);
    }

    /**
     * Returns a new Float array that contains the data that was added before.
     *
     * @return Float array
     */
    Float[] toArray();

    /**
     * Returns a new float array that contains the data that was added before.
     *
     * @return float array
     */
    float[] toPrimitiveArray();
}
