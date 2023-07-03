package com.microserviceProject.ProductService.repository;

import com.microserviceProject.ProductService.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
