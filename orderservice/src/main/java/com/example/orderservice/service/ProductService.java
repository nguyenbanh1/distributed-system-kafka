package com.example.orderservice.service;

import com.example.orderservice.dto.ProductDto;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    public ProductDto calculateProducts(Long productId, Integer quality) {
        return new ProductDto();
    }
}
