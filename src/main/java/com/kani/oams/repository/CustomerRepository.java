package com.kani.oams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kani.oams.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer>{
	
	

}
