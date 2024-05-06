package com.wipro.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.orders.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer>{

}
