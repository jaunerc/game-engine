package ch.travbit.game_engine.physics.shapes;

import org.joml.Vector2f;

/**
 * This class represents an abstract shape.
 */
public abstract class Shape {

    /**
     * Translates this shape by the given translation vector.
     * @param translation the translation vector
     */
    public abstract void translate(Vector2f translation);

    public abstract boolean intersectsWith(Shape otherShape);
}
