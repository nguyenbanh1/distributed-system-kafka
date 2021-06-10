package com.example.orderservice.message;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;

public interface OrderProcessor {
    String MAKE_PAYMENT_OUTPUT = "make-payment";

    @Output(MAKE_PAYMENT_OUTPUT)
    MessageChannel approved();
}
