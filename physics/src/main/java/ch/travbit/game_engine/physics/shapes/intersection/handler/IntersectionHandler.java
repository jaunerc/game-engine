package ch.travbit.game_engine.physics.shapes.intersection.handler;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.Polygon;
import ch.travbit.game_engine.physics.shapes.Shape;
import ch.travbit.game_engine.physics.shapes.intersection.tester.Intersection;

public class IntersectionHandler<T extends Shape> {

    private Intersection<T, Polygon> polygonIntersection;
    private Intersection<T, Circle> circleIntersection;

    public IntersectionHandler(Intersection<T, Polygon> polygonIntersection, Intersection<T, Circle> circleIntersection) {
        this.polygonIntersection = polygonIntersection;
        this.circleIntersection = circleIntersection;
    }

    public boolean intersectWithPolygon(Polygon polygon) {
        polygonIntersection.setShapeB(polygon);
        return polygonIntersection.test();
    }

    public boolean intersectWithCircle(Circle circle) {
        circleIntersection.setShapeB(circle);
        return circleIntersection.test();
    }

    public boolean intersectWithShape(Shape shape) {
        if (shape instanceof Polygon) {
            return intersectWithPolygon((Polygon) shape);
        } else if (shape instanceof Circle) {
            return intersectWithCircle((Circle) shape);
        }
        return false;
    }
}
