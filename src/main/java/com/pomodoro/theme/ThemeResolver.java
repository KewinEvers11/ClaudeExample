package com.pomodoro.theme;

import com.pomodoro.model.SessionType;
import com.pomodoro.model.TimerState;
import javafx.scene.paint.Color;

public class ThemeResolver {

    private static final ColorTheme WORK_THEME = new ColorTheme(
            Color.web("#FFCDD2"),
            Color.web("#C62828")
    );

    private static final ColorTheme BREAK_THEME = new ColorTheme(
            Color.web("#C8E6C9"),
            Color.web("#2E7D32")
    );

    private static final ColorTheme PAUSED_THEME = new ColorTheme(
            Color.web("#FFF9C4"),
            Color.web("#F9A825")
    );

    private static final ColorTheme DEFAULT_THEME = new ColorTheme(
            Color.web("#F5F5F5"),
            Color.web("#212121")
    );

    public ColorTheme resolve(TimerState state, SessionType sessionType) {
        return switch (state) {
            case STOPPED -> DEFAULT_THEME;
            case PAUSED -> PAUSED_THEME;
            case RUNNING -> switch (sessionType) {
                case WORK -> WORK_THEME;
                case SHORT_BREAK, LONG_BREAK -> BREAK_THEME;
            };
        };
    }
}
