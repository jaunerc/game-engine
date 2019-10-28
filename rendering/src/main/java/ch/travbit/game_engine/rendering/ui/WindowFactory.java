package ch.travbit.game_engine.rendering.ui;

/**
 * This class provides a factory to create glfw windows
 */
public final class WindowFactory {

    private WindowFactory() {}

    /**
     * Creates a simple squared window with 800x800 pixels.
     * @return the produced window
     */
    public static Window createBasicWindow() {
        return new GlfwWindow.Builder()
                .setHeight(800)
                .setWidth(800)
                .setTitle("basic window")
                .build();
    }

}
