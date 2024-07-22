package com.example.concurrency.domain.user.entity;

import com.example.concurrency.domain.order.entity.Order;
import com.example.concurrency.domain.point.entity.Point;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String id;

    private String pw;

    private LocalDateTime registerDate;

    @ToString.Exclude
    @Setter
    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Point point;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    @Builder
    public User(String id, String pw, LocalDateTime registerDate) {
        this.id = id;
        this.pw = pw;
        this.registerDate = registerDate;
        this.orders = new ArrayList<>();
    }
}
