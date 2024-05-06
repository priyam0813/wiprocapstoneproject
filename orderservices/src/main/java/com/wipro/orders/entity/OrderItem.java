package com.wipro.orders.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name="order_itm_tbl")
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private int orderItemId;
	
	private int quntity;
	private int medicineId;
	private double itemTotal;
	
	
	@ManyToOne
	@JoinColumn(name="order_id")
	@JsonIgnore
	private Order order;
	

}
