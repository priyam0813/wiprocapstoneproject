package com.wipro.orders.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponse {
	private int orderItemId;
	
	private int quntity;
	private Medicine medicine;
	private double itemTotal;
	
}
