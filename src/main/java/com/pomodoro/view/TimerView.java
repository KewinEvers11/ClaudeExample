package com.pomodoro.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class TimerView {

    private final VBox root;
    private final Label timerLabel;
    private final Label sessionLabel;
    private final Button startButton;
    private final Button pauseButton;
    private final Button resetButton;

    public TimerView() {
        sessionLabel = new Label("Work");
        sessionLabel.setFont(new Font(20));

        timerLabel = new Label("25:00");
        timerLabel.setFont(new Font(72));

        startButton = new Button("Start");
        pauseButton = new Button("Pause");
        resetButton = new Button("Reset");

        HBox buttonBox = new HBox(10, startButton, pauseButton, resetButton);
        buttonBox.setAlignment(Pos.CENTER);

        root = new VBox(20, sessionLabel, timerLabel, buttonBox);
        root.setAlignment(Pos.CENTER);
        root.setPadding(new Insets(40));
    }

    public VBox getRoot() {
        return root;
    }

    public Label getTimerLabel() {
        return timerLabel;
    }

    public Label getSessionLabel() {
        return sessionLabel;
    }

    public Button getStartButton() {
        return startButton;
    }

    public Button getPauseButton() {
        return pauseButton;
    }

    public Button getResetButton() {
        return resetButton;
    }
}
