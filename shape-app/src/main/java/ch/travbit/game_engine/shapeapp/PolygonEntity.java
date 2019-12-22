package ch.travbit.game_engine.shapeapp;

import ch.travbit.game_engine.game.Entity;
import ch.travbit.game_engine.physics.Body;
import ch.travbit.game_engine.physics.shapes.Polygon;
import ch.travbit.game_engine.rendering.opengl.Mesh;
import ch.travbit.game_engine.rendering.ui.RgbaColor;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

public class PolygonEntity extends Entity {

    private Body body;
    private Polygon polygonShape;

    private RgbaColor color;

    public PolygonEntity(Mesh mesh) {
        super(mesh);

        polygonShape = new Polygon();
        polygonShape.set(0f, 0f, 0f, 3f, 1f, 1f, 1f, 0f);

        body = new Body();
        body.setShape(polygonShape);

        color = RgbaColor.BLACK;

        List<Float> floats = new ArrayList<>();
        Vector2f centroid = polygonShape.calcCentroid();
        floats.add(centroid.x);
        floats.add(centroid.y);
        polygonShape.getVertices().forEach(vector2f -> {
            floats.add(vector2f.x);
            floats.add(vector2f.y);
        });

        float[] vertices = new float[floats.size()];
        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = floats.get(i);
        }

        int[] indices = calcIndices(vertices);

        float[] colors = createColorArray(vertices);

        mesh.storeBuffers(vertices, colors, indices);
    }

    public void setVertices(Float... values) {
        polygonShape.set(values);
    }

    private int[] calcIndices(float[] vertices) {
        List<Integer> indicesList = new ArrayList<>();

        int centroidIndex = 0;

        int numVertices = vertices.length / 2;

        for (int i = 2; i < numVertices; i++) {
            indicesList.add(centroidIndex);
            indicesList.add(i - 1);
            indicesList.add(i);
        }
        indicesList.add(centroidIndex);
        indicesList.add(numVertices - 1);
        indicesList.add(1);


        return indicesList.stream().mapToInt(value -> value).toArray();
    }

    private float[] createColorArray(float[] vertices) {
        List<Float> colorsList = new ArrayList<>();

        int numVertices = vertices.length / 2;

        for (int i = 0; i < numVertices; i++) {
            colorsList.add(color.getR());
            colorsList.add(color.getG());
            colorsList.add(color.getB());
            colorsList.add(color.getA());
        }
        colorsList.add(color.getR());
        colorsList.add(color.getG());
        colorsList.add(color.getB());
        colorsList.add(color.getA());

        float[] colors = new float[colorsList.size()];
        for (int i = 0; i < colors.length; i++) {
            colors[i] = colorsList.get(i);
        }
        return colors;
    }

    @Override
    public void update() {

    }
}
