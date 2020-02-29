package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the geometric figure polygon.
 * <p>
 * Each polygon is defined by its list of vertices. The vertices
 * are coordinate pairs and represented by a 2d vector. The order of the vertices in the list depends the edges of
 * the polygon. A vertex is connected with its neighbors in the list and the first vertex is also connected with the
 * last vertex.
 */
public class Polygon implements Shape {

    private final List<Vector2f> vertices;

    public Polygon() {
        vertices = new ArrayList<>();
    }

    /**
     * Set vertices for the polygon.
     *
     * @param values Array of vertices (x,y)
     */
    public void set(Vector2f... values) {
        vertices.clear();
        vertices.addAll(Arrays.asList(values));
    }

    /**
     * Set vertices for the polygon. The values are alternating x1, y1, x2, y2, ...
     *
     * @param values Array of coordinates
     */
    public void set(Float... values) {
        if (values.length % 2 != 0) {
            throw new IllegalArgumentException("The given values must be a list of coordinate pairs (x, y) and " +
                    "therefore the length of the list must be even.");
        }
        vertices.clear();
        for (int i = 0; i < values.length; i += 2) {
            vertices.add(new Vector2f(values[i], values[i + 1]));
        }
    }

    public List<Vector2f> getVertices() {
        return new ArrayList<>(vertices);
    }

    /**
     * Calculates the centroid of this polygon. This is also known as the centre of gravity. The calculation is only
     * correct if the polygon is not self intersecting.
     *
     * @return the centroid
     * @see <a href="https://www.seas.upenn.edu/~sys502/extra_materials/Polygon%20Area%20and%20Centroid.pdf" />
     */
    @Override
    public Vector2f calcCentroid() {
        Vector2f centroid = new Vector2f();
        Vector2f current;
        Vector2f next;
        float x = 0;
        float y = 0;
        float area = 0;

        /*
         * The loop calculates the area between two vertices that are neighbors in the list of vertices. The last
         * vertex is processed with the first vertex.
         */
        for (int i = 0, j = 1; i < vertices.size(); i++, j = (j + 1) % vertices.size()) {
            current = vertices.get(i);
            next = vertices.get(j);
            float rightHandSide = (current.x * next.y - next.x * current.y);
            x += (current.x + next.x) * rightHandSide;
            y += (current.y + next.y) * rightHandSide;
            area += calcArea(current, next);
        }

        float signedArea = area * 0.5f;

        if (signedArea != 0) {
            float multiplier = 1f / (6f * signedArea);
            centroid.set(x * multiplier, y * multiplier);
        } else {
            throw new ArithmeticException("The centroid of this polygon is not defined because the signed area is " +
                    "zero");
        }
        return centroid;
    }

    /**
     * Calculates the area between the two vertices. Attention, the returned value can be 0.0.
     *
     * @param vertexA the first vertex
     * @param vertexB the second vertex
     * @return the signed area
     */
    private float calcArea(final Vector2f vertexA, final Vector2f vertexB) {
        return vertexA.x * vertexB.y - vertexB.x * vertexA.y;
    }

    @Override
    public void translate(final Vector2f translation) {
        vertices.forEach(v -> v.add(translation));
    }

    @Override
    public String toString() {
        return "Polygon{" +
                "vertices=" + vertices +
                '}';
    }
}
