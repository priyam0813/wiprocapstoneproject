package com.wipro.users.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.users.entity.Customer;

import com.wipro.users.exception.ResourceNotFoundException;
import com.wipro.users.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer addCustomer(Customer customer) {
		customerRepository.save(customer);
		return customer;
	}

	@Override
	public Customer updateCustomer(Customer cutsomer) {
		Optional<Customer> optionalCustomer = customerRepository.findById(cutsomer.getUserId());
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer Not exit with id:" + cutsomer.getUserId());
		}
		customerRepository.save(cutsomer);
		return cutsomer;
	}

	@Override
	public Customer deleteCustomer(long userId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(userId);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer Not exit with id:" + userId);
		}
		Customer customer = optionalCustomer.get();
		customerRepository.delete(customer);
		return customer;
	}

	@Override
	public List<Customer> getAllCustomer() {
		List<Customer> customerList = customerRepository.findAll();
		return customerList;
	}

	@Override
	public Customer getCustomerById(long userId) {
		Optional<Customer> optionalCustomer = customerRepository.findById(userId);
		if (optionalCustomer.isEmpty()) {
			throw new ResourceNotFoundException("Customer Not exit with id:" + userId);
		}
		Customer customer = optionalCustomer.get();
		return customer;
	}

}
