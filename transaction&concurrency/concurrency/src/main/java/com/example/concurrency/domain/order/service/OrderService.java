package com.example.concurrency.domain.order.service;

import com.example.concurrency.domain.order.entity.Order;
import com.example.concurrency.domain.order.repo.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
