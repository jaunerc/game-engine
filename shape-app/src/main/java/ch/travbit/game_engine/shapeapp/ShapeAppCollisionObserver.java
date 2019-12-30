package ch.travbit.game_engine.shapeapp;

import ch.travbit.game_engine.physics.Body;
import ch.travbit.game_engine.physics.CollisionObserver;
import org.joml.Vector2f;

public class ShapeAppCollisionObserver implements CollisionObserver {

    private Vector2f changeOnCollision;

    public ShapeAppCollisionObserver(float x, float y) {
        setChangeOnCollision(x, y);
    }

    public void setChangeOnCollision(Vector2f changeOnCollision) {
        this.changeOnCollision = changeOnCollision;
    }

    public void setChangeOnCollision(float x, float y) {
        setChangeOnCollision(new Vector2f(x, y));
    }

    @Override
    public void reactOnCollision(Body myBody, Body otherBody) {
        Vector2f change = new Vector2f(otherBody.getVelocity());
        otherBody.setVelocity(change.mul(changeOnCollision));
    }
}
