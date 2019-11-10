package ch.travbit.game_engine.physics;

import ch.travbit.game_engine.physics.shapes.Shape;
import ch.travbit.game_engine.physics.shapes.intersection.tester.Intersection;

public class Collision<A extends Shape, B extends Shape> {

    private Body bodyA, bodyB;
    private Intersection<A, B> intersection;

    public Collision(Body bodyA, Body bodyB, Intersection<A, B> intersection) {
        this.bodyA = bodyA;
        this.bodyB = bodyB;
        this.intersection = intersection;
    }

    public boolean isCollision() {
        return intersection.test();
    }
}
