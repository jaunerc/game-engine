package ch.travbit.game_engine.shapeapp.util;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a wrapper for 2d vectors with float coordinates.
 * <p>
 * The data of vectors is stored in a list. For each vector the wrapper saves the x and y value in the list. The
 * coordinate pairs are neighbors in the list. The data structure in the result array looks like {x1, y1, x2, y2, ...}
 */
public class VectorToFloatBufferWrapper implements FloatBufferWrapper<Vector2f> {

    private List<Float> floats;

    public VectorToFloatBufferWrapper() {
        floats = new ArrayList<>();
    }

    @Override
    public void add(Vector2f data) {
        floats.add(data.x);
        floats.add(data.y);
    }

    @Override
    public Float[] toArray() {
        return floats.toArray(new Float[0]);
    }

    @Override
    public float[] toPrimitiveArray() {
        float[] primitiveArray = new float[floats.size()];

        for (int i = 0; i < floats.size(); i++) {
            primitiveArray[i] = floats.get(i);
        }

        return primitiveArray;
    }
}
