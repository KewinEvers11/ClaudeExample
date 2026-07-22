package com.pomodoro.controller;

import com.pomodoro.JavaFxTestBase;
import com.pomodoro.model.PomodoroTimer;
import com.pomodoro.model.SessionType;
import com.pomodoro.model.TimerState;
import com.pomodoro.view.TimerView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerControllerTest extends JavaFxTestBase {

    private PomodoroTimer timer;
    private TimerView view;
    private TimerController controller;

    @BeforeEach
    void setUp() {
        timer = new PomodoroTimer();
        view = new TimerView();
        controller = new TimerController(timer, view);
    }

    @Test
    void startButton_setsTimerToRunning() {
        view.getStartButton().fire();
        assertEquals(TimerState.RUNNING, timer.getState());
    }

    @Test
    void pauseButton_setsTimerToPaused() {
        view.getStartButton().fire();
        view.getPauseButton().fire();
        assertEquals(TimerState.PAUSED, timer.getState());
    }

    @Test
    void resetButton_resetsTimer() {
        view.getStartButton().fire();
        timer.tick();
        view.getResetButton().fire();

        assertEquals(TimerState.STOPPED, timer.getState());
        assertEquals(25 * 60, timer.getRemainingSeconds());
    }

    @Test
    void timerLabel_updatesOnTick() {
        view.getStartButton().fire();
        timer.tick();
        assertEquals("24:59", view.getTimerLabel().getText());
    }

    @Test
    void timerLabel_updatesOnReset() {
        view.getStartButton().fire();
        timer.tick();
        timer.tick();
        view.getResetButton().fire();
        assertEquals("25:00", view.getTimerLabel().getText());
    }

    @Test
    void timerLabel_formatsCorrectlyAtLowValues() {
        view.getStartButton().fire();
        for (int i = 0; i < 24 * 60 + 55; i++) {
            timer.tick();
        }
        assertEquals("00:05", view.getTimerLabel().getText());
    }

    @Test
    void sessionLabel_updatesOnReset() {
        view.getStartButton().fire();
        view.getResetButton().fire();
        assertEquals("Work", view.getSessionLabel().getText());
    }

    @Test
    void skipButton_advancesSession() {
        view.getSkipButton().fire();
        assertEquals(SessionType.SHORT_BREAK, timer.getSessionType());
    }

    @Test
    void skipButton_updatesSessionLabel() {
        view.getSkipButton().fire();
        assertEquals("Short Break", view.getSessionLabel().getText());
    }

    @Test
    void skipButton_updatesTimerLabel() {
        view.getSkipButton().fire();
        assertEquals("05:00", view.getTimerLabel().getText());
    }
}
