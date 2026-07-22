package com.pomodoro.model;

public enum SessionType {

    WORK(25 * 60, "Work"),
    SHORT_BREAK(5 * 60, "Short Break"),
    LONG_BREAK(15 * 60, "Long Break");

    private final int durationSeconds;
    private final String displayName;

    SessionType(int durationSeconds, String displayName) {
        this.durationSeconds = durationSeconds;
        this.displayName = displayName;
    }

    public int getDurationSeconds() {
        return durationSeconds;
    }

    public String getDisplayName() {
        return displayName;
    }
}
