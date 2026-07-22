package com.pomodoro.view;

import com.pomodoro.JavaFxTestBase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimerViewTest extends JavaFxTestBase {

    private TimerView view;

    @BeforeEach
    void setUp() {
        view = new TimerView();
    }

    @Test
    void timerLabel_displaysInitialValue() {
        assertEquals("25:00", view.getTimerLabel().getText());
    }

    @Test
    void sessionLabel_displaysWork() {
        assertEquals("Work", view.getSessionLabel().getText());
    }

    @Test
    void startButton_hasCorrectText() {
        assertEquals("Start", view.getStartButton().getText());
    }

    @Test
    void pauseButton_hasCorrectText() {
        assertEquals("Pause", view.getPauseButton().getText());
    }

    @Test
    void resetButton_hasCorrectText() {
        assertEquals("Reset", view.getResetButton().getText());
    }

    @Test
    void root_isNotNull() {
        assertNotNull(view.getRoot());
    }

    @Test
    void root_containsFourChildren() {
        assertEquals(4, view.getRoot().getChildren().size());
    }

    @Test
    void pomodoroCountLabel_displaysInitialValue() {
        assertEquals("Pomodoro 0/4", view.getPomodoroCountLabel().getText());
    }
}
