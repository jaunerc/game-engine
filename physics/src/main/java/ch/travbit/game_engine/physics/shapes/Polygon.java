package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the geometric figure polygon.
 *
 * Each polygon is defined by its list of vertices. The vertices
 * are coordinate pairs and represented by a 2d vector. The order of the vertices in the list depends the edges of
 * the polygon. A vertex is connected with its neighbors in the list and the first vertex is also connected with the
 * last vertex.
 */
public class Polygon extends Shape {

    private List<Vector2f> vertices;

    public Polygon() {
        vertices = new ArrayList<>();
    }

    /**
     * Set vertices for the polygon.
     * @param values Array of vertices (x,y)
     */
    public void set(Vector2f... values) {
        vertices.addAll(Arrays.asList(values));
    }

    /**
     * Set vertices for the polygon. The values are alternating x1, y1, x2, y2, ...
     * @param values Array of coordinates
     */
    public void set(Float... values) {
        if (values.length % 2 != 0) {
            throw new IllegalArgumentException("The given values must be a list of coordinate pairs (x, y) and " +
                    "therefore the length of the list must be even.");
        }
        for (int i = 0; i < values.length; i+=2) {
            vertices.add(new Vector2f(values[i], values[i+1]));
        }
    }

    public List<Vector2f> getVertices() {
        return vertices;
    }

    @Override
    public void translate(Vector2f translation) {
        vertices.forEach(v -> v.add(translation));
    }
}
