package com.kani.oams.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class OrderMedicine {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@ManyToOne
	@JoinColumn(name = "order_id")
	@JsonIdentityReference(alwaysAsId = true)
	private Order order;
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	@JsonIdentityReference(alwaysAsId = true)
	private Medicine medicine;
	private int quantity;
	private double cost;

	/*
	 * Default constructor is needed in Spring when we overload with another
	 * constructor, otherwise it gives error while fetching entity from DB
	 */
	public OrderMedicine() {
		super();
	}

	public OrderMedicine(Order order, Medicine medicine, int quantity, double cost) {
		super();
		this.order = order;
		this.medicine = medicine;
		this.quantity = quantity;
		this.cost = cost;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

}
