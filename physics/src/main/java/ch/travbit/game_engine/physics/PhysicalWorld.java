package ch.travbit.game_engine.physics;

import ch.travbit.game_engine.physics.shapes.Shape;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a world with bodies.
 * <p>
 * This class holds a list of bodies and provides methods to create and update them.
 */
public class PhysicalWorld {

    private List<Body> bodies;

    public PhysicalWorld() {
        bodies = new ArrayList<>();
    }

    /**
     * Creates a new body instance.
     * <p>
     * The body instance will be added to the worlds list of bodies.
     *
     * @param shape instance will be added to the body
     * @return the created body instance.
     */
    public Body createBody(Shape shape) {
        final Body body = new Body();
        body.setShape(shape);

        bodies.add(body);
        return body;
    }

    /**
     * Updates the world.
     *
     * @param deltaNanos The delta in nanoseconds since the last invocation of this method
     */
    public void update(float deltaNanos) {
        bodies.forEach(b -> b.update(deltaNanos));
        checkCollisions();
    }

    /**
     * Checks for all bodies if there are any collisions.
     * <p>
     * This method creates a new list with all bodies in the world. The first body is removed and checked with all
     * remaining bodies until the list is empty. At the end, each two combination of bodies in this world is checked
     * once.
     */
    private void checkCollisions() {
        LinkedList<Body> bodiesCopy = new LinkedList<>(bodies);
        Body current;
        while ((current = bodiesCopy.pollFirst()) != null) {
            current.checkCollisionWithAll(bodiesCopy);
        }
    }
}
