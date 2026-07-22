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
        assertEquals(SessionType.WORK, timer.getSessionType());
    }

    @Test
    void initialCompletedPomodoros_isZero() {
        assertEquals(0, timer.getCompletedPomodoros());
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
        assertEquals(SessionType.WORK, timer.getSessionType());
        assertEquals(0, timer.getCompletedPomodoros());
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

    @Test
    void tick_atZero_duringWork_transitionsToShortBreak() {
        timer.start();
        tickTimes(25 * 60);

        assertEquals(SessionType.SHORT_BREAK, timer.getSessionType());
        assertEquals(5 * 60, timer.getRemainingSeconds());
        assertEquals(TimerState.RUNNING, timer.getState());
    }

    @Test
    void tick_atZero_duringWork_incrementsCompletedPomodoros() {
        timer.start();
        tickTimes(25 * 60);

        assertEquals(1, timer.getCompletedPomodoros());
    }

    @Test
    void tick_atZero_duringShortBreak_transitionsToWork() {
        timer.start();
        tickTimes(25 * 60);
        tickTimes(5 * 60);

        assertEquals(SessionType.WORK, timer.getSessionType());
        assertEquals(25 * 60, timer.getRemainingSeconds());
        assertEquals(TimerState.RUNNING, timer.getState());
    }

    @Test
    void tick_afterFourthWork_transitionsToLongBreak() {
        timer.start();
        for (int i = 0; i < 3; i++) {
            tickTimes(25 * 60);
            tickTimes(5 * 60);
        }
        tickTimes(25 * 60);

        assertEquals(SessionType.LONG_BREAK, timer.getSessionType());
        assertEquals(15 * 60, timer.getRemainingSeconds());
        assertEquals(4, timer.getCompletedPomodoros());
    }

    @Test
    void tick_atZero_duringLongBreak_transitionsToWork() {
        timer.start();
        for (int i = 0; i < 3; i++) {
            tickTimes(25 * 60);
            tickTimes(5 * 60);
        }
        tickTimes(25 * 60);
        tickTimes(15 * 60);

        assertEquals(SessionType.WORK, timer.getSessionType());
        assertEquals(25 * 60, timer.getRemainingSeconds());
    }

    @Test
    void advanceSession_keepsStateRunning() {
        timer.start();
        tickTimes(25 * 60);

        assertEquals(TimerState.RUNNING, timer.getState());
    }

    @Test
    void reset_afterMultipleSessions_clearsCompletedPomodoros() {
        timer.start();
        tickTimes(25 * 60);
        tickTimes(5 * 60);
        tickTimes(25 * 60);
        timer.reset();

        assertEquals(0, timer.getCompletedPomodoros());
        assertEquals(SessionType.WORK, timer.getSessionType());
    }

    private void tickTimes(int count) {
        for (int i = 0; i < count; i++) {
            timer.tick();
        }
    }
}
