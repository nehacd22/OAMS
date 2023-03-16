package com.kani.oams.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.kani.oams.entity.Cart;
import com.kani.oams.entity.CartMedicine;
import com.kani.oams.entity.Customer;
import com.kani.oams.entity.Order;
import com.kani.oams.entity.OrderMedicine;
import com.kani.oams.exceptions.OrderNotFoundException;
import com.kani.oams.repository.CartRepository;
import com.kani.oams.repository.CustomerRepository;
import com.kani.oams.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	static final String ORDER_NOT_FOUND = "order-not-found";

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private MessageSource msgSource;

	@Override
	public Order addOrder(int cartId) throws Exception {
		Order newOrder = null;
		Cart cart = cartRepository.findById(cartId).get();
		LocalDate currentDate = LocalDate.now();
		// Create new order from cart
		Order order = new Order(currentDate, currentDate, Order.STATUS_NEW, cart.getCustomer());
		List<CartMedicine> cartMedicines = cart.getCartMedicines();
		OrderMedicine orderMedicine = null;
		double totalCost = 0;
		// Create order medicines from cart medicines
		for (CartMedicine cm : cartMedicines) {
			orderMedicine = new OrderMedicine(order, cm.getMedicine(), cm.getQuantity(), cm.getCost());
			order.addOrderMedicine(orderMedicine);
			totalCost = totalCost + (cm.getQuantity() * cm.getCost());
		}
		// Update total cost of order after calculating sum from all medicines in order
		order.setTotalCost(totalCost);
		newOrder = orderRepository.save(order);
		return newOrder;
	}

	@Override
	public Order updateOrder(Order order) throws Exception {
		// Check if order exists for the id
		Order fetchedOrder = doesOrderExist(order.getId());
		if (order.getCustomer() != null) { 
			fetchedOrder.setCustomer(order.getCustomer());
		}
		if (order.getDispatchDate() != null) {
			fetchedOrder.setDispatchDate(order.getDispatchDate());
		}
		if (order.getOrderDate() != null) {
			fetchedOrder.setOrderDate(order.getOrderDate());
		}
		if (order.getStatus() != null) {
			fetchedOrder.setStatus(order.getStatus());
		}
		// Then update the order		
		return orderRepository.save(fetchedOrder);
	}

	@Override
	public Order updateOrderStatus(int orderId, String status) throws Exception {
		Order order = doesOrderExist(orderId);
		order.setStatus(status);
		return orderRepository.save(order);
	}

	@Override
	public Order cancelOrder(int orderId) throws Exception {
		return updateOrderStatus(orderId, Order.STATUS_CANCELLED);
	}

	@Override
	public Order calculateTotalCost(int orderId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order showOrder(int orderId)  {
		return orderRepository.findById(orderId).get();
	}

	@Override
	public List<Order> showAllOrders() throws Exception {
		return orderRepository.findAll();
	}

	@Override
	public List<Order> showOrdersByCustomer(int customerId) {
		Customer customer = customerRepository.findById(customerId).get();
		return orderRepository.findByCustomer(customer);
	}

	private Order doesOrderExist(int orderId) {
		Optional<Order> optOrder = orderRepository.findById(orderId);
		Order order = optOrder.orElseThrow(() -> new OrderNotFoundException(
				msgSource.getMessage(ORDER_NOT_FOUND, new String[] { String.valueOf(orderId) }, null)));
		return order;
	}

}
