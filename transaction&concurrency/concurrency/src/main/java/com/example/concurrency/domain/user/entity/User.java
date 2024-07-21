package com.example.concurrency.domain.user.entity;

import com.example.concurrency.domain.order.entity.Order;
import com.example.concurrency.domain.point.entity.Point;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String id;

    private String pw;

    @OneToOne(mappedBy = "user", fetch = FetchType.LAZY)
    private Point point;

    @OneToMany(mappedBy = "users", fetch = FetchType.LAZY)
    private List<Order> orders;

    private LocalDateTime registerDate;

    @Builder
    public User(String id, String pw, LocalDateTime registerDate) {
        this.id = id;
        this.pw = pw;
        this.registerDate = registerDate;
    }
}
