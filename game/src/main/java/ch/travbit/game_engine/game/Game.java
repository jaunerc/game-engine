package ch.travbit.game_engine.game;

/**
 * This interface provides methods to update and run the game.
 */
public interface Game {

    /**
     * Initialize the game.
     * @param programId The id of the opengl program.
     */
    void init(int programId);

    /**
     * Update the game entities.
     *
     * @param deltaNanos The delta in nanoseconds since the last invocation of this method
     */
    void update(float deltaNanos);

    /**
     * Render the game.
     */
    void render();
}
