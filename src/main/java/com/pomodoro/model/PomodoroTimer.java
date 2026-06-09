package com.pomodoro.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PomodoroTimer {

    private static final int WORK_DURATION_SECONDS = 25 * 60;

    private final IntegerProperty remainingSeconds = new SimpleIntegerProperty(WORK_DURATION_SECONDS);
    private final ObjectProperty<TimerState> state = new SimpleObjectProperty<>(TimerState.STOPPED);
    private final StringProperty sessionType = new SimpleStringProperty("Work");

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
        remainingSeconds.set(WORK_DURATION_SECONDS);
        sessionType.set("Work");
    }

    public void tick() {
        if (state.get() == TimerState.RUNNING && remainingSeconds.get() > 0) {
            remainingSeconds.set(remainingSeconds.get() - 1);
        }
    }

    public IntegerProperty remainingSecondsProperty() {
        return remainingSeconds;
    }

    public ObjectProperty<TimerState> stateProperty() {
        return state;
    }

    public StringProperty sessionTypeProperty() {
        return sessionType;
    }

    public int getRemainingSeconds() {
        return remainingSeconds.get();
    }

    public TimerState getState() {
        return state.get();
    }

    public String getSessionType() {
        return sessionType.get();
    }
}
