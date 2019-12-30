package ch.travbit.game_engine.physics;

/**
 * This interface provides a method to react on a collision.
 * <p>
 * It acts as an observer in the body class.
 */
public interface CollisionObserver {

    /**
     * React on a collision.
     * <p>
     * A collision has occurred and the observer is notified.
     */
    void reactOnCollision(Body myBody, Body otherBody);
}
