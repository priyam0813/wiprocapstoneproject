package com.wipro.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.orders.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
