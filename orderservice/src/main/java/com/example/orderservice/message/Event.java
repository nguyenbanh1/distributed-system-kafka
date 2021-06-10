package com.example.orderservice.message;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public abstract class Event {
    private UUID eventId;
}
