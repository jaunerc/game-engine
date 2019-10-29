package ch.travbit.game_engine.physics;

import ch.travbit.game_engine.physics.shapes.Shape;
import org.joml.Vector2f;

public class Body {

    private Vector2f position;
    private Shape shape;

    public Body() {
        position = new Vector2f(0f,0f);
    }

    public void translate(Vector2f translation) {
        position.add(translation);
        shape.translate(translation);
    }

    public Vector2f getPosition() {
        return position;
    }

    public Shape getShape() {
        return shape;
    }
}
