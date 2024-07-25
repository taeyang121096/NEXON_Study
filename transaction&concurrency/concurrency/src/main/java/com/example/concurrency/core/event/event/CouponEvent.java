package com.example.concurrency.core.event.event;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class CouponEvent {

    private final String name;

    private final Long price;

    private final LocalDateTime expireTime;

    private final LocalDateTime registerDate;

    private final String userId;
}
