package ch.travbit.game_engine.physics.shapes.intersection.tester;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.Polygon;
import org.joml.Intersectionf;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.List;

/**
 * This class represents an intersection tester for a polygon and a circle.
 */
public class IntersectionPolygonCircle extends Intersection<Polygon, Circle> {


    public IntersectionPolygonCircle(Polygon shapeA, Circle shapeB) {
        super(shapeA, shapeB);
    }

    public IntersectionPolygonCircle() {
    }

    @Override
    public boolean test() {
        List<Vector2f> polygonVertices = getShapeA().getVertices();
        Vector2f vertexA;
        Vector2f vertexB;
        Vector2f circleCenter = getShapeB().getCenter();
        float circleRadius = getShapeB().getRadius();
        Vector3f closestPoint = new Vector3f();

        for (int i = 0; i < polygonVertices.size(); i++) {
            vertexA = polygonVertices.get(i);

            if (i == polygonVertices.size() - 1) {
                vertexB = polygonVertices.get(0);
            } else {
                vertexB = polygonVertices.get(i + 1);
            }

            Intersectionf.findClosestPointOnLineSegment(vertexA.x, vertexA.y, 0,
                    vertexB.x, vertexB.y, 0,
                    circleCenter.x, circleCenter.y, 0, closestPoint);

            float distanceClosestToCircleCenter = circleCenter.distance(closestPoint.x, closestPoint.y);

            if (distanceClosestToCircleCenter < circleRadius) {
                return true;
            }
        }

        int intersectionCounter = 0;
        for (int i = 0; i < polygonVertices.size(); i++) {
            Vector2f rayDirection = new Vector2f(1f, 0f);
            vertexA = polygonVertices.get(i);

            if (i == polygonVertices.size() - 1) {
                vertexB = polygonVertices.get(0);
            } else {
                vertexB = polygonVertices.get(i + 1);
            }

            float t = Intersectionf.intersectRayLineSegment(circleCenter, rayDirection, vertexA, vertexB);

            if (t == -1.0f) {
                intersectionCounter++;
            }
        }

        return intersectionCounter % 2 != 0;
    }
}
