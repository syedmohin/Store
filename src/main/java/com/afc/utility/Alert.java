package com.afc.utility;

import static com.jfoenix.controls.JFXButton.ButtonType.RAISED;
import static javafx.scene.paint.Color.BLUE;
import static javafx.scene.paint.Color.RED;
import static javafx.stage.Modality.APPLICATION_MODAL;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

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
	private static final Logger log = LoggerFactory.getLogger(Alert.class);
	private static final String FONT_LOCATION = "/fonts/Mono.ttf";

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
		try {
			btn.setFont(Font.loadFont(new ClassPathResource(FONT_LOCATION).getInputStream(), 10));
		} catch (IOException e1) {
			log.error("Unable to Load Font!! {}", e1.getMessage());
		}
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
		layout.setHeading(label);
		var msgLabel = new Label(msg);
		try {
			label.setFont(Font.loadFont(new ClassPathResource(FONT_LOCATION).getInputStream(), 22));
			msgLabel.setFont(Font.loadFont(new ClassPathResource(FONT_LOCATION).getInputStream(), 15));
		} catch (IOException e) {
			log.error("Unable to Load Font!! {}", e.getMessage());
		}
		layout.setBody(new VBox(msgLabel));
		return layout;
	}

}
