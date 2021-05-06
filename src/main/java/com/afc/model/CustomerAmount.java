package com.afc.model;

import java.time.LocalDate;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CustomerAmount {
	@Id
	@GeneratedValue
	private Integer id;
	private LocalDate date;
	private Integer amount;

	public CustomerAmount() {
	}

	public CustomerAmount(Integer id, LocalDate date, Integer amount) {
		this.id = id;
		this.date = date;
		this.amount = amount;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "CustomerAmount [id=" + id + ", date=" + date + ", amount=" + amount + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amount, date, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		var other = (CustomerAmount) obj;
		return Objects.equals(amount, other.amount) && Objects.equals(date, other.date) && Objects.equals(id, other.id);
	}

}
