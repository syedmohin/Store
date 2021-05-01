package com.afc.controller;

import static javafx.scene.input.KeyCode.ENTER;
import static javafx.stage.StageStyle.UNDECORATED;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import com.afc.alert.Alert;
import com.afc.exception.UserNotFoundException;
import com.afc.model.Users;
import com.afc.repository.UsersRepository;
import com.afc.validator.LengthValidation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;
import com.password4j.Password;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView
@Component
public class AuthenticatedController {

	private static final Logger log = LoggerFactory.getLogger(AuthenticatedController.class);

	@Value("${app.key}")
	private String keyAuth;
	@Value("${app.title}")
	private String title;

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

	private final UsersRepository usersRepo;
	private final FxWeaver fxWeaver;

	public AuthenticatedController(UsersRepository us, FxWeaver fx) {
		this.usersRepo = us;
		this.fxWeaver = fx;
	}

	@FXML
	public void initialize() {
		// Validators
		var reqValidator = new RequiredFieldValidator("Field required");
		var lengthValidator = new LengthValidation(4, "Field must be more than ");
		var numberValidator = new NumberValidator("Field accept only number.");

		// applying Validators
		lu.getValidators().addAll(reqValidator, lengthValidator);
		lu.focusedProperty().addListener((o, oldValue, newValue) -> {
			if (!newValue)
				lu.validate();
		});
		lp.getValidators().addAll(reqValidator, lengthValidator);
		lp.focusedProperty().addListener((o, oldvalue, newValue) -> {
			if (!newValue)
				lp.validate();
		});

		su.getValidators().addAll(reqValidator, lengthValidator);
		su.focusedProperty().addListener((o, oldValue, newValue) -> {
			if (!newValue)
				su.validate();
		});
		sp.getValidators().addAll(reqValidator, lengthValidator);
		sp.focusedProperty().addListener((o, oldvalue, newValue) -> {
			if (!newValue)
				sp.validate();
		});
		sk.getValidators().addAll(reqValidator, numberValidator, lengthValidator);
		sk.focusedProperty().addListener((o, oldvalue, newValue) -> {
			if (!newValue)
				sk.validate();
		});

		lb.setOnAction(ae -> login());
		lp.setOnKeyPressed(ae -> {
			if (ae.getCode() == ENTER)
				login();
		});
		sb.setOnAction(ae -> signUp());
		sk.setOnKeyPressed(ae -> {
			if (ae.getCode() == ENTER)
				signUp();
		});
	}

	public void signUp() {
		String user = su.getText();
		String password = sp.getText();
		String key = sk.getText();
		if (usersRepo.existsByUsername(user)) {
			Alert.info("User already exist with that username.", root);
		} else {
			if (key.equals(this.keyAuth)) {
				if (save(new Users(user, password)) != null) {
					Alert.info("Registration Completed", root);
					login.toFront();
					signup.toBack();
				} else {
					Alert.error("Unable to save User Try Again Later..", root);
				}
			} else {
				Alert.error("Wrong key", root);
			}
		}
	}

	public void login() {
		String user = lu.getText();
		String password = lp.getText();
		try {
			if (checkUserAuth(user, password)) {
				lu.getScene().getWindow().hide();
				Stage stage = new Stage();
				System.setProperty("name", user);
				stage.setTitle(title);
				try {
					stage.getIcons().add(new Image(new ClassPathResource("image/icon.png").getInputStream()));
				} catch (IOException e) {
					log.error("unable to add icon");
				}
				Scene scene = new Scene(fxWeaver.loadView(MainController.class));
				stage.initStyle(UNDECORATED);
				stage.setScene(scene);
				stage.centerOnScreen();
				stage.show();
			} else {
				Alert.error("Username and Password is incorrect..", root);
				log.error("Username and Password is incorrect.. ");
			}
		} catch (UserNotFoundException e) {
			Alert.error("Username and Password is incorrect..", root);
			log.error("Username and Password is incorrect.. {}", e.getMessage());
		}
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

	public Users save(Users users) {
		users.setPassword(Password.hash(users.getPassword()).withBCrypt().getResult());
		return usersRepo.save(users);
	}

	public Users getUser(String name) throws UserNotFoundException {
		return usersRepo.findByUsername(name)
						.orElseThrow(() -> new UserNotFoundException("Username not found " + name + "!!"));
	}

	public String getPassword(String username) throws UserNotFoundException {
		return getUser(username).getPassword();
	}

	public String getUsername(String username) throws UserNotFoundException {
		return getUser(username).getUsername();
	}

	public boolean checkUserAuth(String username, String pass) throws UserNotFoundException {
		boolean b = Password.check(pass, getPassword(username)).withBCrypt();
		System.out.println(b);
		return b;
	}
}
