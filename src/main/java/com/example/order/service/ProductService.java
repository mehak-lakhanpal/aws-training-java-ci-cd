package com.example.order.service;

import com.example.order.entity.Product;
import com.example.order.respository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Product create(Product product) {
        product.setProductId(UUID.randomUUID().toString());
        repository.save(product);
        return product;
    }

    public Product get(String id) {
        return repository.findById(id);
    }

    public List<Product> byCategory(String category) {
        return repository.findByCategory(category);
    }
}