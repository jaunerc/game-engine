package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;

/**
 * This class represents a line segment.
 * <p>
 * A line segment is the line between a start and an end vector.
 */
public class LineSegment implements Shape {

    private final Vector2f start;
    private final Vector2f end;

    public LineSegment() {
        this(new Vector2f(0, 0), new Vector2f(1, 0));
    }

    public LineSegment(LineSegment otherLine) {
        this(new Vector2f(otherLine.getStart()), new Vector2f(otherLine.getEnd()));
    }

    public LineSegment(Vector2f start, Vector2f end) {
        this.start = start;
        this.end = end;
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
        return new Vector2f(
                0.5f * (start.x + end.x),
                0.5f * start.y + end.y);
    }

    public void setStart(float x, float y) {
        start.set(x, y);
    }

    public void setStart(Vector2f start) {
        setStart(start.x, start.y);
    }

    public Vector2f getStart() {
        return start;
    }

    public void setEnd(float x, float y) {
        end.set(x, y);
    }

    public void setEnd(Vector2f end) {
        setEnd(end.x, end.y);
    }

    public Vector2f getEnd() {
        return end;
    }

    public void set(float startX, float startY, float endX, float endY) {
        setStart(startX, startY);
        setEnd(endX, endY);
    }
}
