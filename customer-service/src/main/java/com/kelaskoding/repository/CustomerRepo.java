package com.kelaskoding.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kelaskoding.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Long> {

    Customer findByEmail(String email);

}
