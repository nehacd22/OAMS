package com.kani.oams.service;

import java.util.List;

import com.kani.oams.entity.Order;

public interface OrderService {
	
	public Order addOrder(int cartId) throws Exception;
	
	public Order updateOrder(Order order) throws Exception;
	
	public Order updateOrderStatus(int orderId, String status) throws Exception;
	
	public Order cancelOrder(int orderId) throws Exception;
	
	public Order calculateTotalCost(int orderId) throws Exception;
	
	public Order showOrder(int orderId) throws Exception;
	
	public List<Order> showAllOrders() throws Exception;
	
	public List<Order> showOrdersByCustomer(int customerId) throws Exception;
	
}
