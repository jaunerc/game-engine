package ch.travbit.game_engine.game;


import ch.travbit.game_engine.rendering.opengl.Mesh;

public class Rectangle extends Entity {

    private final static float[] VERTICES = new float[] {
            -0.5f, -0.5f,
            0.5f, -0.5f,
            0.5f, 0.5f,
            -0.5f, 0.5f,
    };

    private final static int[] INDICES = new int[] {
            0, 1, 2,
            0, 3, 2
    };

    public Rectangle(Mesh mesh, float[] colors) {
        super(mesh);
        mesh.storeBuffers(VERTICES, colors, INDICES);
    }

    @Override
    public void update() {

    }
}
