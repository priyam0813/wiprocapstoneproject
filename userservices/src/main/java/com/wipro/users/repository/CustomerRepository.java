package com.wipro.users.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.users.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	

}
