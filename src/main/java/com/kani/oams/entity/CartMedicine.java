package com.kani.oams.entity;

import com.fasterxml.jackson.annotation.JsonIdentityReference;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class CartMedicine {
	
	@Id
	private int id;
	@ManyToOne
	@JoinColumn(name = "cart_id")
	@JsonIdentityReference(alwaysAsId = true)
	private Cart cart;
	@ManyToOne
	@JoinColumn(name = "medicine_id")
	@JsonIdentityReference(alwaysAsId = true)
	private Medicine medicine;
	private int quantity;
	private float cost;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Cart getCart() {
		return cart;
	}
	public void setCart(Cart cart) {
		this.cart = cart;
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
	public float getCost() {
		return cost;
	}
	public void setCost(float cost) {
		this.cost = cost;
	}
	
	
	

}
