package ch.travbit.game_engine.game.logic;


import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Test for {@link StopWatch}.
 */
class StopWatchTest {

    @Test
    void interval_CurrentTimeIsLastTime_DeltaIsZero() {
        StopWatch stopWatch = new StopWatch();
        stopWatch.interval(stopWatch.getStartTime());
        assertEquals(0, stopWatch.getLastTimeDeltaNano());
    }

    @Test
    void interval_CurrentTimeIsAboveLastTime_DeltaIsCorrect() {
        StopWatch stopWatch = new StopWatch();
        LocalDateTime timeInTheFuture = stopWatch.getStartTime().plusNanos(1000);
        stopWatch.interval(timeInTheFuture);
        assertEquals(1000, stopWatch.getLastTimeDeltaNano());
    }

    @Test
    void interval_CurrentTimeIsBehindLastTime_DeltaIsCorrect() {
        StopWatch stopWatch = new StopWatch();
        LocalDateTime timeInThePast = stopWatch.getStartTime().minusNanos(1000);
        stopWatch.interval(timeInThePast);
        assertEquals(999999000, stopWatch.getLastTimeDeltaNano());
    }

    @Test
    void Stopwatch_Initial_DeltaIsZero() {
        StopWatch stopWatch = new StopWatch();
        assertEquals(0, stopWatch.getLastTimeDeltaNano());
    }

    @Test
    void Stopwatch_Initial_NoDiffBetweenTimestamps() {
        StopWatch stopWatch = new StopWatch();
        int delta = Duration.between(stopWatch.getStartTime(), stopWatch.getLastTimeStamp()).getNano();
        assertEquals(0, delta);
    }
}
