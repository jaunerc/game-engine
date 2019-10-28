package ch.travbit.game_engine.game;

import ch.travbit.game_engine.rendering.opengl.Mesh;
import org.joml.Vector3f;

/**
 * This class represents a basic entity in the game. Each entity has a position and a mesh for the buffers.
 */
public abstract class Entity {

    private Mesh mesh;
    private Vector3f position;

    public Entity(Mesh mesh) {
        this.mesh = mesh;
        position = new Vector3f();
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public abstract void update();

    public void render() {
        mesh.render();
    }
}
