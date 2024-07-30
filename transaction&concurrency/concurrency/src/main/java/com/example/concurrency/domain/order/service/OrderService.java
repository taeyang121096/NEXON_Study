package com.example.concurrency.domain.order.service;

import com.example.concurrency.domain.item.entity.Item;
import com.example.concurrency.domain.order.dto.OrderDto;
import com.example.concurrency.domain.order.entity.Order;
import com.example.concurrency.domain.order.repo.OrderRepository;
import com.example.concurrency.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    @Transactional
    public Order createOrder(User user, Item item, OrderDto orderDto) {
        Order order = Order.builder().count(orderDto.getCount())
                .totalPrice(orderDto.getTotalPrice())
                .registerDate(orderDto.getRegisterDate())
                .build();

        order.setOrderWithUser(user);
        order.setItem(item);

        return orderRepository.save(order);
    }

    public void deleteAllItems(){
        orderRepository.deleteAllItems();
    }

    public long getOrderCount(){
        return orderRepository.count();
    }
}
