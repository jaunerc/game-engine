package ch.travbit.game_engine.shapeapp;

import ch.travbit.game_engine.game.Entity;
import ch.travbit.game_engine.game.Game;
import ch.travbit.game_engine.physics.Body;
import ch.travbit.game_engine.physics.CollisionObserver;
import ch.travbit.game_engine.physics.PhysicalWorld;
import ch.travbit.game_engine.physics.shapes.LineSegment;
import ch.travbit.game_engine.physics.shapes.Polygon;
import ch.travbit.game_engine.rendering.opengl.Mesh;
import ch.travbit.game_engine.rendering.opengl.variables.Attribute;
import ch.travbit.game_engine.rendering.opengl.variables.Loader;
import ch.travbit.game_engine.rendering.opengl.variables.Uniform;
import org.joml.Matrix3f;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.lwjgl.opengl.GL11.GL_FLOAT;

public class ShapeApp implements Game {

    private List<Entity> entities;
    private Uniform<Matrix3f> transformMatrix;
    private Uniform<Matrix3f> projectionMatrix;

    private PhysicalWorld physicalWorld;

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

        physicalWorld = new PhysicalWorld();

        addBoundaries();

        for (int i = 0; i < 2; i++) {
            createPolygonAtRandomPosition(new Mesh(posAttribute, colorAttribute));
        }
    }

    private void createPolygonAtRandomPosition(Mesh mesh) {
        Polygon polygon = new Polygon();
        polygon.set(0f, 0f, 1f, 1f, 1f, -1f);
        Body body = physicalWorld.createBody(polygon);
        PolygonEntity polygonEntity = new PolygonEntity(mesh, body);

        Random random = new Random();
        float randX = (random.nextFloat() * 3) - 1.5f;
        float randY = (random.nextFloat() * 3) - 1.5f;

        body.setPosition(randX, randY);
        body.setVelocity(random.nextFloat() * .001f, random.nextFloat() * .001f);

        entities.add(polygonEntity);
    }

    private void addBoundaries() {
        LineSegment horizontalLine = new LineSegment();
        horizontalLine.set(0f, 0f, 4f, 0f);
        LineSegment verticalLine = new LineSegment();
        verticalLine.set(0f, 0f, 0f, 4f);

        float pos = 2f;

        CollisionObserver collisionObserver = new ShapeAppCollisionObserver(1f, -1f);
        Body top = physicalWorld.createBody(new LineSegment(horizontalLine));
        top.setPosition(0f, pos);
        top.addCollisionObserver(collisionObserver);

        collisionObserver = new ShapeAppCollisionObserver(-1f, 1f);
        Body right = physicalWorld.createBody(new LineSegment(verticalLine));
        right.setPosition(pos, 0f);
        right.addCollisionObserver(collisionObserver);

        collisionObserver = new ShapeAppCollisionObserver(1f, -1f);
        Body bottom = physicalWorld.createBody(new LineSegment(horizontalLine));
        bottom.setPosition(0f, -pos);
        bottom.addCollisionObserver(collisionObserver);

        collisionObserver = new ShapeAppCollisionObserver(-1f, 1f);
        Body left = physicalWorld.createBody(new LineSegment(verticalLine));
        left.setPosition(-pos, 0f);
        left.addCollisionObserver(collisionObserver);
    }

    @Override
    public void update(float deltaMillis) {
        physicalWorld.update(deltaMillis);
    }

    @Override
    public void render() {
        Matrix3f projection = new Matrix3f();
        int screenWidth = 300;
        int screenHeight = 300;
        float invAspect = (float) screenWidth / screenHeight;

        projection.scaling(0.1f * invAspect);
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
