package com.wipro.orders.model;

import java.time.LocalDate;
import java.util.List;



import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class OrderResponse {

	private int orderId;
	private LocalDate orderDate;
	private double orderTotal;
	private String orderStatus;
	private double orderPrice;
	private Customer customer;
	private List<OrderItemResponse> orderItem;
}
