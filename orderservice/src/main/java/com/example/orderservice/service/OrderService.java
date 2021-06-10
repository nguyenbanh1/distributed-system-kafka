package com.example.orderservice.service;

import com.example.orderservice.dto.OrderRequest;
import com.example.orderservice.dto.ProductDto;
import com.example.orderservice.entity.Order;
import com.example.orderservice.entity.OrderDetail;
import com.example.orderservice.entity.OrderStatus;
import com.example.orderservice.mapper.OrderMapper;
import com.example.orderservice.message.MakingPaymentEvent;
import com.example.orderservice.message.OrderProducerService;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final OrderProducerService orderProducerService;

    @Transactional
    public void createOrder(OrderRequest orderReq) {
        Order order = OrderMapper.INSTANCE.of(orderReq);
        order.setOrderDate(Date.from(Instant.now()));
        order.setOrderStatus(OrderStatus.PENDING);
        Set<OrderDetail> orderDetails = orderReq.getOrderProducts()
                .stream()
                .map(item -> {
                    OrderDetail orderDetail = new OrderDetail();
                    ProductDto product = productService.calculateProducts(item.getProductId(), item.getQuantity());
                    orderDetail.setProduct(product.getProductId());
                    orderDetail.setOrder(order);
                    orderDetail.setQuantity(item.getQuantity());
                    orderDetail.setAmount(product.getAmount());
                    return orderDetail;
                }).collect(Collectors.toSet());
        order.setOrderDetails(orderDetails);
        orderRepository.save(order);
        MakingPaymentEvent event = new MakingPaymentEvent();
        event.setOrderId(order.getId());
        Long amount = order.getOrderDetails()
                .stream()
                .map(OrderDetail::getAmount).reduce(Long::sum).orElse(0L);
        event.setAmount(amount);
        orderProducerService.publishMakingPayment(event);
    }
}
