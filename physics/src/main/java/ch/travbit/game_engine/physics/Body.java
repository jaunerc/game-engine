package ch.travbit.game_engine.physics;

import ch.travbit.game_engine.physics.shapes.Circle;
import ch.travbit.game_engine.physics.shapes.LineSegment;
import ch.travbit.game_engine.physics.shapes.Polygon;
import ch.travbit.game_engine.physics.shapes.Shape;
import ch.travbit.game_engine.physics.shapes.intersection.IntersectionFacade;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * This class represents a physical body.
 * <p>
 * A body holds information about the position in the physical world. It can detect collisions with other bodies and
 * notify observers when a collision occurs. A body can have a shape to define its collision boundaries.
 */
public class Body {

    private Vector2f position;
    private Shape shape;

    private List<CollisionObserver> collisionObservers;

    public Body() {
        this(0, 0, null);
    }

    public Body(float x, float y, Shape shape) {
        this(new Vector2f(x, y), shape);
    }

    public Body(Vector2f position, Shape shape) {
        this.position = position;
        this.shape = shape;
        collisionObservers = new ArrayList<>();
        setPosition(position);
    }

    /**
     * Translates this body by the given translation vector.
     *
     * @param translation in cartesian coordinates
     */
    public void translate(Vector2f translation) {
        position.add(translation);
        getShape().ifPresent(shape -> shape.translate(translation));
    }

    /**
     * Translates this body by the given cartesian coordinates.
     *
     * @param x translation on the x-axis
     * @param y translation on the y-axis
     */
    public void translate(float x, float y) {
        translate(new Vector2f(x, y));
    }

    /**
     * Sets the position of this body directly to the given position vector.
     *
     * @param position the new position of the body
     */
    public void setPosition(Vector2f position) {
        this.position = position;
        getShape().ifPresent(shape -> shape.setShapeAtPosition(position));
    }

    /**
     * Sets the position of this body directly by the given cartesian coordinates.
     *
     * @param x position on the x-axis
     * @param y position on the y-axis
     */
    public void setPosition(float x, float y) {
        setPosition(new Vector2f(x, y));
    }

    public Vector2f getPosition() {
        return new Vector2f(position);
    }

    public Optional<Shape> getShape() {
        return Optional.ofNullable(shape);
    }

    /**
     * Add the given object to the list of observers that are notified about a collision.
     *
     * @param observer the observer object
     */
    public void addCollisionObserver(CollisionObserver observer) {
        collisionObservers.add(observer);
    }

    /**
     * Whether this body has collided with the other body.
     *
     * @param other the other body to check for collision
     * @return true if this body collided with the other body; false otherwise
     */
    public boolean isCollidedWith(Body other) {
        boolean isCollided = false;

        if (other.getShape().isPresent() && getShape().isPresent()) {
            Shape myShape = getShape().get();
            isCollided = IntersectionFacade.testShapeShape(myShape, other.getShape().get());
        }

        if (isCollided) {
            collisionObservers.forEach(CollisionObserver::reactOnCollision);
        }

        return isCollided;
    }
}
