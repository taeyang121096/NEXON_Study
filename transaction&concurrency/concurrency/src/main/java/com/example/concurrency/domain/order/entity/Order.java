package com.example.concurrency.domain.order.entity;

import com.example.concurrency.domain.item.entity.Item;
import com.example.concurrency.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

    private Long count;

    private Long totalPrice;

    private LocalDateTime registerDate;

    @Setter
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Order(Long count, Long totalPrice, LocalDateTime registerDate) {
        this.count = count;
        this.totalPrice = totalPrice;
        this.registerDate = registerDate;
    }

    public void setOrderWithUser(User user) {
        this.user = user;
        user.getOrders().add(this);
    }

}
