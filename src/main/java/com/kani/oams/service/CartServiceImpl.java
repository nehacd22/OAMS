package com.kani.oams.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.kani.oams.entity.Cart;
import com.kani.oams.entity.CartMedicine;
import com.kani.oams.entity.Customer;
import com.kani.oams.exceptions.CartNotFoundException;
import com.kani.oams.exceptions.CustomerNotFoundException;
import com.kani.oams.repository.CartMedicineRepository;
import com.kani.oams.repository.CartRepository;
import com.kani.oams.repository.CustomerRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CartServiceImpl implements CartService {

	// Messages
	static final String CUSTOMER_NOT_FOUND = "customer-not-found";
	static final String CART_NOT_FOUND = "cart-not-found";

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CartMedicineRepository cmRepository;
	@Autowired
	private MessageSource msgSource;

	@Override
	public Cart createCart(Cart cart) {
		// Check if customer exists in db
		doesCustomerExist(cart.getCustomer().getId());
		// Save cart for customer if customer is valid
		Cart newCart = cartRepository.save(cart);
		return newCart;
	}

	@Override
	public Cart viewCartInfo(int customerId) {
		// Check if customer exists in db
		Customer customer = doesCustomerExist(customerId);
		return cartRepository.findByCustomer(customer);
	}

	@Override
	public List<CartMedicine> viewMedicines(int cartId) {
		// Check if cart id is valid
		Cart cart = doesCartExist(cartId);
		// Fetch medicines for the cart
		return cmRepository.findByCart(cart);
	}

	@Override
	public CartMedicine addMedicine(CartMedicine cartMedicine) {
		return cmRepository.save(cartMedicine);
	}

	@Override
	public void deleteMedicine(CartMedicine cartMedicine) {
		cmRepository.delete(cartMedicine);
	}

	@Override
	public void deleteAllMedicines(int cartId) {
		Optional<Cart> optCart = cartRepository.findById(cartId);
		List<CartMedicine> allCartMeds = cmRepository.findByCart(optCart.get());
		cmRepository.deleteAll(allCartMeds);
	}

	private Customer doesCustomerExist(int customerId) {
		Optional<Customer> optCustomer = customerRepository.findById(customerId);
		Customer customer = optCustomer.orElseThrow(() -> new CustomerNotFoundException(
				msgSource.getMessage(CUSTOMER_NOT_FOUND, new String[] { String.valueOf(customerId) }, null)));
		return customer;
	}

	private Cart doesCartExist(int cartId) {
		Optional<Cart> optCart = cartRepository.findById(cartId);
		Cart cart = optCart.orElseThrow(() -> new CartNotFoundException(
				msgSource.getMessage(CART_NOT_FOUND, new String[] { String.valueOf(cartId) }, null)));
		return cart;

	}

}
