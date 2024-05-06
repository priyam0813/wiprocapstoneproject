package com.wipro.orders.service;

import java.util.List;

import com.wipro.orders.entity.Order;
import com.wipro.orders.model.OrderResponse;
import com.wipro.orders.payload.OrderItemPayload;


public interface OrderService {
	
	Order createOrder(long userId,List<OrderItemPayload> selectedProducts);
	
	Order saveOrder(Order order);
	
	OrderResponse getOrderDetails(int orderId);
	
	List<OrderResponse> getAllOrders();

	Order updateOrder(Order order);

	void deleteOrder(int orderId);
}
