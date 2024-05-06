package com.wipro.users.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.users.entity.Customer;
import com.wipro.users.entity.User;
import com.wipro.users.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@GetMapping("/all")
	public List<Customer> fetchAllCustomer() {
		List<Customer> customers = customerService.getAllCustomer();
		return customers;
	}

	@PostMapping("/save")
	public ResponseEntity<Customer> addUser(@Valid @RequestBody Customer customer) {
		customerService.addCustomer(customer);
		return new ResponseEntity<>(customer, HttpStatus.CREATED);
	}

	@PutMapping("/update")
	public ResponseEntity<User> updateUser(@Valid @RequestBody Customer customer) {
		customerService.updateCustomer(customer);
		ResponseEntity<User> responseEntity = new ResponseEntity<>(customer, HttpStatus.OK);
		return responseEntity;
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> deleteCustomer(@PathVariable("id") long userId) {
		customerService.deleteCustomer(userId);
		ResponseEntity<User> ResponseEntity = new ResponseEntity<>(HttpStatus.OK);
		return ResponseEntity;

	}

	@GetMapping("/{id}")
	public ResponseEntity<Customer> fetchAllDetails(@PathVariable("id") long userId) {
		Customer customer = customerService.getCustomerById(userId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

}
