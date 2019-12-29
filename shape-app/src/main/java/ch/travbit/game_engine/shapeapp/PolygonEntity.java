package ch.travbit.game_engine.shapeapp;

import ch.travbit.game_engine.game.Entity;
import ch.travbit.game_engine.physics.Body;
import ch.travbit.game_engine.physics.CollisionObserver;
import ch.travbit.game_engine.physics.shapes.Polygon;
import ch.travbit.game_engine.rendering.opengl.Mesh;
import ch.travbit.game_engine.rendering.ui.RgbaColor;
import ch.travbit.game_engine.shapeapp.util.FloatBufferWrapper;
import ch.travbit.game_engine.shapeapp.util.IndicesToIntBufferWrapper;
import ch.travbit.game_engine.shapeapp.util.RgbaColorToFloatBufferWrapper;
import ch.travbit.game_engine.shapeapp.util.VectorToFloatBufferWrapper;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class PolygonEntity extends Entity implements CollisionObserver {

    private Body body;

    private RgbaColor color;

    private float[] vertices;
    private int[] indices;
    private float[] colors;

    public PolygonEntity(Mesh mesh) {
        this(mesh, new Body());
    }

    public PolygonEntity(Mesh mesh, Body body) {
        super(mesh);
        this.body = body;

        color = RgbaColor.randomColorNoTransparency();

        defineVertices();
        defineIndices(vertices);
        defineColors(vertices);

        mesh.storeBuffers(vertices, colors, indices);
}

    private void defineVertices() {
        if (body.getShape().isPresent() && body.getShape().get() instanceof Polygon) {
            Polygon polygonShape = (Polygon) body.getShape().get();
            final FloatBufferWrapper<Vector2f> floatBufferWrapper = new VectorToFloatBufferWrapper();
            final Vector2f centroid = polygonShape.calcCentroid();
            floatBufferWrapper.add(centroid);
            floatBufferWrapper.addAll(polygonShape.getVertices());
            vertices = floatBufferWrapper.toPrimitiveArray();
        } else {
            vertices = new float[0];
        }
    }

    private void defineIndices(float[] vertices) {
        IndicesToIntBufferWrapper wrapper = new IndicesToIntBufferWrapper();

        int centroidIndex = 0;
        int numVertices = vertices.length / 2;

        for (int i = 2; i < numVertices; i++) {
            wrapper.add(centroidIndex);
            wrapper.add(i - 1);
            wrapper.add(i);
        }
        wrapper.add(centroidIndex);
        wrapper.add(numVertices - 1);
        wrapper.add(1);


        indices = wrapper.toPrimitiveArray();
    }

    private void defineColors(float[] vertices) {
        FloatBufferWrapper<RgbaColor> wrapper = new RgbaColorToFloatBufferWrapper();

        int numVertices = vertices.length / 2;

        for (int i = 0; i < numVertices; i++) {
            wrapper.add(color);
        }
        wrapper.add(color);
        colors = wrapper.toPrimitiveArray();
    }

    @Override
    public void update() {
        defineColors(vertices);
        getMesh().storeBuffers(vertices, colors, indices);
    }

    @Override
    public void setPosition(Vector3f position) {
        super.setPosition(position);
        body.setPosition(getPosition().x, getPosition().y);
    }

    @Override
    public void reactOnCollision() {
        color.set(1f, 0f, 0f, 1f);
    }
}
