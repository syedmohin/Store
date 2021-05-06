package com.afc.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView
@Component
public class SplashController {

	@FXML
	private AnchorPane root;
	private final FxWeaver fxWeaver;
	private final String title;

	public SplashController(FxWeaver fxWeaver, @Value("${app.title}") String title) {
		this.fxWeaver = fxWeaver;
		this.title = title;
	}

	private static final Logger log = LoggerFactory.getLogger(SplashController.class);

	@FXML
	public void initialize() {
		new SplashScreen().start();
	}

	public class SplashScreen extends Thread {

		@Override
		public void run() {
			try {
				Thread.sleep(3000L);
				Platform.runLater(() -> {
					try {
						var stage = new Stage();
						stage.setTitle(title);
						stage.getIcons().add(new Image(new ClassPathResource("image/icon.png").getInputStream()));
						var scene = new Scene(fxWeaver.loadView(AuthenticatedController.class));
						stage.setScene(scene);
						stage.initStyle(StageStyle.TRANSPARENT);
						stage.show();
						root.getScene().getWindow().hide();
					} catch (IOException ex) {
						log.error(ex.getMessage());
					}
				});
			} catch (InterruptedException ex) {
				log.error(ex.getMessage());
				Thread.currentThread().interrupt();
			}
		}
	}
}
