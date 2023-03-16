package com.kani.oams.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kani.oams.entity.Cart;
import com.kani.oams.entity.CartMedicine;


public interface CartMedicineRepository extends JpaRepository<CartMedicine, Integer>{
	
	public List<CartMedicine> findByCart(Cart cart);

}
