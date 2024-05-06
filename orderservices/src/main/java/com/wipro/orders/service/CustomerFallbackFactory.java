package com.wipro.orders.service;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.wipro.orders.model.Customer;
@Component
public class CustomerFallbackFactory implements FallbackFactory<CustomerServicesConsumer>{

	@Override
	public CustomerServicesConsumer create(Throwable cause) {
		// TODO Auto-generated method stub
		return new CustomerServicesConsumer() {
			
			@Override
			public Customer getCoustomerDetailsById(long userId) {
				// TODO Auto-generated method stub
				return new Customer();
			}
		};
	}

}
