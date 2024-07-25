package com.example.concurrency.domain.coupon.entity;

import com.example.concurrency.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long couponId;

    private String name;

    private Long price;

    private LocalDateTime expireDate;

    private LocalDateTime registerDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Coupon(String name, Long price, LocalDateTime expireDate, LocalDateTime registerDate){
        this.name = name;
        this.price = price;
        this.expireDate = expireDate;
        this.registerDate = registerDate;
    }

}
