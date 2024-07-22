package com.example.concurrency.domain.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class OrderDto {


    private Long orderId;

    private Long count;

    private Long totalPrice;

    private LocalDateTime registerDate;

}
