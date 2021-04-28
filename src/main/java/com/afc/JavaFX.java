package com.afc;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

import com.afc.stage.StageEvent;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.stage.Stage;

public class JavaFX extends Application {

	private ConfigurableApplicationContext context;

	@Override
	public void init() throws Exception {
		ApplicationContextInitializer<GenericApplicationContext> initializer = ac -> {
			ac.registerBean(Application.class, () -> JavaFX.this);
			ac.registerBean(Parameters.class, this::getParameters);
			ac.registerBean(HostServices.class, this::getHostServices);
		};
		this.context = new SpringApplicationBuilder().sources(Main.class).initializers(initializer)
				.run(getParameters().getRaw().toArray(new String[0]));
	}

	@SuppressWarnings("exports")
	@Override
	public void start(final Stage primaryStage) throws Exception {
		this.context.publishEvent(new StageEvent(primaryStage));
	}

	@Override
	public void stop() throws Exception {
		this.context.close();
		Platform.exit();
	}
}
