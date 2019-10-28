package ch.travbit.game_engine.game.logic;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for {@link StopWatch}.
 */
class StopWatchTest {

    @Test
    void interval() {
        StopWatch stopWatch = new StopWatch();
        for (int i = 0; i < 100000; i++) {
            // Pseudo loop to pass some time
        }
        stopWatch.interval();
        assertTrue(stopWatch.getLastTimeDeltaNano() > 0);
    }
}