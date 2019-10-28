package ch.travbit.game_engine.game.logic;

/**
 * This interface provides methods for the game loop.
 */
public interface GameLoop {

    /**
     * Initialize the game loop.
     */
    void init();

    /**
     * Starts the game loop.
     * @param glProgramId The id of the OpenGL program
     */
    void start(int glProgramId);
}
