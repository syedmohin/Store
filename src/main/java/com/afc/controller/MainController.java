package com.afc.controller;

import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;
import org.springframework.stereotype.Component;

@FxmlView
@Component
public class MainController {

    @FXML
    private Button exit, minus;

    @FXML
    private Label name;

    @FXML
    private JFXButton customerBtn, stockBtn;

    @FXML
    private AnchorPane customerPane, stockPane;

    @FXML
    public void initialize() {
        exit.getStylesheets().add("css/main.css");
        exit.setOnAction(ae -> Platform.exit());
        minus.setOnAction(ae -> ((Stage) minus.getScene().getWindow()).setIconified(true));
        name.setText(System.getProperty("name", "Guest"));
        customerBtn.setOnAction(ae -> {
            customerPane.toFront();
            stockPane.toBack();
        });
        stockBtn.setOnAction(ae -> {
            stockPane.toFront();
            customerPane.toBack();
        });
    }
}
