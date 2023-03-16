package com.kani.oams.service;

import java.util.List;

import com.kani.oams.entity.Cart;
import com.kani.oams.entity.CartMedicine;

public interface CartService {
	
	public Cart createCart(Cart cart);
	
	public Cart viewCartInfo(int customerId);
	
	public List<CartMedicine> viewMedicines(int cartId);
	
	public CartMedicine addMedicine(CartMedicine cartMedicine);

	public void deleteMedicine(CartMedicine cartMedicine);
	
	public void deleteAllMedicines(int cartId);
	
	public default void newMethod() {
		System.out.println("Test default method");
	}
	
}
