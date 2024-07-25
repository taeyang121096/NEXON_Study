package com.example.concurrency.domain.coupon.dto;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor
public class CouponDto {

    private Long couponId;

    private String name;

    private LocalDateTime expireDate;

    @Builder.Default
    private LocalDateTime registerDate = LocalDateTime.now();

}
