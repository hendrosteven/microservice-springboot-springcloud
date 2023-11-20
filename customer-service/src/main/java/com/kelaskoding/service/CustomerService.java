package com.kelaskoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelaskoding.entity.Customer;
import com.kelaskoding.repository.CustomerRepo;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepo customerRepo;

    public Customer save(Customer customer) {
        return customerRepo.save(customer);
    }

    public Customer findById(Long id) {
        return customerRepo.findById(id).orElse(null);
    }

    public Iterable<Customer> findAll() {
        return customerRepo.findAll();
    }

    public Customer findByEmail(String email) {
        return customerRepo.findByEmail(email);
    }
}
