package com.pomodoro.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PomodoroTimerTest {

    private PomodoroTimer timer;

    @BeforeEach
    void setUp() {
        timer = new PomodoroTimer();
    }

    @Test
    void initialState_isStopped() {
        assertEquals(TimerState.STOPPED, timer.getState());
    }

    @Test
    void initialRemainingSeconds_is25Minutes() {
        assertEquals(25 * 60, timer.getRemainingSeconds());
    }

    @Test
    void initialSessionType_isWork() {
        assertEquals("Work", timer.getSessionType());
    }

    @Test
    void start_setsStateToRunning() {
        timer.start();
        assertEquals(TimerState.RUNNING, timer.getState());
    }

    @Test
    void pause_whenRunning_setsStateToPaused() {
        timer.start();
        timer.pause();
        assertEquals(TimerState.PAUSED, timer.getState());
    }

    @Test
    void pause_whenStopped_remainsStopped() {
        timer.pause();
        assertEquals(TimerState.STOPPED, timer.getState());
    }

    @Test
    void reset_returnsToInitialState() {
        timer.start();
        timer.tick();
        timer.tick();
        timer.reset();

        assertEquals(TimerState.STOPPED, timer.getState());
        assertEquals(25 * 60, timer.getRemainingSeconds());
        assertEquals("Work", timer.getSessionType());
    }

    @Test
    void tick_whenRunning_decrementsRemainingSeconds() {
        timer.start();
        timer.tick();
        assertEquals(25 * 60 - 1, timer.getRemainingSeconds());
    }

    @Test
    void tick_whenPaused_doesNotDecrement() {
        timer.start();
        timer.pause();
        int before = timer.getRemainingSeconds();
        timer.tick();
        assertEquals(before, timer.getRemainingSeconds());
    }

    @Test
    void tick_whenStopped_doesNotDecrement() {
        int before = timer.getRemainingSeconds();
        timer.tick();
        assertEquals(before, timer.getRemainingSeconds());
    }

    @Test
    void tick_atZero_doesNotGoNegative() {
        timer.start();
        for (int i = 0; i < 25 * 60; i++) {
            timer.tick();
        }
        assertEquals(0, timer.getRemainingSeconds());
        timer.tick();
        assertEquals(0, timer.getRemainingSeconds());
    }

    @Test
    void multipleTicks_decrementCorrectly() {
        timer.start();
        timer.tick();
        timer.tick();
        timer.tick();
        assertEquals(25 * 60 - 3, timer.getRemainingSeconds());
    }

    @Test
    void startAfterPause_resumesRunning() {
        timer.start();
        timer.tick();
        timer.pause();
        int paused = timer.getRemainingSeconds();
        timer.start();

        assertEquals(TimerState.RUNNING, timer.getState());
        assertEquals(paused, timer.getRemainingSeconds());
    }
}
