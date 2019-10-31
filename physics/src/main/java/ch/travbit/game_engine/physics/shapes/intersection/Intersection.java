package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Shape;

/**
 * This class provides methods to test if a shape intersects with another shape.
 * @param <A> the first shape type
 * @param <B> the second shape type
 */
public abstract class Intersection<A extends Shape, B extends Shape> {

    private A shapeA;
    private B shapeB;

    public Intersection(A shapeA, B shapeB) {
        this.shapeA = shapeA;
        this.shapeB = shapeB;
    }

    public Intersection() {
        shapeA = null;
        shapeB = null;
    }

    public A getShapeA() {
        return shapeA;
    }

    public B getShapeB() {
        return shapeB;
    }

    public void setShapeA(A shapeA) {
        this.shapeA = shapeA;
    }

    public void setShapeB(B shapeB) {
        this.shapeB = shapeB;
    }

    /**
     * Indicates whether the two shape are intersecting.
     * @return true if the two shapes are intersecting; false otherwise
     */
    public abstract boolean test();
}
