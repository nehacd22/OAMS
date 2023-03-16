package com.kani.oams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kani.oams.entity.Cart;
import com.kani.oams.entity.Customer;


public interface CartRepository extends JpaRepository<Cart, Integer>{
	
	public Cart findByCustomer(Customer customer);


}
