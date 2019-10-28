package ch.travbit.game_engine.physics;

/**
 * This interface provides a method to detect whether two figures are intersecting or not.
 */
public interface Intersecting {

    /**
     * Checks whether two polygons are intersecting or not. Any implementation should be symmetric so that
     * isPolyPolyIntersecting(A, B) == isPolyPolyIntersecting(B, A).
     * @param polyA The first polygon
     * @param polyB The second polygon
     * @return true if the polygons are intersecting false otherwise
     */
    boolean isPolyPolyIntersecting(Polygon polyA, Polygon polyB);
}
