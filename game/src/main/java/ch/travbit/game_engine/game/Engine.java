package ch.travbit.game_engine.game;


import ch.travbit.game_engine.game.logic.GameLoop;
import ch.travbit.game_engine.rendering.opengl.GlProgram;
import ch.travbit.game_engine.rendering.ui.Window;

import java.io.IOException;

/**
 * This class represents the engine that runs the game.
 */
public class Engine {

    private Window window;
    private Game game;
    private GlProgram glProgram;
    private GameLoop gameLoop;

    public Engine(Window window, Game game, GlProgram glProgram, GameLoop gameLoop) {
        this.window = window;
        this.game = game;
        this.glProgram = glProgram;
        this.gameLoop = gameLoop;
    }

    /**
     * Initialize the engine. The order of the calls in this method is crucial. glProgram.create() must be executed
     * after window.init(). Otherwise the OpenGL context is broken.
     * @throws IOException
     */
    private void init() throws IOException {
        System.out.println("initialize");
        window.init();
        glProgram.create();
        game.init(glProgram.getProgramId());
        gameLoop.init();
    }

    public void start() {
        try {
            init();
            gameLoop.start(glProgram.getProgramId());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            shutdown();
        }
    }

    public void shutdown() {
        System.out.println("shutdown engine");
    }
}
