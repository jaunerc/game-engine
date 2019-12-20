package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;

/**
 * This class represents an abstract shape.
 */
public interface Shape {

    /**
     * Translates this shape by the given translation vector.
     *
     * @param translation the translation vector
     */
    void translate(Vector2f translation);

    /**
     * Sets the shape at the given position. The default implementation use the centroid as the old position to
     * calculate the necessary translation to the new position.
     *
     * @param newPosition the new position of the shape
     */
    default void setShapeAtPosition(Vector2f newPosition) {
        Vector2f oldPosition = calcCentroid();
        Vector2f translation = new Vector2f(newPosition.x - oldPosition.x, newPosition.y - oldPosition.y);
        translate(translation);
    }

    /**
     * Calculates the centroid for the shape.
     *
     * @return the centroid
     */
    Vector2f calcCentroid();
}
