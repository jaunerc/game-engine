package ch.travbit.game_engine.rendering.opengl;

import ch.travbit.game_engine.rendering.opengl.variables.Attribute;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15C.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;

/**
 * This class represents a collection of buffers. A mesh instance collects the buffers for the position and color of
 * an object that is rendered.
 */
public class Mesh {
    private Attribute position;
    private Attribute color;
    private int verticesBufferId;
    private int indicesBufferId;
    private int colorsBufferId;

    private int indicesCount;

    public Mesh(Attribute position, Attribute color) {
        this.position = position;
        this.color = color;
        verticesBufferId = -1;
        indicesBufferId = -1;
        colorsBufferId = -1;
        setIndicesCount(0);
    }

    public void setIndicesCount(int indicesCount) {
        this.indicesCount = indicesCount;
    }

    /**
     * Saves the given vertices and indices to the OpenGL buffers.
     *
     * @param vertices a list of vertices
     * @param colors   a list of rgba colors
     * @param indices  a list of indices
     */
    public void storeBuffers(float[] vertices, float[] colors, int[] indices) {
        storeVerticesBuffer(vertices);
        storeColorsBuffer(colors);
        storeIndicesBuffer(indices);
    }

    public void storeVerticesBuffer(float[] vertices) {
        FloatBuffer verticesBuffer = null;
        try {
            verticesBufferId = glGenBuffers();
            verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
            verticesBuffer.put(vertices).flip();
            glBindBuffer(GL_ARRAY_BUFFER, verticesBufferId);
            glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        } finally {
            if (verticesBuffer != null) {
                MemoryUtil.memFree(verticesBuffer);
            }
        }
    }

    public void storeColorsBuffer(float[] colors) {
        FloatBuffer colorsBuffer = null;
        try {
            colorsBufferId = glGenBuffers();
            colorsBuffer = MemoryUtil.memAllocFloat(colors.length);
            colorsBuffer.put(colors).flip();
            glBindBuffer(GL_ARRAY_BUFFER, colorsBufferId);
            glBufferData(GL_ARRAY_BUFFER, colorsBuffer, GL_STATIC_DRAW);
        } finally {
            if (colorsBuffer != null) {
                MemoryUtil.memFree(colorsBuffer);
            }
        }
    }

    public void storeIndicesBuffer(int[] indices) {
        IntBuffer indicesBuffer = null;
        try {
            setIndicesCount(indices.length);

            indicesBufferId = glGenBuffers();
            indicesBuffer = MemoryUtil.memAllocInt(indicesCount);
            indicesBuffer.put(indices).flip();
            glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
            glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);
        } finally {
            if (indicesBuffer != null) {
                MemoryUtil.memFree(indicesBuffer);
            }
        }
    }

    /**
     * Renders this mesh. This function binds the buffers and invokes a OpenGL draw command.
     */
    public void render() {
        // vertices
        glBindBuffer(GL_ARRAY_BUFFER, verticesBufferId);
        position.bind();
        position.enable(true);

        // colors
        glBindBuffer(GL_ARRAY_BUFFER, colorsBufferId);
        color.bind();
        color.enable(true);

        // indices
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, indicesBufferId);
        glDrawElements(GL_TRIANGLES, indicesCount, GL_UNSIGNED_INT, 0);

        // Restore state
        position.enable(false);
        glDisableVertexAttribArray(indicesBufferId);
    }
}
