package ch.travbit.game_engine.game.logic;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * This class represents a stopwatch to measure time deltas.
 */
public class StopWatch {

    private LocalDateTime startTime;
    private LocalDateTime lastTimeStamp;
    private int lastTimeDeltaNano;

    public StopWatch() {
        startTime = LocalDateTime.now();
        lastTimeStamp = LocalDateTime.now();
        lastTimeDeltaNano = 0;
    }

    /**
     * Stores the time delta between the last time stamp and now.
     */
    public void interval() {
        LocalDateTime current = LocalDateTime.now();
        lastTimeDeltaNano = Duration.between(lastTimeStamp, current).getNano();
        lastTimeStamp = LocalDateTime.now();
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getLastTimeDeltaNano() {
        return lastTimeDeltaNano;
    }
}
