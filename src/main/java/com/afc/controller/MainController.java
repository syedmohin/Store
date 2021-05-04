package com.afc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.afc.utility.LengthValidation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView
@Component
public class MainController {

	@FXML
	private AnchorPane root;

	@FXML
	private AnchorPane stockPane;

	@FXML
	private JFXButton customerBtn;

	@FXML
	private JFXButton stockBtn;

	@FXML
	private JFXButton min;

	@FXML
	private JFXButton exit;

	@FXML
	private Label name;

	@FXML
	private AnchorPane customerPane;

	@FXML
	private JFXTextField rate;

	@FXML
	private Label rateLabel;

	@FXML
	private JFXTreeTableView<?> customerTable;

	private static final Logger log = LoggerFactory.getLogger(MainController.class);
//	private final FxWeaver fxWeaver;
//
//	public MainController(FxWeaver fx) {
//		this.fxWeaver = fx;
//	}

	public void initialize() {
		customerValidControl();
		exit.setOnAction(ae -> Platform.exit());
		min.setOnAction(ae -> ((Stage) min.getScene().getWindow()).setIconified(true));
		name.setText(System.getProperty("name", "isDone Solution!!"));
	}

	@FXML
	private void customerDisplay() {
		customerPane.toFront();
		stockPane.toBack();
	}

	@FXML
	private void stockDisplay() {
		customerPane.toBack();
		stockPane.toFront();
	}

	private void customerValidControl() {
		var reqValidator = new RequiredFieldValidator("Field required");
		var lengthValidator = new LengthValidation(4, "Field must be more than ");
		var numberValidator = new NumberValidator("Field accept only number.");
		rate.getValidators().add(numberValidator);
		rate.focusedProperty().addListener((o, oldValue, newValue) -> {
			if (!newValue)
				rate.validate();
		});
		rate.textProperty().addListener((o, oldValue, newValue) -> {
			if (rate.validate())
				rateLabel.setText("Today Rate " + newValue + " Rs");
			if (newValue.isEmpty())
				rateLabel.setText("");
		});
	}
}
