package com.afc.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.afc.model.Customer;
import com.afc.model.CustomerTable;
import com.afc.repository.CustomerRepository;
import com.afc.utility.LengthValidation;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.cells.editors.TextFieldEditorBuilder;
import com.jfoenix.controls.cells.editors.base.OnPressedEditableTreeTableCell;
import com.jfoenix.validation.NumberValidator;
import com.jfoenix.validation.RequiredFieldValidator;

import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.StringConverter;
import javafx.util.converter.IntegerStringConverter;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;

@FxmlView
@Component
public class MainController {

	@FXML
	private AnchorPane root;

	@FXML
	private AnchorPane customerReleased;

	@FXML
	private AnchorPane customerPane;

	@FXML
	private JFXTextField cName;

	@FXML
	private JFXTextField cWeight;

	@FXML
	private JFXTextField cRate;

	@FXML
	private JFXTextField cAmount;

	@FXML
	private JFXTextField cCrate;

	@FXML
	private JFXTextField cMobile;

	@FXML
	private Label totalAmount;

	@FXML
	private JFXButton customerBtn;

	@FXML
	private JFXTextField cSearch;

	@FXML
	private JFXTreeTableView<CustomerTable> customerTable;

	@FXML
	private Label developer;

	@FXML
	private JFXButton logout;

	@FXML
	private JFXButton min;

	@FXML
	private JFXButton exit;

	@FXML
	private Label nameLabel;

	private ObservableList<CustomerTable> customerOb = FXCollections.observableArrayList();

	private final String title;
	private final FxWeaver fxWeaver;
	private final HostServices hostServices;
	private final CustomerRepository customerRepository;

	private static final Logger log = LoggerFactory.getLogger(MainController.class);

	public MainController(CustomerRepository cr, FxWeaver fx, HostServices hs, @Value("${app.title}") String title) {
		this.fxWeaver = fx;
		this.title = title;
		this.hostServices = hs;
		this.customerRepository = cr;

	}

	public void initialize() {
		customerValidControl();
		bindingFields();
		createCustomerTable();

		exit.setOnAction(ae -> Platform.exit());
		min.setOnAction(ae -> ((Stage) min.getScene().getWindow()).setIconified(true));
		developer.setOnMousePressed(ae -> hostServices.resolveURI(hostServices.getDocumentBase(),
				"https://github.com/syedmohin/Store.git"));
		logout.setOnAction(ae -> logout());
		logout.setCancelButton(true);
		nameLabel.setText(System.getProperty("name", "isDone Solution!!"));
		customerBtn.setOnAction(ae -> clear());
	}

	private void createCustomerTable() {

		customerTable.setShowRoot(false);
		var billIdColumn = new JFXTreeTableColumn<CustomerTable, String>("#");
		billIdColumn.setCellValueFactory(p -> p.getValue().getValue().billIdProperty());

		var nameColumn = new JFXTreeTableColumn<CustomerTable, String>("Name");
		nameColumn.setCellValueFactory(p -> p.getValue().getValue().nameProperty());
		nameColumn.setCellFactory(p -> new OnPressedEditableTreeTableCell<>(new TextFieldEditorBuilder()));

		var weightColumn = new JFXTreeTableColumn<CustomerTable, Integer>("Weight");
		weightColumn.setCellValueFactory(p -> p.getValue().getValue().weigthProperty().asObject());
		weightColumn.setCellFactory(p -> new OnPressedEditableTreeTableCell<>(new TextFieldEditorBuilder()));

		var rateColumn = new JFXTreeTableColumn<CustomerTable, Integer>("Rate");
		rateColumn.setCellValueFactory(p -> p.getValue().getValue().rateProperty().asObject());
		rateColumn.setCellFactory(p -> new OnPressedEditableTreeTableCell<>(new TextFieldEditorBuilder()));

		var totalColumn = new JFXTreeTableColumn<CustomerTable, Integer>("Total Amount");
		totalColumn.setCellValueFactory(p -> p.getValue().getValue().totalAmountProperty().asObject());

		var balanceColumn = new JFXTreeTableColumn<CustomerTable, Integer>("Balance");
		balanceColumn.setCellValueFactory(p -> p.getValue().getValue().totalAmountProperty().asObject());
		balanceColumn.setCellFactory(p -> new OnPressedEditableTreeTableCell<>(new TextFieldEditorBuilder()));

		var dateColumn = new JFXTreeTableColumn<CustomerTable, String>("Date");
		dateColumn.setCellValueFactory(p -> p.getValue().getValue().dateProperty());

		var crateColumn = new JFXTreeTableColumn<CustomerTable, Integer>("Crate");
		crateColumn.setCellValueFactory(p -> p.getValue().getValue().crateProperty().asObject());
		crateColumn.setCellFactory(p -> new OnPressedEditableTreeTableCell<>(new TextFieldEditorBuilder()));

		var returnCrateColumn = new JFXTreeTableColumn<CustomerTable, Integer>("Pending Crate");
		returnCrateColumn.setCellValueFactory(p -> p.getValue().getValue().returnCrateProperty().asObject());
		returnCrateColumn.setCellFactory(p -> new OnPressedEditableTreeTableCell<>(new TextFieldEditorBuilder()));

		var mobileColumn = new JFXTreeTableColumn<CustomerTable, String>("Mobile no");
		mobileColumn.setCellValueFactory(p -> p.getValue().getValue().mobileProperty());
		mobileColumn.setCellFactory(p -> new OnPressedEditableTreeTableCell<>(new TextFieldEditorBuilder()));

		var customer = getAllCustomer();
		customer.forEach(c -> customerOb.add(new CustomerTable(c)));
		customerTable.setEditable(true);
//		customerTable.setIte
	}

	@FXML
	private void customerDisplay() {
		customerPane.toFront();
		customerReleased.toBack();
	}

	@FXML
	private void stockDisplay() {
		customerPane.toBack();
		customerReleased.toFront();
	}

	private void customerValidControl() {
		var reqValidator = new RequiredFieldValidator("Field required.");
		var lengthValidator = new LengthValidation(10, "Field must be ");
		var numberValidator = new NumberValidator("Field accept only number.");
		
		cRate.getValidators().addAll(reqValidator, numberValidator);
		cRate.focusedProperty().addListener((o, oldValue, newValue) -> focused(newValue));
		cRate.textProperty().addListener((o, oldValue, newValue) -> textValid(newValue));

		cName.getValidators().addAll(reqValidator, numberValidator);
		cName.focusedProperty().addListener((o, oldValue, newValue) -> focused(newValue));
		cName.textProperty().addListener((o, oldValue, newValue) -> textValid(newValue));
		
		cWeight.getValidators().addAll(reqValidator, numberValidator);
		cWeight.focusedProperty().addListener((o, oldValue, newValue) -> focused(newValue));
		cWeight.textProperty().addListener((o, oldValue, newValue) -> textValid(newValue));

		cAmount.getValidators().addAll(reqValidator, numberValidator);
		cAmount.focusedProperty().addListener((o, oldValue, newValue) -> focused(newValue));
		cAmount.textProperty().addListener((o, oldValue, newValue) -> textValid(newValue));

		cCrate.getValidators().addAll(reqValidator, numberValidator);
		cCrate.focusedProperty().addListener((o, oldValue, newValue) -> focused(newValue));
		cCrate.textProperty().addListener((o, oldValue, newValue) -> textValid(newValue));

		cMobile.getValidators().addAll(reqValidator, lengthValidator);
		cMobile.focusedProperty().addListener((o, oldValue, newValue) -> focused(newValue));
		cMobile.textProperty().addListener((o, oldValue, newValue) -> textValid(newValue));

	}

	private void textValid(String newValue) {
		if (!newValue.isEmpty())
			cRate.validate();
	}

	private void focused(Boolean newValue) {
		if (!newValue)
			cRate.validate();
	}

	@SuppressWarnings("unchecked")
	private void bindingFields() {
		var weightBind = new SimpleIntegerProperty();
		var rateBind = new SimpleIntegerProperty();
		var totalBind = new SimpleIntegerProperty();
		totalBind.bind(weightBind.multiply(rateBind));
		StringConverter<? extends Number> converter = new IntegerStringConverter();
		Bindings.bindBidirectional(cWeight.textProperty(), weightBind, (StringConverter<Number>) converter);
		Bindings.bindBidirectional(cRate.textProperty(), rateBind, (StringConverter<Number>) converter);
		totalAmount.textProperty().bind(Bindings.format("Total Amount %s", totalBind));
	}

	private void clear() {
		cName.clear();
		cMobile.clear();
		cWeight.clear();
		cAmount.clear();
		cCrate.clear();
		cRate.clear();
	}

	private void logout() {
		var stage = new Stage();
		stage.setTitle(title);
		setIcon(stage);
		var scene = new Scene(fxWeaver.loadView(AuthenticatedController.class));
		stage.setScene(scene);
		stage.initStyle(StageStyle.TRANSPARENT);
		stage.show();
		root.getScene().getWindow().hide();
	}

	private void setIcon(Stage stage) {
		try {
			stage.getIcons().add(new Image(new ClassPathResource("image/icon.png").getInputStream()));
		} catch (IOException e) {
			log.error("Unable to load ICON !! {}", e.getMessage());
		}
	}

	@Transactional(readOnly = true)
	public List<Customer> getAllCustomer() {
		var list = new ArrayList<Customer>();
		customerRepository.findAll().forEach(list::add);
		return list;
	}

	@Transactional
	public Customer save(Customer customer) {
		return customerRepository.save(customer);
	}

	@Transactional
	public String billId() {
		var customerId = customerRepository.findTopByOrderByIdDesc();
		if (customerId.isPresent()) {
			var customer = customerId.get();
			var id = Integer.parseInt(customer.getBillId().substring(4));
			return "CUST" + (id + 1);
		}
		return "CUST1";
	}
}
