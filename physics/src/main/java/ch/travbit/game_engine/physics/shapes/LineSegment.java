package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;

/**
 * This class represents a line segment.
 * <p>
 * A line segment is the line between a start and an end vector.
 */
public class LineSegment implements Shape {

    private Vector2f start;
    private Vector2f end;

    public LineSegment() {
    }

    @Override
    public void translate(Vector2f translation) {
        start.add(translation);
        end.add(translation);
    }

    /**
     * Calculates the centroid of this line segment. This is just the point at the center of the line segment.
     *
     * @return the centroid
     */
    @Override
    public Vector2f calcCentroid() {
        Vector2f centroid = new Vector2f(
                0.5f * (start.x + end.x),
                0.5f * start.y + end.y);
        return centroid;
    }

    public Vector2f getStart() {
        return start;
    }

    public void setStart(Vector2f start) {
        this.start = start;
    }

    public Vector2f getEnd() {
        return end;
    }

    public void setEnd(Vector2f end) {
        this.end = end;
    }
}
