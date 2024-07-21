package com.example.concurrency.domain.point.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PointDto {

    private Long pointId;

    private Long point;

    @Builder.Default
    private LocalDateTime registerDate = LocalDateTime.now();

}
