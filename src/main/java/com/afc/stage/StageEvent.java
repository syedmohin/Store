package com.afc.stage;

import org.springframework.context.ApplicationEvent;

import javafx.stage.Stage;

public class StageEvent extends ApplicationEvent {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5763363209052774276L;

	public StageEvent(Object source) {
        super(source);
    }

    public Stage getStage() {
        return (Stage) getSource();
    }
}
