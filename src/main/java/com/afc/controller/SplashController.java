package com.afc.controller;

import java.io.IOException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import org.springframework.stereotype.Component;

import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

//@Slf4j
@FxmlView
@Component

public class SplashController {

    private static final Logger log = LoggerFactory.getLogger(SplashController.class);
    @Value("${app.title}")
    private String title;
    @Value("classpath:image/icon.png")
    private Resource icon;
    @FXML
    private AnchorPane root;
    private final FxWeaver fxWeaver;

    public SplashController(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @FXML
    public void initialize() {
        new SplashScreen().start();
    }

    public class SplashScreen extends Thread {

        @Override
        public void run() {
            try {
                Thread.sleep(3000l);
                Platform.runLater(() -> {
                    try {
                        log.info(title);
                        Stage stage = new Stage();
                        stage.setTitle(title);
                        stage.getIcons().add(new Image(icon.getInputStream()));
                        Scene scene = new Scene(fxWeaver.loadView(AuthenticatedController.class));
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
            }
        }
    }
}
