package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.LineSegment;
import ch.travbit.game_engine.physics.shapes.Polygon;
import ch.travbit.game_engine.physics.shapes.Shape;

public class IntersectionFacade {

    private static Intersection<Polygon, Polygon> polygonPolygonIntersection;
    private static Intersection<Polygon, Circle> polygonCircleIntersection;
    private static Intersection<LineSegment, Polygon> lineSegmentPolygonIntersection;
    private static Intersection<LineSegment, LineSegment> lineSegmentLineSegmentIntersection;
    private static Intersection<LineSegment, Circle> lineSegmentCircleIntersection;
    private static Intersection<Circle, Circle> circleCircleIntersection;

    static {
        polygonPolygonIntersection = new IntersectionPolygonPolygon();
        polygonCircleIntersection = new IntersectionPolygonCircle();
        lineSegmentPolygonIntersection = new IntersectionLinePolygon();
        lineSegmentLineSegmentIntersection = new IntersectionLineLine();
        lineSegmentCircleIntersection = new IntersectionLineCircle();
        circleCircleIntersection = new IntersectionCircleCircle();
    }

    private IntersectionFacade() {
    }

    public static boolean testShapeShape(Shape A, Shape B) {
        if (A instanceof Polygon) {
            return IntersectionFacade.testPolygonShape((Polygon) A, B);
        } else if (A instanceof Circle) {
            return IntersectionFacade.testCircleShape((Circle) A, B);
        } else if (A instanceof LineSegment) {
            return IntersectionFacade.testLineSegmentShape((LineSegment) A, B);
        }
        return false;
    }

    public static boolean testPolygonShape(Polygon polygon, Shape B) {
        if (B instanceof Polygon) {
            return testPolygonPolygon(polygon, (Polygon) B);
        } else if (B instanceof Circle) {
            return testPolygonCircle(polygon, (Circle) B);
        } else if (B instanceof LineSegment) {
            return testLinePolygon((LineSegment) B, polygon);
        }
        return false;
    }

    public static boolean testCircleShape(Circle circle, Shape B) {
        if (B instanceof Polygon) {
            return testPolygonCircle((Polygon) B, circle);
        } else if (B instanceof Circle) {
            return testCircleCircle(circle, (Circle) B);
        } else if (B instanceof LineSegment) {
            return testLineCircle((LineSegment) B, circle);
        }
        return false;
    }

    public static boolean testLineSegmentShape(LineSegment lineSegment, Shape B) {
        if (B instanceof Polygon) {
            return testLinePolygon(lineSegment, (Polygon) B);
        } else if (B instanceof Circle) {
            return testLineCircle(lineSegment, (Circle) B);
        } else if (B instanceof LineSegment) {
            return testLineLine(lineSegment, (LineSegment) B);
        }
        return false;
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    private static boolean test(Intersection intersection, Shape A, Shape B) {
        intersection.setShapeA(A);
        intersection.setShapeB(B);
        return intersection.test();
    }

    private static boolean testPolygonPolygon(Polygon polygonA, Polygon polygonB) {
        return test(polygonPolygonIntersection, polygonA, polygonB);
    }

    private static boolean testPolygonCircle(Polygon polygon, Circle circle) {
        return test(polygonCircleIntersection, polygon, circle);
    }

    private static boolean testLinePolygon(LineSegment lineSegment, Polygon polygon) {
        return test(lineSegmentPolygonIntersection, lineSegment, polygon);
    }

    private static boolean testCircleCircle(Circle A, Circle B) {
        return test(circleCircleIntersection, A, B);
    }

    private static boolean testLineCircle(LineSegment A, Circle B) {
        return test(lineSegmentCircleIntersection, A, B);
    }

    private static boolean testLineLine(LineSegment lineSegmentA, LineSegment lineSegmentB) {
        return test(lineSegmentLineSegmentIntersection, lineSegmentA, lineSegmentB);
    }
}
