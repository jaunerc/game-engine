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

    public LineSegment(LineSegment otherLine) {
        start = otherLine.getStart();
        end = otherLine.getEnd();
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
        return new Vector2f(start);
    }

    public void setStart(Vector2f start) {
        this.start = start;
    }

    public void setStart(float x, float y) {
        setStart(new Vector2f(x, y));
    }

    public void setEnd(float x, float y) {
        setEnd(new Vector2f(x, y));
    }

    public void set(float startX, float startY, float endX, float endY) {
        setStart(startX, startY);
        setEnd(endX, endY);
    }

    public Vector2f getEnd() {
        return new Vector2f(end);
    }

    public void setEnd(Vector2f end) {
        this.end = end;
    }
}
