package com.kani.oams.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kani.oams.entity.OrderMedicine;


public interface OrderMedicineRepository extends JpaRepository<OrderMedicine, Integer>{
	
	
}
