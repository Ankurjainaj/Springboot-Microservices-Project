package com.microserviceProject.ProductService.controller;

import com.microserviceProject.ProductService.dto.ProductRequest;
import com.microserviceProject.ProductService.dto.ProductResponse;
import com.microserviceProject.ProductService.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping("/createProduct")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequest request) {
        service.createProduct(request);
    }

    @GetMapping("/getAllProducts")
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        List<ProductResponse> productResponses = service.getAllProducts();
        return productResponses;
    }
}
