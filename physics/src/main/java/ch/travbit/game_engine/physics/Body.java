package ch.travbit.game_engine.physics;

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

    private Vector2f position, velocity;
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
        velocity = new Vector2f(0f, 0f);
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

    public Vector2f getVelocity() {
        return velocity;
    }

    public void setVelocity(Vector2f velocity) {
        this.velocity = velocity;
    }

    public void setVelocity(float x, float y) {
        setVelocity(new Vector2f(x, y));
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

    public Optional<Shape> getShape() {
        return Optional.ofNullable(shape);
    }

    public void setShape(Shape shape) {
        this.shape = shape;
        setPosition(getPosition());
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
     * Notifies all observers that this body is collided.
     */
    private void notifyCollisionObservers() {
        collisionObservers.forEach(CollisionObserver::reactOnCollision);
    }

    /**
     * Whether this body has collided with the other body.
     * <p>
     * If there's a collision the collision observers from both bodies are notified.
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
            notifyCollisionObservers();
            other.notifyCollisionObservers();
        }

        return isCollided;
    }

    /**
     * Checks for all other bodies in the list if this body is collided.
     * <p>
     * If this body has no shape or the given list is null the method does nothing because a collision check is then
     * not possible. If this and another body is collided the collision observers from both bodies are notified.
     *
     * @param otherBodies a list with bodies
     */
    public void checkCollisionWithAll(List<Body> otherBodies) {
        if (getShape().isPresent() && otherBodies != null) {
            Shape myShape = getShape().get();
            otherBodies.forEach(other -> {
                if (other.getShape().isPresent()) {
                    if (IntersectionFacade.testShapeShape(myShape, other.getShape().get())) {
                        notifyCollisionObservers();
                        other.notifyCollisionObservers();
                    }
                }
            });
        }
    }

    public void update(float deltaNanos) {
        float factor = deltaNanos / 1_000_000f;
        float deltaX = velocity.x() * factor;
        float deltaY = velocity.y() * factor;
        translate(deltaX, deltaY);
    }
}
