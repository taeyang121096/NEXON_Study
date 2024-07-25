package com.example.concurrency.domain.point.entity;

import com.example.concurrency.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pointId;

    @Setter
    private Long point;

    private LocalDateTime registerDate;

//    @Setter
//    @OneToOne(mappedBy = "point", fetch = FetchType.LAZY)
//    private User user;

    @Builder
    public Point(Long point, LocalDateTime registerDate) {
        this.point = point;
        this.registerDate = registerDate;
    }

}
