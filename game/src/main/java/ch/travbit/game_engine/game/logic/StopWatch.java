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
        LocalDateTime initialTimeStamp = timeStamp();
        startTime = initialTimeStamp;
        lastTimeStamp = initialTimeStamp;
        lastTimeDeltaNano = 0;
    }

    private LocalDateTime timeStamp() {
        return LocalDateTime.now();
    }

    private int measureDuration(LocalDateTime startTime, LocalDateTime endTime) {
        return Duration.between(startTime, endTime).getNano();
    }

    /**
     * Stores the time delta between the last time stamp and now.
     */
    public void interval() {
        interval(timeStamp());
    }

    public void interval(LocalDateTime currentTime) {
        lastTimeDeltaNano = measureDuration(lastTimeStamp, currentTime);
        lastTimeStamp = currentTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public int getLastTimeDeltaNano() {
        return lastTimeDeltaNano;
    }

    public LocalDateTime getLastTimeStamp() {
        return lastTimeStamp;
    }
}
