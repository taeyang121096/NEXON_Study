package com.example.concurrency.domain.order.service;

import com.example.concurrency.domain.order.dto.OrderDto;
import com.example.concurrency.domain.order.entity.Order;
import com.example.concurrency.domain.order.repo.OrderRepository;
import com.example.concurrency.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public Order createOrder(User user, OrderDto orderDto) {
        Order order = Order.builder().count(orderDto.getCount())
                .totalPrice(orderDto.getTotalPrice())
                .registerDate(orderDto.getRegisterDate())
                .build();
        order.setOrderWithUser(user);
        return orderRepository.save(order);
    }
}
