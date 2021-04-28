package com.afc.alert;

import com.jfoenix.controls.JFXAlert;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDialogLayout;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;

public class Alert {
	private Alert() {
	}

	public static void error(String msg, Pane root) {
		var alert = alert(root);
		var layout = layout(msg, "Error");
		var btn = button(alert, "Okay!");
		layout.setActions(btn);
		alert.setContent(layout);
		alert.showAndWait();
	}

	public static void info(String msg, Pane root) {
		var alert = alert(root);
		var layout = layout(msg, "Info");
		var btn = button(alert, "Okay!");
		layout.setActions(btn);
		alert.setContent(layout);
		alert.showAndWait();
	}

	public static JFXButton button(JFXAlert<String> alert, String msg) {
		var btn = new JFXButton(msg);
		btn.setFont(new Font(15));
		btn.setCancelButton(true);
		btn.setOnAction(e -> alert.hideWithAnimation());
		return btn;
	}

	public static JFXAlert<String> alert(Pane root) {
		JFXAlert<String> alert = new JFXAlert<>(root.getScene().getWindow());
		alert.initModality(Modality.APPLICATION_MODAL);
		alert.setOverlayClose(false);
		return alert;
	}

	public static JFXDialogLayout layout(String msg, String alertType) {
		var layout = new JFXDialogLayout();
		Label label = new Label(alertType);
		label.setFont(new Font(18));
		layout.setHeading(label);
		Label msgLabel = new Label(msg);
		msgLabel.setFont(new Font(15));
		layout.setBody(new VBox(msgLabel));
		return layout;
	}

}
