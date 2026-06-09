package com.pomodoro;

import javafx.application.Platform;
import org.junit.jupiter.api.BeforeAll;

public abstract class JavaFxTestBase {

    @BeforeAll
    static void initToolkit() {
        try {
            Platform.startup(() -> {});
        } catch (IllegalStateException e) {
            // toolkit already initialized
        }
    }
}
