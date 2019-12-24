package ch.travbit.game_engine.shapeapp.util;

import ch.travbit.game_engine.rendering.ui.RgbaColor;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a wrapper for rgba colors with float values.
 * <p>
 * The data of colors is stored in a list. For each color the wrapper saves four value in the list. The data
 * structure in the result array looks like {r1, g1, b1, a1, r2, g2, ...}
 */
public class RgbaColorToFloatBufferWrapper implements FloatBufferWrapper<RgbaColor> {

    private List<Float> floats;

    public RgbaColorToFloatBufferWrapper() {
        floats = new ArrayList<>();
    }

    @Override
    public void add(RgbaColor data) {
        floats.add(data.getR());
        floats.add(data.getG());
        floats.add(data.getB());
        floats.add(data.getA());
    }

    @Override
    public Float[] toArray() {
        return floats.toArray(new Float[0]);
    }

    @Override
    public float[] toPrimitiveArray() {
        return FloatBufferWrapper.wrapToPrimitiveArray(floats);
    }
}
