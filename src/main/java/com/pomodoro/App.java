package com.pomodoro;

import com.pomodoro.controller.TimerController;
import com.pomodoro.model.PomodoroTimer;
import com.pomodoro.view.TimerView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        PomodoroTimer timer = new PomodoroTimer();
        TimerView view = new TimerView();
        new TimerController(timer, view);

        Scene scene = new Scene(view.getRoot(), 400, 300);
        stage.setTitle("Pomodoro Timer");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
