package com.wipro.orders.entity;

import java.time.LocalDate;
import java.util.List;



import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="orders_tbl")
public class Order {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderId;
	private LocalDate orderDate;
	private String orderStatus;
	private double orderPrice;
	private double orderTotal;
	
	private long userId;
	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
	private List<OrderItem> orderItems;

}
