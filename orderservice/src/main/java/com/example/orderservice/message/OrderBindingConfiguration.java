package com.example.orderservice.message;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.stream.annotation.EnableBinding;

@Configurable
@EnableBinding(OrderProcessor.class)
public class OrderBindingConfiguration {
}
