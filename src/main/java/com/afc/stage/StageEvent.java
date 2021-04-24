package com.afc.stage;

import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;

public class StageEvent extends ApplicationEvent {

    public StageEvent(Object source) {
        super(source);
    }

    public Stage getStage() {
        return (Stage) getSource();
    }
}
