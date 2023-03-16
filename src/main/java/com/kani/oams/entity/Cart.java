package com.kani.oams.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Cart {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int id;
	private LocalDate createdDate;
	@OneToOne
	@JoinColumn(name = "customer_id")
	private Customer customer;
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CartMedicine> cartMedicines = new ArrayList<CartMedicine>();
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDate createdDate) {
		this.createdDate = createdDate;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartMedicine> getCartMedicines() {
		return cartMedicines;
	}

	public void setCartMedicines(List<CartMedicine> cartMedicines) {
		this.cartMedicines = cartMedicines;
	}
	
	//This method is added to avoid issue with nested cascade & cyclic references
	public void addCartMedicine(CartMedicine cartMedicine) {
		cartMedicine.setCart(this);
		this.getCartMedicines().add(cartMedicine);
	}

}
