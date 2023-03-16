package com.kani.oams.entity.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kani.oams.entity.Order;
import com.kani.oams.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/add/{cartId}")
	public ResponseEntity<Order> addOrder(@PathVariable int cartId) throws Exception {
		Order newOrder = orderService.addOrder(cartId);
		return ResponseEntity.ok(newOrder);
	}

	@PutMapping("/update")
	public ResponseEntity<Order> updateOrder(@RequestBody Order order) throws Exception {
		Order updatedOrder = orderService.updateOrder(order);
		return ResponseEntity.ok(updatedOrder);
	}

	@PutMapping("/updatestatus/{id}")
	public ResponseEntity<Order> updateOrderStatus(@PathVariable int id, @RequestBody String status) throws Exception {
		Order updatedOrder = orderService.updateOrderStatus(id, status);
		return ResponseEntity.ok(updatedOrder);
	}

	@PutMapping("/cancel/{id}")
	public ResponseEntity<Order> cancelOrder(@PathVariable int id) throws Exception {
		Order updatedOrder = orderService.cancelOrder(id);
		return ResponseEntity.ok(updatedOrder);
	}

	@GetMapping("/show/{id}")
	public ResponseEntity<Order> showOrder(@PathVariable int id) throws Exception {
		Order order = orderService.showOrder(id);
		return ResponseEntity.ok(order);
	}

	@GetMapping("/showall")
	public ResponseEntity<List<Order>> showAllOrders() throws Exception {
		List<Order> allOrders = orderService.showAllOrders();
		return ResponseEntity.ok(allOrders);
	}

	@GetMapping("/showbycustomer/{customerId}")
	public ResponseEntity<List<Order>> showAllOrders(@PathVariable int customerId) throws Exception {
		List<Order> customerOrders = orderService.showOrdersByCustomer(customerId);
		return ResponseEntity.ok(customerOrders);
	}

}
