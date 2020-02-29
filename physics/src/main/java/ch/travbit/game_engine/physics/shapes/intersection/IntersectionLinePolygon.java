package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.LineSegment;
import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Vector2f;

import java.util.List;

class IntersectionLinePolygon extends Intersection<LineSegment, Polygon> {

    public IntersectionLinePolygon() {

    }

    @Override
    public boolean test(LineSegment lineSegment, Polygon polygon) {
        List<Vector2f> vertices = polygon.getVertices();
        IntersectionLineLine intersectionLineLine;
        LineSegment polygonLine = new LineSegment();

        for (int i = 0, j = 1; i < vertices.size(); i++, j = (j + 1) % vertices.size()) {
            polygonLine.setStart(vertices.get(i));
            polygonLine.setEnd(vertices.get(j));

            intersectionLineLine = new IntersectionLineLine();
            if (intersectionLineLine.test(lineSegment, polygonLine)) {
                return true;
            }
        }

        return isRayIntersectingOddNumberOfLines(lineSegment.getStart(), vertices);
    }
}
