package com.example.concurrency.domain.item.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;

    private String name;

    private Long price;

    @Setter
    private Long count;

    private LocalDateTime registerDate;

    @Builder
    public Item(String name, Long price, Long count, LocalDateTime registerDate) {
        this.name = name;
        this.price = price;
        this.count = count;
        this.registerDate = registerDate;
    }
}
