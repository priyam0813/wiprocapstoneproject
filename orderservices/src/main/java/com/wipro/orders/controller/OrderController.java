package com.wipro.orders.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.orders.entity.Order;
import com.wipro.orders.model.OrderResponse;
import com.wipro.orders.payload.OrderItemPayload;
import com.wipro.orders.payload.OrderPayload;
import com.wipro.orders.service.OrderService;

import jakarta.validation.Valid;



@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@PostMapping("/save")
	public ResponseEntity<Order> placeOrder( @RequestBody OrderPayload orderPayload){
		
		long userId = orderPayload.getUserid();
		List<OrderItemPayload> productsOrders = orderPayload.getOrderItems();
		
		Order order = orderService.createOrder(userId, productsOrders);
		Order newOrder = orderService.saveOrder(order);
		return new ResponseEntity<>(newOrder , HttpStatus.CREATED);
	}
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> fetchOrderDetails(@PathVariable("id") int orderId){
		OrderResponse order = orderService.getOrderDetails(orderId);
		return new ResponseEntity<>(order , HttpStatus.OK);
		
	}
	@GetMapping("/all")
	public List<OrderResponse> fetchAllOrders(){
		List<OrderResponse> orders = orderService.getAllOrders();
		return orders;
	}
	@PutMapping("/update")
	public  ResponseEntity<Order> editOrder(@RequestBody Order order) {
		orderService.updateOrder(order);
		ResponseEntity<Order> responseEntity = new ResponseEntity<>(order,HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") int orderId) {
		orderService.deleteOrder(orderId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;	
	}

}
