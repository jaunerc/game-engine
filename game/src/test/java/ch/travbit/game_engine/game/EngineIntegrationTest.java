package ch.travbit.game_engine.game;


import ch.travbit.game_engine.game.logic.GameLoop;
import ch.travbit.game_engine.game.logic.SimpleLoop;
import ch.travbit.game_engine.rendering.opengl.GlProgram;
import ch.travbit.game_engine.rendering.ui.Window;
import ch.travbit.game_engine.rendering.ui.WindowFactory;

public class EngineIntegrationTest {
    private final static String VERTEX_RESOURCE = "VertexShader.vs";
    private final static String FRAGMENT_RESOURCE = "FragmentShader.fs";

    /**
     * Private constructor to prevent object creation of this class.
     */
    private EngineIntegrationTest() {

    }

    public static void main(String[] args) {
        Window window = WindowFactory.createBasicWindow();
        GlProgram glProgram = new GlProgram(VERTEX_RESOURCE, FRAGMENT_RESOURCE);
        Game game = new PseudoGame();
        GameLoop gameLoop = new SimpleLoop(window, game);

        Engine engine = new Engine(window, game, glProgram, gameLoop);
        engine.start();
    }
}
