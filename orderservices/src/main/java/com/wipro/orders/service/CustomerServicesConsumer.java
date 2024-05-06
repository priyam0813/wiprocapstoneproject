package com.wipro.orders.service;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.wipro.orders.model.Customer;

@FeignClient(name="USERSERVICES", fallbackFactory =CustomerFallbackFactory.class)
public interface CustomerServicesConsumer {
	
	@GetMapping("/customer/{id}")
	Customer getCoustomerDetailsById(@PathVariable("id") long userId);
	
}
