package com.kelaskoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelaskoding.entity.Order;

public interface OrderRepo extends JpaRepository<Order, Long> {

    Order findByOrderNumber(String orderNumber);
}
