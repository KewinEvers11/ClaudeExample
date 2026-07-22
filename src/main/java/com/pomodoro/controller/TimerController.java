package com.pomodoro.controller;

import com.pomodoro.model.PomodoroTimer;
import com.pomodoro.model.SessionType;
import com.pomodoro.model.TimerState;
import com.pomodoro.theme.ColorTheme;
import com.pomodoro.theme.ThemeAnimator;
import com.pomodoro.theme.ThemeResolver;
import com.pomodoro.view.TimerView;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimerController {

    private final PomodoroTimer timer;
    private final TimerView view;
    private final Timeline timeline;
    private final ThemeResolver themeResolver;
    private final ThemeAnimator themeAnimator;

    public TimerController(PomodoroTimer timer, TimerView view) {
        this.timer = timer;
        this.view = view;
        this.timeline = createTimeline();
        this.themeResolver = new ThemeResolver();
        this.themeAnimator = new ThemeAnimator(
                view.getRoot(), view.getTimerLabel(), view.getSessionLabel());
        bindView();
        wireButtons();
    }

    private Timeline createTimeline() {
        Timeline tl = new Timeline(new KeyFrame(Duration.seconds(1), e -> timer.tick()));
        tl.setCycleCount(Timeline.INDEFINITE);
        return tl;
    }

    private void bindView() {
        timer.remainingSecondsProperty().addListener((obs, oldVal, newVal) ->
                view.getTimerLabel().setText(formatTime(newVal.intValue())));

        timer.sessionTypeProperty().addListener((obs, oldVal, newVal) -> {
            view.getSessionLabel().setText(newVal.getDisplayName());
            updateTheme();
        });

        timer.stateProperty().addListener((obs, oldState, newState) -> {
            if (newState == TimerState.RUNNING) {
                timeline.play();
            } else {
                timeline.pause();
            }
            updateTheme();
        });

        timer.completedPomodorosProperty().addListener((obs, oldVal, newVal) ->
                view.getPomodoroCountLabel().setText(
                        String.format("Pomodoro %d/4", newVal.intValue())));
    }

    private void updateTheme() {
        ColorTheme target = themeResolver.resolve(timer.getState(), timer.getSessionType());
        themeAnimator.transitionTo(target);
    }

    private void wireButtons() {
        view.getStartButton().setOnAction(e -> timer.start());
        view.getPauseButton().setOnAction(e -> timer.pause());
        view.getResetButton().setOnAction(e -> timer.reset());
        view.getSkipButton().setOnAction(e -> timer.skip());
    }

    private String formatTime(int totalSeconds) {
        int minutes = totalSeconds / 60;
        int seconds = totalSeconds % 60;
        return String.format("%02d:%02d", minutes, seconds);
    }
}
