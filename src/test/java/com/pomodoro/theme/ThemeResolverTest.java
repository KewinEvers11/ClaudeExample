package com.pomodoro.theme;

import com.pomodoro.model.SessionType;
import com.pomodoro.model.TimerState;
import javafx.scene.paint.Color;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ThemeResolverTest {

    private ThemeResolver resolver;

    @BeforeEach
    void setUp() {
        resolver = new ThemeResolver();
    }

    @Test
    void resolve_stoppedState_returnsDefaultTheme() {
        ColorTheme theme = resolver.resolve(TimerState.STOPPED, SessionType.WORK);

        assertEquals(Color.web("#F5F5F5"), theme.backgroundColor());
        assertEquals(Color.web("#212121"), theme.textColor());
    }

    @Test
    void resolve_pausedState_returnsYellowTheme() {
        ColorTheme theme = resolver.resolve(TimerState.PAUSED, SessionType.WORK);

        assertEquals(Color.web("#FFF9C4"), theme.backgroundColor());
        assertEquals(Color.web("#F9A825"), theme.textColor());
    }

    @Test
    void resolve_pausedDuringBreak_returnsYellowTheme() {
        ColorTheme theme = resolver.resolve(TimerState.PAUSED, SessionType.SHORT_BREAK);

        assertEquals(Color.web("#FFF9C4"), theme.backgroundColor());
        assertEquals(Color.web("#F9A825"), theme.textColor());
    }

    @Test
    void resolve_runningWork_returnsRedTheme() {
        ColorTheme theme = resolver.resolve(TimerState.RUNNING, SessionType.WORK);

        assertEquals(Color.web("#FFCDD2"), theme.backgroundColor());
        assertEquals(Color.web("#C62828"), theme.textColor());
    }

    @Test
    void resolve_runningShortBreak_returnsGreenTheme() {
        ColorTheme theme = resolver.resolve(TimerState.RUNNING, SessionType.SHORT_BREAK);

        assertEquals(Color.web("#C8E6C9"), theme.backgroundColor());
        assertEquals(Color.web("#2E7D32"), theme.textColor());
    }

    @Test
    void resolve_runningLongBreak_returnsGreenTheme() {
        ColorTheme theme = resolver.resolve(TimerState.RUNNING, SessionType.LONG_BREAK);

        assertEquals(Color.web("#C8E6C9"), theme.backgroundColor());
        assertEquals(Color.web("#2E7D32"), theme.textColor());
    }
}
