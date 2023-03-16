package com.kani.oams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kani.oams.entity.Customer;
import com.kani.oams.entity.Order;


public interface OrderRepository extends JpaRepository<Order, Integer>{
	
	public List<Order> findByCustomer(Customer customer);
}
