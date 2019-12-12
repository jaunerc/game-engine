package ch.travbit.game_engine.physics.shapes;

import ch.travbit.game_engine.physics.shapes.intersection.IntersectionHandler;
import org.joml.Vector2f;

/**
 * This class represents a line segment.
 * <p>
 * A line segment is the line between a start and an end vector.
 */
public class LineSegment extends Shape {

    private Vector2f start;
    private Vector2f end;
    private IntersectionHandler<LineSegment> intersectionHandler;

    public LineSegment() {
        this(null);
    }

    public LineSegment(IntersectionHandler<LineSegment> intersectionHandler) {
        this.intersectionHandler = intersectionHandler;
    }

    @Override
    public void translate(Vector2f translation) {
        start.add(translation);
        end.add(translation);
    }

    @Override
    public boolean intersectsWith(Shape otherShape) {
        return intersectionHandler.intersectWithShape(otherShape);
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
