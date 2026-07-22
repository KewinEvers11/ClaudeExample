package com.pomodoro.theme;

import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class ThemeAnimator {

    private static final Duration FADE_DURATION = Duration.millis(300);

    private final VBox root;
    private final Label timerLabel;
    private final Label sessionLabel;

    private ColorTheme currentTheme;
    private Timeline fadeTimeline;

    public ThemeAnimator(VBox root, Label timerLabel, Label sessionLabel) {
        this.root = root;
        this.timerLabel = timerLabel;
        this.sessionLabel = sessionLabel;
        this.currentTheme = new ColorTheme(Color.web("#F5F5F5"), Color.web("#212121"));
        applyColors(currentTheme.backgroundColor(), currentTheme.textColor());
    }

    public void transitionTo(ColorTheme target) {
        if (fadeTimeline != null) {
            fadeTimeline.stop();
        }

        Color currentBg = extractCurrentBackground();
        Color currentText = (Color) timerLabel.getTextFill();
        ColorTheme from = new ColorTheme(currentBg, currentText);

        DoubleProperty progress = new SimpleDoubleProperty(0);
        progress.addListener((obs, oldVal, newVal) -> {
            double t = newVal.doubleValue();
            Color bg = from.backgroundColor().interpolate(target.backgroundColor(), t);
            Color text = from.textColor().interpolate(target.textColor(), t);
            applyColors(bg, text);
        });

        fadeTimeline = new Timeline(
                new KeyFrame(Duration.ZERO, new KeyValue(progress, 0.0)),
                new KeyFrame(FADE_DURATION, new KeyValue(progress, 1.0, Interpolator.EASE_BOTH))
        );
        fadeTimeline.setOnFinished(e -> currentTheme = target);
        fadeTimeline.play();
    }

    private void applyColors(Color bgColor, Color textColor) {
        root.setBackground(new Background(new BackgroundFill(bgColor, CornerRadii.EMPTY, Insets.EMPTY)));
        timerLabel.setTextFill(textColor);
        sessionLabel.setTextFill(textColor);
    }

    private Color extractCurrentBackground() {
        Background bg = root.getBackground();
        if (bg != null && !bg.getFills().isEmpty()) {
            return (Color) bg.getFills().get(0).getFill();
        }
        return currentTheme.backgroundColor();
    }
}
