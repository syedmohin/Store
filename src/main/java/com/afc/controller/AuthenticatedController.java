package com.afc.controller;

import com.afc.services.UsersService;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import net.rgielen.fxweaver.core.FxmlView;

import org.springframework.stereotype.Component;

@FxmlView
@Component
public class AuthenticatedController {

    @FXML
    private AnchorPane login, signup;

    @FXML
    private Pane root;

    @FXML
    private JFXTextField su, lu;

    @FXML
    private JFXPasswordField sk, sp, lp;

    @FXML
    private JFXButton sb, lb;

    @FXML
    private Label sl, ca;
    private final UsersService us;

    public AuthenticatedController(UsersService us) {
        this.us = us;
    }

    public void initialize() {

    }

    public boolean login() {
        String user = lu.getText();
        String password = lp.getText();
        return us.checkUserAuth(user, password);

    }

    @FXML
    public void loginShow(Event ae) {
        login.toFront();
        signup.toBack();
    }

    @FXML
    public void signUpShow(Event ae) {
        signup.toFront();
        login.toBack();
    }
}
