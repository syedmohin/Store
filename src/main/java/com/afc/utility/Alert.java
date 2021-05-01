package com.afc.utility;

import static com.jfoenix.controls.JFXButton.ButtonType.RAISED;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;
import static javafx.stage.Modality.APPLICATION_MODAL;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.control.Label;
import javafx.scene.effect.BoxBlur;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class Alert {
	private Alert() {
	}

	private static final String OKAY = "Okay!";

	public static void error(String msg, Pane root) {
		var alert = alert(root);
		var layout = layout(msg, "Error");
		var btn = button(alert, OKAY);
		btn.setRipplerFill(RED);
		layout.setActions(btn);
		alert.setContent(layout);
		alert.showAndWait();
	}

	public static void info(String msg, Pane root) {
		var alert = alert(root);
		var layout = layout(msg, "Info");
		var btn = button(alert, OKAY);
		btn.setRipplerFill(BLUE);
		layout.setActions(btn);
		alert.setContent(layout);
		alert.showAndWait();
	}

	public static JFXButton button(JFXAlert<String> alert, String msg) {
		var btn = new JFXButton(msg);
		btn.setFont(new Font(15));
		btn.setButtonType(RAISED);
		btn.setRipplerFill(RED);
		btn.setCancelButton(true);
		btn.setOnAction(e -> alert.hideWithAnimation());
		return btn;
	}

	public static JFXAlert<String> alert(Pane root) {
		var alert = new JFXAlert<String>(root.getScene().getWindow());
		alert.initModality(APPLICATION_MODAL);
		alert.setOverlayClose(false);
		root.setEffect(new BoxBlur(3, 3, 3));
		alert.setOnCloseRequest(ae -> root.setEffect(null));
		return alert;
	}

	public static JFXDialogLayout layout(String msg, String alertType) {
		var layout = new JFXDialogLayout();
		var label = new Label(alertType);
		label.setFont(new Font(22));
		layout.setHeading(label);
		var msgLabel = new Label(msg);
		msgLabel.setFont(new Font(15));
		layout.setBody(new VBox(msgLabel));
		return layout;
	}

}
