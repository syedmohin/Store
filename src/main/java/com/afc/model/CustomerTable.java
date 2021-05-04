package com.afc.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class CustomerTable {
	private StringProperty billId;
	private StringProperty name;
	private IntegerProperty weigth;
	private IntegerProperty rate;
	private IntegerProperty crate;
	private IntegerProperty returnCrate;
	private IntegerProperty totalAmount;
	private IntegerProperty balance;
	private StringProperty date;

	private static final DateTimeFormatter pattern = DateTimeFormatter.ofPattern("dd-MM-YYYY");

	public CustomerTable(String billId, String name, Integer weigth, Integer rate, Integer crate, Integer returnCrate,
			Integer totalAmount, Integer balance, LocalDate date) {
		this.billId = new SimpleStringProperty(billId);
		this.name = new SimpleStringProperty(name);
		this.weigth = new SimpleIntegerProperty(weigth);
		this.rate = new SimpleIntegerProperty(rate);
		this.crate = new SimpleIntegerProperty(crate);
		this.returnCrate = new SimpleIntegerProperty(returnCrate);
		this.totalAmount = new SimpleIntegerProperty(totalAmount);
		this.balance = new SimpleIntegerProperty(balance);
		this.date = new SimpleStringProperty(pattern.format(date));
	}

	public CustomerTable(Customer customer) {
		var amounts = customer.getCustomerAmounts();
		this.billId = new SimpleStringProperty(customer.getBillId());
		this.name = new SimpleStringProperty(customer.getName());
		this.weigth = new SimpleIntegerProperty(customer.getWeight());
		this.rate = new SimpleIntegerProperty(customer.getRate());
		this.crate = new SimpleIntegerProperty(customer.getCrate());
		this.returnCrate = new SimpleIntegerProperty(customer.getReturnCrate());
		this.totalAmount = new SimpleIntegerProperty(getTotalAmount());
		this.balance = new SimpleIntegerProperty(customer.getBalance());
		this.date = new SimpleStringProperty(pattern.format(amounts.get(amounts.size() - 1).getDate()));
	}

	public final StringProperty billIdProperty() {
		return this.billId;
	}

	public final String getBillId() {
		return this.billIdProperty().get();
	}

	public final void setBillId(final String billId) {
		this.billIdProperty().set(billId);
	}

	public final StringProperty nameProperty() {
		return this.name;
	}

	public final String getName() {
		return this.nameProperty().get();
	}

	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final IntegerProperty weigthProperty() {
		return this.weigth;
	}

	public final int getWeigth() {
		return this.weigthProperty().get();
	}

	public final void setWeigth(final int weigth) {
		this.weigthProperty().set(weigth);
	}

	public final IntegerProperty rateProperty() {
		return this.rate;
	}

	public final int getRate() {
		return this.rateProperty().get();
	}

	public final void setRate(final int rate) {
		this.rateProperty().set(rate);
	}

	public final IntegerProperty crateProperty() {
		return this.crate;
	}

	public final int getCrate() {
		return this.crateProperty().get();
	}

	public final void setCrate(final int crate) {
		this.crateProperty().set(crate);
	}

	public final IntegerProperty returnCrateProperty() {
		return this.returnCrate;
	}

	public final int getReturnCrate() {
		return this.returnCrateProperty().get();
	}

	public final void setReturnCrate(final int returnCrate) {
		this.returnCrateProperty().set(returnCrate);
	}

	public final IntegerProperty totalAmountProperty() {
		return this.totalAmount;
	}

	public final int getTotalAmount() {
		return this.totalAmountProperty().get();
	}

	public final void setTotalAmount(final int totalAmount) {
		this.totalAmountProperty().set(totalAmount);
	}

	public final IntegerProperty balanceProperty() {
		return this.balance;
	}

	public final int getBalance() {
		return this.balanceProperty().get();
	}

	public final void setBalance(final int balance) {
		this.balanceProperty().set(balance);
	}

	public final StringProperty dateProperty() {
		return this.date;
	}

	public final String getDate() {
		return this.dateProperty().get();
	}

	public final void setDate(final LocalDate date) {
		this.dateProperty().set(pattern.format(date));
	}

}
