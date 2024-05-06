package com.wipro.orders.payload;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderPayload {

	private long userid;
	private List<OrderItemPayload> orderItems = new ArrayList<>();
}
