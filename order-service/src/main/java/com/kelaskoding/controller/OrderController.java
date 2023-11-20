package com.kelaskoding.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kelaskoding.dto.OrderResponse;
import com.kelaskoding.entity.Order;
import com.kelaskoding.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/{id}")
    public OrderResponse findById(@PathVariable("id") Long id) {
        return orderService.findById(id);
    }

    @GetMapping("/order-number/{number}")
    public OrderResponse findByOrderNumber(@PathVariable("number") String number) {
        return orderService.findByOrderNumber(number);
    }

    @PostMapping
    public Order save(@RequestBody Order order) {
        return orderService.save(order);
    }
}
