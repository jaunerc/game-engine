package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.Polygon;
import ch.travbit.game_engine.physics.shapes.Shape;

/**
 * This class represents a facade for intersection testing of shapes.
 */
public class IntersectionFacade {

    public IntersectionFacade() {
    }

    /**
     * Indicates whether the given shapes intersect or not. The intersection tests only exists for concrete
     * implementations of shapes. Therefore the given shapes are downcast in the method.
     * @param shapeA the first shape
     * @param shapeB the second shape
     * @return true if the two shapes are intersecting; false otherwise
     */
    public boolean doShapesIntersect(Shape shapeA, Shape shapeB) {
        if (shapeA instanceof Polygon) {
            if (shapeB instanceof Polygon) {
                Intersection<Polygon, Polygon> polygonPolygonIntersection =
                        new IntersectionPolygonPolygon((Polygon) shapeA, (Polygon) shapeB);
                return polygonPolygonIntersection.test();
            } else if (shapeB instanceof Circle) {
                Intersection<Polygon, Circle> polygonCircleIntersection =
                        new IntersectionPolygonCircle((Polygon) shapeA, (Circle) shapeB);
                return polygonCircleIntersection.test();
            }
        } else if (shapeA instanceof Circle) {
            if (shapeB instanceof Polygon) {
                Intersection<Polygon, Circle> polygonCircleIntersection =
                        new IntersectionPolygonCircle((Polygon) shapeB, (Circle) shapeA);
                return polygonCircleIntersection.test();
            } else if (shapeB instanceof Circle) {
                Intersection<Circle, Circle> circleCircleIntersection =
                        new IntersectionCircleCircle((Circle) shapeA, (Circle) shapeB);
                return circleCircleIntersection.test();
            }
        } else {
            throw new IllegalArgumentException("No intersection test found for the given shapes.");
        }
        return false;
    }
}
