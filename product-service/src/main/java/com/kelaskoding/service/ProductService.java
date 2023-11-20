package com.kelaskoding.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kelaskoding.entity.Product;
import com.kelaskoding.repository.ProductRepo;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Product save(Product product) {
        return productRepo.save(product);
    }

    public Product findById(Long id) {
        return productRepo.findById(id).orElse(null);
    }

    public Iterable<Product> findAll() {
        return productRepo.findAll();
    }

}
