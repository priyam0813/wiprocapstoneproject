package com.wipro.orders.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Customer {

	
	private long userId;
	private String firstName;
	private String lastName;
	private String address;
	private String phoneNo;
}
