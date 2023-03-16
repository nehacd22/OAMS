package com.kani.oams.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "customer_order")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Order {

	@Transient
	public static final String STATUS_NEW = "New";
	@Transient
	public static final String STATUS_IN_PROCESS = "In Process";
	@Transient
	public static final String STATUS_COMPLETED = "Completed";
	@Transient
	public static final String STATUS_CANCELLED = "Cancelled";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private LocalDate orderDate;
	private LocalDate dispatchDate;
	private double totalCost;
	private String status;
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	@JsonIdentityReference(alwaysAsId = true)
	private Customer customer;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderMedicine> orderMedicines = new ArrayList<OrderMedicine>();

	/*
	 * Default constructor is needed in Spring when we overload with another
	 * constructor, otherwise it gives error while fetching entity from DB
	 */
	public Order() {
		super();
	}

	public Order(LocalDate orderDate, LocalDate dispatchDate, String status, Customer customer) {
		super();
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.status = status;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<OrderMedicine> getOrderMedicines() {
		return orderMedicines;
	}

	public void setOrderMedicines(List<OrderMedicine> orderMedicines) {
		this.orderMedicines = orderMedicines;
	}

	public void addOrderMedicine(OrderMedicine orderMedicine) {
		this.getOrderMedicines().add(orderMedicine);
		orderMedicine.setOrder(this);
	}

}
