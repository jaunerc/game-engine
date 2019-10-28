package ch.travbit.game_engine.game.logic;

import ch.travbit.game_engine.game.Game;
import ch.travbit.game_engine.rendering.ui.Window;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * This class represents a simple game loop that runs as fast as possible without any synchronization.
 */
public final class SimpleLoop implements GameLoop {

    private Window window;
    private Game game;
    private StopWatch stopWatch;

    public SimpleLoop(Window window, Game game) {
        this.window = window;
        this.game = game;
        stopWatch = new StopWatch();
    }

    @Override
    public void init() {
        stopWatch.interval();
    }

    @Override
    public void start(int glProgramId) {
        while (!window.shouldClose()) {
            glClear(GL_COLOR_BUFFER_BIT);

            stopWatch.interval();
            game.update(stopWatch.getLastTimeDeltaNano());

            glUseProgram(glProgramId);

            game.render();

            glUseProgram(0);

            window.update();
        }
    }
}
