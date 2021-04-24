package com.afc.stage;

import com.afc.controller.SplashController;
import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import static javafx.stage.StageStyle.UNDECORATED;
import net.rgielen.fxweaver.core.FxWeaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class StageListener implements ApplicationListener<StageEvent> {

    @Value("${app.title}")
    private String title;
    @Value("classpath:image/icon.png")
    private Resource icon;
    private final FxWeaver fxWeaver;
    private Stage stage;
    private static final Logger log = LoggerFactory.getLogger(StageListener.class);

    public StageListener(FxWeaver fxWeaver) {
        this.fxWeaver = fxWeaver;
    }

    @Override
    public void onApplicationEvent(StageEvent event) {
        try {
            stage = event.getStage();
            stage.setTitle(title);
            stage.getIcons().add(new Image(icon.getInputStream()));
            Scene scene = new Scene(fxWeaver.loadView(SplashController.class));
            stage.initStyle(UNDECORATED);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    public Stage getStage() {
        return stage;
    }

}
