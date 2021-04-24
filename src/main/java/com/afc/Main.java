package com.afc;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import javafx.application.Application;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

@EnableScheduling
@SpringBootApplication
public class Main {

    public static void main(String[] args) {
        Application.launch(JavaFX.class, args);
    }

    @Bean
    public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
        return new SpringFxWeaver(applicationContext);
    }
}
