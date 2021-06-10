package com.example.orderservice.message;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MakingPaymentEvent extends Event {
    private Long orderId;
    private Long amount;
}
