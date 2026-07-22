package com.pomodoro.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class PomodoroTimer {

    private static final int POMODOROS_BEFORE_LONG_BREAK = 4;

    private final IntegerProperty remainingSeconds;
    private final ObjectProperty<TimerState> state = new SimpleObjectProperty<>(TimerState.STOPPED);
    private final ObjectProperty<SessionType> sessionType = new SimpleObjectProperty<>(SessionType.WORK);
    private final IntegerProperty completedPomodoros = new SimpleIntegerProperty(0);

    public PomodoroTimer() {
        this.remainingSeconds = new SimpleIntegerProperty(SessionType.WORK.getDurationSeconds());
    }

    public PomodoroTimer(int workSeconds, int shortBreakSeconds, int longBreakSeconds) {
        this.remainingSeconds = new SimpleIntegerProperty(workSeconds);
    }

    public void start() {
        state.set(TimerState.RUNNING);
    }

    public void pause() {
        if (state.get() == TimerState.RUNNING) {
            state.set(TimerState.PAUSED);
        }
    }

    public void reset() {
        state.set(TimerState.STOPPED);
        sessionType.set(SessionType.WORK);
        remainingSeconds.set(sessionType.get().getDurationSeconds());
        completedPomodoros.set(0);
    }

    public void tick() {
        if (state.get() == TimerState.RUNNING && remainingSeconds.get() > 0) {
            remainingSeconds.set(remainingSeconds.get() - 1);
            if (remainingSeconds.get() == 0) {
                advanceSession();
            }
        }
    }

    private void advanceSession() {
        if (sessionType.get() == SessionType.WORK) {
            completedPomodoros.set(completedPomodoros.get() + 1);
            if (completedPomodoros.get() % POMODOROS_BEFORE_LONG_BREAK == 0) {
                sessionType.set(SessionType.LONG_BREAK);
            } else {
                sessionType.set(SessionType.SHORT_BREAK);
            }
        } else {
            sessionType.set(SessionType.WORK);
        }
        remainingSeconds.set(sessionType.get().getDurationSeconds());
    }

    public IntegerProperty remainingSecondsProperty() {
        return remainingSeconds;
    }

    public ObjectProperty<TimerState> stateProperty() {
        return state;
    }

    public ObjectProperty<SessionType> sessionTypeProperty() {
        return sessionType;
    }

    public IntegerProperty completedPomodorosProperty() {
        return completedPomodoros;
    }

    public int getRemainingSeconds() {
        return remainingSeconds.get();
    }

    public TimerState getState() {
        return state.get();
    }

    public SessionType getSessionType() {
        return sessionType.get();
    }

    public int getCompletedPomodoros() {
        return completedPomodoros.get();
    }
}
