package com.afc.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private Integer id;
	private String name;
	@Column(unique = true)
	private String billId;
	private String mobile;
	private Integer weight;
	private Integer rate;
	private Integer crate;
	private Integer returnCrate;
	private Integer totalAmount;
	private Integer balance;
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "billId")
	private List<CustomerAmount> customerAmounts = new ArrayList<>();

	public Customer() {
	}

	public Customer(Integer id, String name, String billId, String mobile, Integer weight, Integer rate, Integer crate,
			Integer returnCrate, Integer totalAmount, Integer balance, List<CustomerAmount> customerAmounts) {
		this.id = id;
		this.name = name;
		this.billId = billId;
		this.mobile = mobile;
		this.weight = weight;
		this.rate = rate;
		this.crate = crate;
		this.returnCrate = returnCrate;
		this.totalAmount = totalAmount;
		this.balance = balance;
		this.customerAmounts = customerAmounts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBillId() {
		return billId;
	}

	public void setBillId(String billId) {
		this.billId = billId;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getRate() {
		return rate;
	}

	public void setRate(Integer rate) {
		this.rate = rate;
	}

	public Integer getCrate() {
		return crate;
	}

	public void setCrate(Integer crate) {
		this.crate = crate;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getReturnCrate() {
		return returnCrate;
	}

	public void setReturnCrate(Integer returnCrate) {
		this.returnCrate = returnCrate;
	}

	public Integer getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Integer totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getBalance() {
		return balance;
	}

	public void setBalance(Integer balance) {
		this.balance = balance;
	}

	public List<CustomerAmount> getCustomerAmounts() {
		return customerAmounts;
	}

	public void setCustomerAmounts(List<CustomerAmount> customerAmounts) {
		this.customerAmounts = customerAmounts;
	}

	@Override
	public int hashCode() {
		return Objects.hash(balance, billId, crate, id, name, rate, returnCrate, totalAmount, weight);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Customer))
			return false;
		var other = (Customer) obj;
		return Objects.equals(balance, other.balance) && Objects.equals(billId, other.billId)
				&& Objects.equals(crate, other.crate) && Objects.equals(id, other.id)
				&& Objects.equals(name, other.name) && Objects.equals(rate, other.rate)
				&& Objects.equals(returnCrate, other.returnCrate) && Objects.equals(totalAmount, other.totalAmount)
				&& Objects.equals(weight, other.weight);
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", billId=" + billId + ", weight=" + weight + ", rate=" + rate
				+ ", crate=" + crate + ", returnCrate=" + returnCrate + ", totalAmount=" + totalAmount + ", balance="
				+ balance + "]";
	}

}
