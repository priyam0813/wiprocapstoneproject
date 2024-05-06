package com.wipro.users.service;

import java.util.List;

import com.wipro.users.entity.Customer;

public interface CustomerService {

	Customer addCustomer(Customer customer);

	Customer getCustomerById(long userId);

	Customer updateCustomer(Customer cutsomer);

	Customer deleteCustomer(long userId);

	List<Customer> getAllCustomer();
}
