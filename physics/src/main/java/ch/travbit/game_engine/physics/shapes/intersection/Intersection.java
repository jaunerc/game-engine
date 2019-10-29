package ch.travbit.game_engine.physics.shapes.intersection;

import ch.travbit.game_engine.physics.shapes.Shape;

/**
 * This interface provides methods to test if a shape intersects with another shape.
 * @param <A> the first shape type
 * @param <B> the second shape type
 */
public interface Intersection<A extends Shape, B extends Shape> {

    /**
     * Indicates whether the two shape are intersecting.
     * @param shapeA the first shape to test intersecting
     * @param shapeB the second shape to test intersecting
     * @return true if the two shapes are intersecting; false otherwise
     */
    boolean test(A shapeA, B shapeB);
}
