package com.example.orderservice.message;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.util.MimeTypeUtils;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderProducerService {

    private final OrderProcessor orderProcessor;

    public void publishMakingPayment(MakingPaymentEvent paymentEvent) {
        String eventUuid = UUID.randomUUID().toString();
        MessageChannel messageChannel = orderProcessor.approved();
        messageChannel.send(MessageBuilder
                .withPayload(paymentEvent)
                .setHeader(MessageHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON)
                .setHeader(MessageHeaders.ID, eventUuid)
                .build()
        );
        log.info("Publish Making payment: {} - {}", paymentEvent, eventUuid);
    }

}
