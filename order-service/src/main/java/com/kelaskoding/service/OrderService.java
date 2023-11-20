package com.kelaskoding.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelaskoding.dto.OrderLineResponse;
import com.kelaskoding.dto.OrderResponse;
import com.kelaskoding.dto.Product;
import com.kelaskoding.entity.Order;
import com.kelaskoding.entity.OrderLine;
import com.kelaskoding.repository.OrderRepo;
import com.kelaskoding.webclient.CustomerClient;
import com.kelaskoding.webclient.ProductClient;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;

    // @Autowired
    // private RestTemplate restTemplate;

    @Autowired
    private CustomerClient customerClient;

    @Autowired
    private ProductClient productClient;

    public Order save(Order order) {
        for (OrderLine orderLine : order.getOrderLines()) {
            orderLine.setOrder(order);
        }
        return orderRepo.save(order);
    }

    @CircuitBreaker(name = "customerService", fallbackMethod = "fallbackFindCustomerById")
    public OrderResponse findById(Long id) {
        Optional<Order> optOrder = orderRepo.findById(id);
        if (!optOrder.isPresent()) {
            return null;
        }

        Order order = optOrder.get();
        OrderResponse response = new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderDate(),
                customerClient.findById(order.getCustomerId()), new ArrayList<OrderLineResponse>());

        for (OrderLine orderLine : order.getOrderLines()) {
            Product product = productClient.findById(orderLine.getProductId());
            response.getOrderLines().add(new OrderLineResponse(orderLine.getId(),
                    product, orderLine.getQuantity(),
                    orderLine.getPrice()));
        }
        return response;
    }

    private OrderResponse fallbackFindCustomerById(Long id, Throwable throwable) {
        return new OrderResponse();
    }

    public OrderResponse findByOrderNumber(String orderNumber) {
        Order order = orderRepo.findByOrderNumber(orderNumber);
        if (order == null) {
            return null;
        }

        OrderResponse response = new OrderResponse(order.getId(), order.getOrderNumber(), order.getOrderDate(),
                customerClient.findById(order.getCustomerId()), new ArrayList<OrderLineResponse>());

        for (OrderLine orderLine : order.getOrderLines()) {
            Product product = productClient.findById(orderLine.getProductId());
            response.getOrderLines().add(new OrderLineResponse(orderLine.getId(), product, orderLine.getQuantity(),
                    orderLine.getPrice()));
        }
        return response;
    }

    // public Customer findCustomerById(Long id) {
    // return restTemplate.getForObject("http://localhost:8081/api/customers/" +
    // id, Customer.class);
    // }

    // public Product findProductById(Long id) {
    // return restTemplate.getForObject("http://localhost:8082/api/products/" + id,
    // Product.class);
    // }
}
