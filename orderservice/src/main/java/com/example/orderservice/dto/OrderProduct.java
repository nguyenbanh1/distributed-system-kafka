package com.example.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderProduct {
    private Long productId;
    private Integer quantity;
}