package ch.travbit.game_engine.physics.shapes.intersection.tester;

import ch.travbit.game_engine.physics.shapes.LineSegment;
import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Vector2f;

import java.util.List;

public class IntersectionLinePolygon extends Intersection<LineSegment, Polygon> {

    public IntersectionLinePolygon() {

    }

    public IntersectionLinePolygon(LineSegment shapeA, Polygon shapeB) {
        super(shapeA, shapeB);
    }

    @Override
    public boolean test() {
        List<Vector2f> vertices = getShapeB().getVertices();
        IntersectionLineLine intersectionLineLine;
        LineSegment polygonLine = new LineSegment();

        for (int i = 0, j = 1; i < vertices.size(); i++, j = (j + 1) % vertices.size()) {
            polygonLine.setStart(vertices.get(i));
            polygonLine.setEnd(vertices.get(j));

            intersectionLineLine = new IntersectionLineLine(getShapeA(), polygonLine);
            if (intersectionLineLine.test()) {
                return true;
            }
        }

        return isRayIntersectingOddNumberOfLines(getShapeA().getStart(), vertices);
    }
}
