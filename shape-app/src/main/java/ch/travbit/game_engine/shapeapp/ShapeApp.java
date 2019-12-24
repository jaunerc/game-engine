package ch.travbit.game_engine.shapeapp;

import ch.travbit.game_engine.game.Entity;
import ch.travbit.game_engine.game.Game;
import ch.travbit.game_engine.physics.Body;
import ch.travbit.game_engine.physics.shapes.Polygon;
import ch.travbit.game_engine.rendering.opengl.Mesh;
import ch.travbit.game_engine.rendering.opengl.variables.Attribute;
import ch.travbit.game_engine.rendering.opengl.variables.Loader;
import ch.travbit.game_engine.rendering.opengl.variables.Uniform;
import org.joml.Matrix3f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_FLOAT;

public class ShapeApp implements Game {

    private List<Entity> entities;
    private Uniform<Matrix3f> transformMatrix;
    private Uniform<Matrix3f> projectionMatrix;

    public ShapeApp() {
        entities = new ArrayList<>();
    }

    @Override
    public void init(int programId) {
        Loader loader = new Loader(programId);
        projectionMatrix = loader.loadUniformMatrix3("projection");
        transformMatrix = loader.loadUniformMatrix3("transformation");

        final Attribute posAttribute = loader.loadAttribute("vertexPosition", GL_FLOAT, 2);
        final Attribute colorAttribute = loader.loadAttribute("vertexColor", GL_FLOAT, 4);
        final Mesh mesh = new Mesh(posAttribute, colorAttribute);

        Polygon polygon = new Polygon();
        polygon.set(0f, 0f, 1f, 1f, 1f, -1f);

        Body body = new Body();
        body.setShape(polygon);

        PolygonEntity polygonEntity = new PolygonEntity(mesh, body);

        entities.add(polygonEntity);
    }

    @Override
    public void update(float deltaMillis) {

    }

    @Override
    public void render() {
        Matrix3f projection = new Matrix3f();
        int screenWidth = 300;
        int screenHeight = 300;
        float invAspect = (float) screenWidth / screenHeight;

        projection.scaling(0.25f * invAspect);
        projectionMatrix.bind(projection);

        entities.forEach(entity -> {
            entity.update();
            Matrix3f transform = new Matrix3f();
            Matrix3f translationMat = create2dTranslationMat(entity.getPosition());
            transform.mulLocal(translationMat);
            transformMatrix.bind(transform);
            entity.render();
        });
    }

    private Matrix3f create2dTranslationMat(Vector3f translation) {
        Matrix3f translationMat = new Matrix3f().identity();
        translationMat.setColumn(2, translation);
        return translationMat;
    }
}
