package com.kani.oams.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kani.oams.entity.Cart;
import com.kani.oams.entity.CartMedicine;
import com.kani.oams.service.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	private CartService cartService;

	@PostMapping("/createcart")
	public ResponseEntity<Cart> createCart(@RequestBody Cart cart) {
		Cart newCart = cartService.createCart(cart);
		return ResponseEntity.ok(newCart);
	}

	@GetMapping("/cartinfo/{customerId}")
	public ResponseEntity<Cart> viewCartInfo(@PathVariable("customerId") int customerId) throws Exception {
		Cart cart = cartService.viewCartInfo(customerId);
		return ResponseEntity.ok(cart);
	}

	@GetMapping("/viewmedicines/{cartId}")
	public ResponseEntity<List<CartMedicine>> viewMedicines(@PathVariable("cartId") int cartId) {
		List<CartMedicine> cartMedicines = cartService.viewMedicines(cartId);
		return ResponseEntity.ok(cartMedicines);
	}

	@PostMapping("/addmedicine")
	public ResponseEntity<CartMedicine> addMedicine(@RequestBody CartMedicine cartMedicine) {
		CartMedicine addedMedicine = cartService.addMedicine(cartMedicine);
		return ResponseEntity.ok(addedMedicine);

	}

	@DeleteMapping("/deletemedicine")
	public ResponseEntity<String> deleteMedicine(@RequestBody CartMedicine cartMedicine) {
		cartService.deleteMedicine(cartMedicine);
		return ResponseEntity.ok("Medicine removed from cart");
	}

	@DeleteMapping("/deleteallmedicines/{cartId}")
	public ResponseEntity<String> deleteAllMedicines(@PathVariable("cartId") int cartId) {
		cartService.deleteAllMedicines(cartId);
		return ResponseEntity.ok("All medicines removed from cart");
	}

}
